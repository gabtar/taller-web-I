package ar.edu.unlam.tallerweb1.controladores;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Locker;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioHome;

public class ControladorHomeTest {

	ServicioHome servicioHome;
	ControladorHome controladorHome;
	private HttpSession session;

	@Before
	public void setUp() throws Exception {
		servicioHome = mock(ServicioHome.class);
		session = mock(HttpSession.class);
		controladorHome = new ControladorHome(servicioHome, session);
	}

	@Test
	public void queSePuedaAlquilarUnLocker() {
		Locker locker = new Locker();
		Usuario usuario = new Usuario();
		when(servicioHome.alquilarLocker(locker, usuario)).thenReturn(true);
		ModelAndView mav = controladorHome.alquilarLocker(locker, usuario);
		String error = "alquiler Exitoso";

		assertThat("home").isEqualTo(mav.getViewName());
		assertThat(mav.getModel().get("error")).isEqualTo(error);

	}

	@Test
	public void queNoSePuedaAlquilarUnLockerYaAlquilado() {
		Locker locker = new Locker();
		Usuario usuario = new Usuario();
		when(servicioHome.alquilarLocker(locker, usuario)).thenReturn(false);
		ModelAndView mav = controladorHome.alquilarLocker(locker, usuario);
		String error = "Locker no disponible";

		assertThat("home").isEqualTo(mav.getViewName());
		assertThat(mav.getModel().get("error")).isEqualTo(error);

	}

	@Test
	public void queCuandoVoyALaHomeMeLLeveALaVistaDeLaHome() {
		ModelAndView vistaEsperada = new ModelAndView("home");

		when(servicioHome.buscarAlquileresDisponibles()).thenReturn(null);
		when(session.getAttribute("userId")).thenReturn(5);
		ModelAndView vistaObtenida = controladorHome.irHome();

		assertThat(vistaEsperada.getViewName()).isEqualTo(vistaObtenida.getViewName());
	}
}
