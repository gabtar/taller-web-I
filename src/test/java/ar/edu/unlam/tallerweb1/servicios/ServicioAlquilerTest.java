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
	int lockerId = 1;
	private static final Long USUARIO_ID = 1L;
	Usuario usuario;

	Locker locker;

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
		dadoQueTengoElSiguienteLockerDisponible();
		//when(repositorioLockerDAO.getEstadoLocker(lockerId)).thenReturn(false);
		Boolean actual = cuandoQuieroAlquilar();
		//Boolean actual = servicioAlquiler.alquilarLocker(lockerId,usuarioId);
		esperoPoderAlquilarlo(actual);
		//assertTrue(actual);
	}

	private void dadoQueTengoElSiguienteLockerDisponible() {
		locker = new Locker();
		locker.setId(lockerId);
		locker.setOcupado(false);
		when(repositorioLockerDAO.getEstadoLocker(locker.getId())).thenReturn(false);
	}

	private Boolean cuandoQuieroAlquilar() {
		boolean actual;
		return actual = servicioAlquiler.alquilarLocker(lockerId,USUARIO_ID);
	}

	private void esperoPoderAlquilarlo(Boolean actual) {
		assertTrue(actual);
	}
	@Test
	public void queNoSePuedaAlquilarUnLocker() {
		dadoQueTengoElSiguienteLockerOcupado();
		Boolean actual = cuandoQuieroAlquilar();
		esperoNoPoderAlquilarlo(actual);
	}

	private void dadoQueTengoElSiguienteLockerOcupado() {
		locker = new Locker();
		locker.setId(lockerId);
		locker.setOcupado(true);
		when(repositorioLockerDAO.getEstadoLocker(locker.getId())).thenReturn(true);
	}

	private void esperoNoPoderAlquilarlo(Boolean actual) {
		assertFalse(actual);
	}
	@Test
	public void queSePuedaObtenerElEstadoDeUnLocker() {
		dadoQueTengoElSiguienteLockerDisponible();
		Boolean actual = cuandoObtengoElEstado();
		esperoElEstado(actual);
	}

	private Boolean cuandoObtengoElEstado() {
		Boolean actual;
		return servicioAlquiler.getEstadoLocker(lockerId);
	}

	@Test
	public void mostrarLockersDelUsuario() {
		List lockersUsuario = dadoQueElUsuarioTieneLosSiguienteLocker();
		List lista = cuandoObtengoLaListaDeLocker(usuario, lockersUsuario);
		esperoLaLista(lista);
	}

	private List<Locker> dadoQueElUsuarioTieneLosSiguienteLocker() {
		List <Locker>listaLocker = new ArrayList<>();
		usuario.setId(USUARIO_ID);
		locker = new Locker();
		Locker locker2 = new Locker();
		locker.setId(lockerId);
		locker2.setId(2);
		locker.setOcupado(true);
		locker2.setOcupado(true);
		locker.setPropietario(usuario);
		locker2.setPropietario(usuario);
		listaLocker.add(locker);
		listaLocker.add(locker2);
		return listaLocker;
	}

	private List<Locker> cuandoObtengoLaListaDeLocker(Usuario usuario, List lista) {
		when(repositorioLockerDAO.buscarLockersPorUsuario(usuario)).thenReturn(lista);
		return servicioAlquiler.verAlquileresPropios(usuario);
	}

	private void esperoLaLista(List lista) {
		assertThat(lista.size()).isEqualTo(2);
	}
	private void esperoElEstado(Boolean actual) {
		assertFalse(actual);
	}

	@Test
	public void queSePuedaCancelarUnLockerAlquilado() {
		dadoQueTengoElSiguienteLockerOcupado();
		Boolean actual = cuandoQuieroCancelar();
		esperoPoderCancelarlo(actual);
	}

	private Boolean cuandoQuieroCancelar(){
		boolean actual = servicioAlquiler.cancelarLocker(lockerId, USUARIO_ID);
		return actual;
	}

	private void esperoPoderCancelarlo(Boolean actual) {
		assertTrue(actual);
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
