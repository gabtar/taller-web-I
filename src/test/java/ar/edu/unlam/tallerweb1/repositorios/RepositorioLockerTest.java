package ar.edu.unlam.tallerweb1.repositorios;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import ar.edu.unlam.tallerweb1.servicios.ServicioAlquilerImpl;
import ar.edu.unlam.tallerweb1.servicios.ServicioSucursalImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Locker;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public class RepositorioLockerTest extends SpringTest{

	HttpServletRequest request;
	HttpSession session;
	@Autowired
	private RepositorioLocker repositorioLocker;
	private int lockerId= 1;
	private Long usuarioId = 1L;

	@Before
	public void setUp() throws Exception {
		request = mock(HttpServletRequest.class);
		session = mock(HttpSession.class);
	}
	@Test @Transactional @Rollback
	public void queElLockerSeCreeSinAlquilar() {
		Locker locker = dadoQueTengoElSiguienteLocker();
		esperoQueCreeSinAlquilar(locker);
	}

	private void esperoQueCreeSinAlquilar(Locker locker) {
		assertThat(repositorioLocker.getEstadoLocker(locker.getId())).isEqualTo(false);
	}
	@Test @Transactional @Rollback
	public void queSePuedaBuscarUnLockerPorId() {
		Locker locker = dadoQueTengoElSiguienteLocker();
		Locker lockerEsperado = cuandoObtengoLosLockers(locker);
		esperoElLocker(lockerEsperado, locker);
	}

	private Locker dadoQueTengoElSiguienteLocker() {
		Locker locker = new Locker();
		locker.setIdSucursal((long)2);
		locker.setOcupado(false);
		session().save(locker);

		return locker;
	}

	private Locker cuandoObtengoLosLockers(Locker locker) {

		return repositorioLocker.buscarLockersPorId(locker.getId());
	}

	private void esperoElLocker(Locker lockerEsperado, Locker locker) {

		assertThat(lockerEsperado).isEqualTo(locker);
	}

	@Test @Transactional @Rollback
	public void queSeMuestreLosLockersAlquiladosDeUnCliente() {
		Usuario usuario = dadoQueExisteElUsuario();
		Locker locker = dadoQueElUsuarioTieneLosSiguienteLockers(usuario);
		Locker lockerEsperado=cuandoObtengoLosLockersDelUsuario(usuario);
		esperoElLocker(lockerEsperado, locker);
	}

	private Usuario dadoQueExisteElUsuario() {
		Usuario usuario=new Usuario();
		usuario.setEmail("g@g");
		usuario.setPassword("1234");
		session().save(usuario);

		return usuario;
	}
	private Locker dadoQueElUsuarioTieneLosSiguienteLockers(Usuario usuario) {

		int id= 1;
		Locker locker = new Locker();
		locker.setId(id);
		locker.setIdSucursal((long)2);
		locker.setOcupado(false);
		locker.setUsuario(usuario.getId());
		session().save(locker);

		return locker;
	}

	private Locker cuandoObtengoLosLockersDelUsuario(Usuario usuario) {
		return repositorioLocker.buscarLockersPorUsuario(usuario);
	}

	private void esperoLosLockersDelUsuario(Locker lockerEsperado, Locker locker) {
		assertThat(lockerEsperado).isEqualTo(locker);
	}
	@Test @Transactional @Rollback
	public void queSeMuestreLosLockersDisponibles() {
		dadoQueTengoLosSiguienteLockersDisponibles();
		List <Locker> listaEsperada = cuandoObtengoLosLockersDisponibles();
		esperoLaListaDeLockersDisponibles(listaEsperada);
	}

	private void dadoQueTengoLosSiguienteLockersDisponibles() {
		int id= 1;
		Locker locker = new Locker();
		locker.setId(id);
		locker.setIdSucursal((long)2);
		locker.setOcupado(false);
		session().save(locker);
		int id2= 2;
		Locker locker2 = new Locker();
		locker.setId(id);
		locker.setIdSucursal((long)2);
		locker.setOcupado(false);
		session().save(locker2);
	}

	private List<Locker> cuandoObtengoLosLockersDisponibles() {
		return repositorioLocker.buscarLockers();
	}

	private void esperoLaListaDeLockersDisponibles(List<Locker> lockerEsperado) {
		assertThat(lockerEsperado).hasSize(2);
	}

	@Test @Transactional @Rollback
	public void queSePuedaCancelarUnLockerAlquilado() {
		Locker locker = dadoQueTengoElSiguienteLockerAlquilado();
		cuandoQuieroCancelar(locker);
		esperoPoderCancelarlo(locker);
	}

	private Locker dadoQueTengoElSiguienteLockerAlquilado() {
		Locker locker = new Locker();
		locker.setId(lockerId);
		locker.setUsuario(usuarioId);
		session().save(locker);
		repositorioLocker.alquilarLocker(locker.getId(), usuarioId);
		return locker;
	}

	private Boolean cuandoQuieroCancelar(Locker locker) {
		boolean actual = repositorioLocker.cancelarLocker(locker.getId(), usuarioId);
		return actual;
	}

	private void esperoPoderCancelarlo(Locker locker) {
		assertThat(locker.isOcupado()).isFalse();
	}

}
