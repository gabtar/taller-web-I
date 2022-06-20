package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Locker;
import ar.edu.unlam.tallerweb1.modelo.Sucursal;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioAlquiler;
import ar.edu.unlam.tallerweb1.servicios.ServicioEmail;
import ar.edu.unlam.tallerweb1.servicios.ServicioSucursal;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Transactional
@Rollback
public class ControladorAlquilerTest {

	private static final Long RAMOS = 1L;
	private ServicioAlquiler servicioAlquiler;
	private ServicioSucursal servicioSucursal;
	private ServicioEmail servicioEmail;
	private ControladorAlquiler controladorAlquiler;
	private Usuario usuario;
	HttpServletRequest request;
	HttpSession session;

	@Before
	public void setUp() throws Exception {
		servicioAlquiler = mock(ServicioAlquiler.class);
		servicioSucursal = mock(ServicioSucursal.class);
		servicioEmail = mock(ServicioEmail.class);
		request = mock(HttpServletRequest.class);
		session = mock(HttpSession.class);
		controladorAlquiler = new ControladorAlquiler(servicioAlquiler, servicioSucursal, servicioEmail);
	}

	@Test
	public void queSePuedaAlquilarUnLocker() {

		int lockerId = 1;
		final String TEXTO_EMAIL_REGISTRO = "usted a alquilado el locker " + lockerId
				+ " gracias pepe@pepe por elegirnos RENTLOCK";
		Long usuarioId = 1L;
		Locker locker = new Locker();
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("userId")).thenReturn(1L);
		when(request.getSession().getAttribute("nombreUsuario")).thenReturn("pepe@pepe");
		when(servicioAlquiler.alquilarLocker(lockerId, usuarioId)).thenReturn(true);

		ModelAndView mav = controladorAlquiler.alquilarLocker(request, lockerId);
		String error = "Alquiler exitoso";
		verify(servicioEmail, times(1)).enviarMail(eq("pepe@pepe"), any(String.class), eq(TEXTO_EMAIL_REGISTRO));
		assertThat("redirect:/homeLogeado").isEqualTo(mav.getViewName());
		assertThat(mav.getModel().get("error")).isEqualTo(error);
	}

	@Test
	public void queNoSePuedaAlquilarUnLockerYaAlquilado() {
		int lockerId = 1;
		Long usuarioId = 1L;
		Locker locker = new Locker();
		when(servicioAlquiler.alquilarLocker(lockerId, usuarioId)).thenReturn(false);
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("userId")).thenReturn(1L);
		ModelAndView mav = controladorAlquiler.alquilarLocker(request, lockerId);
		String error = "Locker no disponible";
		assertThat("redirect:/homeLogeado").isEqualTo(mav.getViewName());
		assertThat(mav.getModel().get("error")).isEqualTo(error);
	}

	@Test
	public void queMustreLosAlquileresDisponibles() {
		// preparacion
		List<Locker> listaLockers = new ArrayList<>();
		Locker A = new Locker();
		Locker B = new Locker();
		Locker C = new Locker();
		listaLockers.add(A);
		listaLockers.add(B);
		listaLockers.add(C);
		List<Locker> listaRegresada;

		// ejecucion
		when(servicioAlquiler.buscarAlquileresDisponibles()).thenReturn(listaLockers);
		ModelAndView model = controladorAlquiler.mostrarAlquileresDisponibles(request);
		listaRegresada = (List<Locker>) model.getModelMap().get("alquileres");

		// testeo
		assertThat(model.getViewName()).isEqualTo("lista-alquileres");
		assertThat(listaRegresada.size()).isEqualTo(3);
	}

	@Test
	public void queSePuedaCancelarUnLockerAlquilado() {
		int lockerId = 1;
		Long usuarioId = 1L;
		Locker locker = new Locker();
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("userId")).thenReturn(1L);
		ModelAndView mav = controladorAlquiler.cancelarLocker(request, lockerId);
		String error = "Cancelacion exitosa";
		assertThat("redirect:/homeLogeado").isEqualTo(mav.getViewName());
		assertThat(mav.getModel().get("error")).isEqualTo(error);
	}

	@Test
	public void testQueSePuedanMostrarLosAlquileresDisponiblesPorSucursal() {
		List<Locker> lockers = dadoQueUnaSucursalTieneLockersDisponibles(RAMOS);

		ModelAndView mav = cuandoPidoMostrarLockersDisponiblesPorSucursal(RAMOS);

		entoncesMeLLevaALaVista("lista-alquileres", mav.getViewName());
		entoncesEncuentroLockersDisponibles(lockers, (List) mav.getModel().get("alquileres"));
	}

	private void entoncesEncuentroLockersDisponibles(List<Locker> lockers, List<Locker> lockersEnModelo) {
		assertThat(lockers.size()).isEqualTo(lockersEnModelo.size());
	}

	private void entoncesMeLLevaALaVista(String vistaEsperada, String vistaObtenida) {
		assertThat(vistaEsperada).isEqualTo(vistaObtenida);
	}

	private ModelAndView cuandoPidoMostrarLockersDisponiblesPorSucursal(Long idSucursal) {
		ModelAndView mav = controladorAlquiler.mostrarLockersDisponiblesPorLocalidad(request, RAMOS);
		return mav;
	}

	private List<Locker> dadoQueUnaSucursalTieneLockersDisponibles(Long idSucursal) {
		when(servicioAlquiler.buscarLockersDisponiblesPorSucursal(RAMOS)).thenReturn(new ArrayList());
		return servicioAlquiler.buscarLockersDisponiblesPorSucursal(RAMOS);
	}
}
