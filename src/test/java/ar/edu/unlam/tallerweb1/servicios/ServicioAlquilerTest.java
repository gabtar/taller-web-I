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
	ServicioAlquiler servicioAlquiler;
	RepositorioLocker repositorioLockerDAO;
	ServicioSucursal servicioSucursal;
	RepositorioSucursal repositorioSucursal;
	int lockerId = 1;
	Long usuarioId = 1L;
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
		return actual = servicioAlquiler.alquilarLocker(lockerId,usuarioId);
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
		dadoQueElUsuarioTieneLosSiguienteLocker();
		usuario.setId(usuarioId);
		List lista = cuandoObtengoLaListaDeLocker(usuario);
		esperoLaLista(lista);
	}

	private void dadoQueElUsuarioTieneLosSiguienteLocker() {
		List <Locker>listaLocker = new ArrayList<>();
		usuario.setId(usuarioId);
		locker = new Locker();
		Locker locker2 = new Locker();
		locker.setId(lockerId);
		locker2.setId(2);
		locker.setOcupado(true);
		locker2.setOcupado(true);
		locker.setUsuario(usuario.getId());
		locker2.setUsuario(usuario.getId());
		listaLocker.add(locker);
		listaLocker.add(locker2);
		when(repositorioLockerDAO.buscarAlquileresActivosDeUsuario(usuario)).thenReturn(listaLocker);
	}

	private List<Locker> cuandoObtengoLaListaDeLocker(Usuario usuario) {
		//usuario.setId(usuarioId);
		return servicioAlquiler.verAlquileresPropios(usuario);
	}

	private void esperoLaLista(List lista) {
		assertThat(lista.size()).isEqualTo(2);
	}
	private void esperoElEstado(Boolean actual) {
		assertFalse(actual);
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
		dadoQueTengoElSiguienteLockerOcupado();
		Boolean actual = cuandoQuieroCancelar();
		esperoPoderCancelarlo(actual);
	}

	private Boolean cuandoQuieroCancelar(){
		boolean actual = servicioAlquiler.cancelarLocker(lockerId, usuarioId);
		return actual;
	}

	private void esperoPoderCancelarlo(Boolean actual) {
		assertTrue(actual);
	}

}
