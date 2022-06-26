package ar.edu.unlam.tallerweb1.servicios;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ar.edu.unlam.tallerweb1.modelo.DatosGestorAlquiler;
import ar.edu.unlam.tallerweb1.modelo.Sucursal;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioSucursal;
import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.tallerweb1.modelo.Locker;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioLocker;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class ServicioAlquilerTest {
	private static final Long ID_RAMOS = 1L;
	private static final String TAMANIO_CHICO = "20x50x60";
	ServicioAlquiler servicioAlquiler;
	RepositorioLocker repositorioLockerDAO;
	ServicioSucursal servicioSucursal;
	RepositorioSucursal repositorioSucursal;
	int lockerId;
	Long usuarioId;
	Usuario usuario;

	HttpServletRequest request;
	HttpSession session;

	@Before
	public void setUp() throws Exception {
		repositorioLockerDAO = mock(RepositorioLocker.class);
		request = mock(HttpServletRequest.class);
		session = mock(HttpSession.class);
		usuario= mock(Usuario.class);
		servicioAlquiler = new ServicioAlquilerImpl(repositorioLockerDAO);
		repositorioSucursal = mock(RepositorioSucursal.class);
		servicioSucursal = new ServicioSucursalImpl(repositorioSucursal);

	}
	@Test
	public void queSePuedaAlquilarUnLocker() {
		when(repositorioLockerDAO.getEstadoLocker(lockerId)).thenReturn(false);
		Boolean actual = servicioAlquiler.alquilarLocker(lockerId,usuarioId);
		assertTrue(actual);
		
	}
	@Test
	public void queNoSePuedaAlquilarUnLocker() {
		when(repositorioLockerDAO.getEstadoLocker(lockerId)).thenReturn(true);
		Boolean actual = servicioAlquiler.alquilarLocker(lockerId,usuarioId);
		assertFalse(actual);
		
	}
	@Test
	public void queSePuedaObtenerElEstadoDeUnLocker() {
		when(repositorioLockerDAO.getEstadoLocker(lockerId)).thenReturn(false);
		Boolean actual = servicioAlquiler.getEstadoLocker(lockerId);
		assertFalse(actual);
	}
	@Test
	public void mostrarAlquileresDeUnUsuarioLlamaAlMetodoBuscarAlquileresPropios() {
		servicioAlquiler.verAlquileresPropios(usuario);
		verify(repositorioLockerDAO, times(1)).buscarAlquileresActivosDeUsuario(any());
	}


	@Test
	public void elServicioDeAlquilerCambiaTextoDelLocker(){
		Usuario usuario1= new Usuario();
		usuario1.setId(1L);
		Locker locker1= new Locker();
		locker1.setId(1);
		locker1.setTextoDelUsuario("asd");
		String texto="tiene Bananas";
		when(repositorioLockerDAO.NotaDelLocker(1L)).thenReturn(texto);
		servicioAlquiler.ModificarNotaDeLocker(locker1.getId(),texto);
		assertThat(servicioAlquiler.NotaDelocker(locker1.getId())).isEqualTo(texto);
	}
	@Test
	public void elServicioDeAlquilerCambiaTextoDelLocker2(){
		Usuario usuario1= new Usuario();
		usuario1.setId(1L);
		Locker locker1= new Locker();
		locker1.setId(1);
		locker1.setTextoDelUsuario("asd");
		String texto="tiene Bananas";
		when(repositorioLockerDAO.NotaDelLocker(1L)).thenReturn(texto);
		servicioAlquiler.ModificarNotaDeLocker(locker1.getId(),texto);
		assertThat(servicioAlquiler.NotaDelocker(locker1.getId())).isEqualTo(texto);
	}
	@Test
	public void elServicioDEAlguilerTieneUnMetodoParaMostrarTodoLosDatosDeLosAlquileres(){
		// preparacion
		Usuario usuario1 = new Usuario();
		usuario1.setId(1L);
		Sucursal sucursal1=new Sucursal();
		sucursal1.setId(1L);
		Locker locker1 = new Locker();
		locker1.setId(1);
		locker1.setIdSucursal(1L);
		locker1.setUsuarioId(1L);

		List <DatosGestorAlquiler> lista= new ArrayList<>();
		DatosGestorAlquiler datos= new DatosGestorAlquiler();
		lista.add(datos);
		when(repositorioLockerDAO.GestorAlquileresDelUsuario(usuario1)).thenReturn(lista);

		// ejecucion
		List<DatosGestorAlquiler> gestor = servicioAlquiler.GestinarAlquilerUsuario(usuario1);
		// comparacion
		assertThat(gestor.size()).isEqualTo(1);
	}

	@Test
	public void queSePuedaCancelarUnLockerAlquilado() {
		int lockerId= 1;
		Long usuarioId = 1L;
		Locker locker = new Locker();
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("userId")).thenReturn(1L);
		when(repositorioLockerDAO.getEstadoLocker(lockerId)).thenReturn(false);
		servicioAlquiler.cancelarLocker(lockerId, usuarioId);
		assertThat(servicioAlquiler.getEstadoLocker(lockerId)).isFalse();
	}
	
	@Test
	public void testQueSePuedanBuscarLockersDisponiblesPorSucursal() {
		servicioAlquiler.buscarLockersDisponiblesPorSucursal(ID_RAMOS);
		verify(repositorioLockerDAO, times(1)).buscarLockersDisponiblesPorSucursal(ID_RAMOS);
	}
	
	@Test
	public void testQueSePuedanBuscarLockersDisponiblesPorSucursalYTamanio() {
		servicioAlquiler.buscarLockersDisponiblesPorSucursalYTamanio(ID_RAMOS, TAMANIO_CHICO);
		verify(repositorioLockerDAO, times(1)).buscarLockersDisponiblesPorSucursalYTamanio(ID_RAMOS, TAMANIO_CHICO);
	}

}
