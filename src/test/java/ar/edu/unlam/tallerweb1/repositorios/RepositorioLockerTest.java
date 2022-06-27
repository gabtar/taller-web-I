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
import ar.edu.unlam.tallerweb1.modelo.Localidad;
import ar.edu.unlam.tallerweb1.modelo.Locker;
import ar.edu.unlam.tallerweb1.modelo.Sucursal;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public class RepositorioLockerTest extends SpringTest {

	private static final Long ID_RAMOS = 1L;
	private static final String TAMANIO_CHICO = "40x50x60";
	private static final String TAMANIO_GRANDE = "80x80x100";
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

	@Test
	@Transactional
	@Rollback
	public void queElLockerSeCreeSinAlquilar() {
<<<<<<< HEAD
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
=======
		int lockerId = 1;
		int usuarioId = 1;
		Usuario usuario = new Usuario();
		usuario.setEmail("g@g");
		usuario.setPassword("1234");
		session().save(usuario);
		Locker locker = new Locker();
		locker.setId(lockerId);
		locker.setIdSucursal((long) 2);
		locker.setOcupado(false);
		session().save(locker);

		assertThat(repositorioLocker.getEstadoLocker(locker.getId())).isEqualTo(false);
	}

	@Test
	@Transactional
	@Rollback
	public void queSePuedaBuscarUnLockerPorId() {
		Locker locker = new Locker();
		locker.setIdSucursal((long) 2);
		locker.setOcupado(false);
		session().save(locker);
		Locker lockerEsperado = repositorioLocker.buscarLockersPorId(locker.getId());
		assertThat(lockerEsperado).isEqualTo(locker);
	}

	@Test
	@Transactional
	@Rollback
	public void queSeMuestreLosLockersAlquiladosDeUnCliente() {
		Usuario usuario = new Usuario();
		usuario.setEmail("g@g");
		usuario.setPassword("1234");
		session().save(usuario);
		int id = 1;
>>>>>>> c052e64bbea9e5d7975a9c72d9a5c3f3fd69c023
		Locker locker = new Locker();
		locker.setId(id);
		locker.setIdSucursal((long) 2);
		locker.setOcupado(false);
		locker.setUsuario(usuario.getId());
		session().save(locker);
<<<<<<< HEAD

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
=======
		Locker lockerEsperado = repositorioLocker.buscarLockersPorUsuario(usuario);
		assertThat(lockerEsperado).isEqualTo(locker);

>>>>>>> c052e64bbea9e5d7975a9c72d9a5c3f3fd69c023
	}

	@Test
	@Transactional
	@Rollback
	public void queSeMuestreLosLockersDisponibles() {

		int id = 1;
		Locker locker = new Locker();
		locker.setId(id);
		locker.setIdSucursal((long) 2);
		locker.setOcupado(false);
		session().save(locker);
		int id2 = 2;
		Locker locker2 = new Locker();
		locker.setId(id);
		locker.setIdSucursal((long) 2);
		locker.setOcupado(false);
		session().save(locker2);
		List lockerEsperado = repositorioLocker.buscarLockers();
		assertThat(lockerEsperado).hasSize(2);

	}

	@Test
	@Transactional
	@Rollback
	public void queSePuedaCancelarUnLockerAlquilado() {
<<<<<<< HEAD
		Locker locker = dadoQueTengoElSiguienteLockerAlquilado();
		cuandoQuieroCancelar(locker);
		esperoPoderCancelarlo(locker);
	}

	private Locker dadoQueTengoElSiguienteLockerAlquilado() {
=======
		int lockerId = 1;
		Long usuarioId = 1L;
>>>>>>> c052e64bbea9e5d7975a9c72d9a5c3f3fd69c023
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

	@Test
	@Transactional
	@Rollback
	public void testQueSePuedanBuscarLockersPorSucursal() {

		Sucursal sucRamos = dadoQueTengoUnaSucursalConLockersDisponibles();
	
		List<Locker> lockersEncontrados = cuandoBuscoLockersDisponiblesPorSucursal(sucRamos.getId());

		entoncesEncuentroLockersDisponibles(lockersEncontrados, 2);
	}

	private void entoncesEncuentroLockersDisponibles(List<Locker> lockersEncontrados, int lockersEsperados) {
		assertThat(lockersEncontrados.size()).isEqualTo(lockersEsperados);
	}

	private List<Locker> cuandoBuscoLockersDisponiblesPorSucursal(Long idRamos) {
		return (List<Locker>) repositorioLocker.buscarLockersDisponiblesPorSucursal(idRamos);
	}

	private Sucursal dadoQueTengoUnaSucursalConLockersDisponibles() {
		Localidad ramos = new Localidad();
		ramos.setNombre("Ramos");
		ramos.setId(1L);
		session().save(ramos);
		Localidad haedo = new Localidad();
		haedo.setNombre("Haedo");
		haedo.setId(2L);
		session().save(haedo);

		Sucursal sucRamos = new Sucursal();
		sucRamos.setId(ID_RAMOS);
		sucRamos.setLocalidad(ramos);
		session().save(sucRamos);

		Sucursal sucHaedo = new Sucursal();
		sucRamos.setLocalidad(haedo);
		session().save(sucHaedo);

		Locker l1 = new Locker();
		l1.setId(1);
		l1.setSucursal(sucRamos);
		l1.setTamano(TAMANIO_CHICO);
		session().save(l1);
		Locker l2 = new Locker();
		l2.setId(2);
		l2.setOcupado(true);
		l2.setSucursal(sucRamos);
		l2.setTamano(TAMANIO_GRANDE);
		session().save(l2);
		Locker l3 = new Locker();
		l3.setId(3);
		l3.setSucursal(sucRamos);
		l2.setTamano(TAMANIO_GRANDE);
		session().save(l3);

		// En haedo
		Locker l4 = new Locker();
		l4.setId(4);
		l4.setSucursal(sucHaedo);
		l4.setTamano(TAMANIO_CHICO);
		session().save(l4);
		
		return sucRamos;
	}
	
	@Test
	@Transactional
	@Rollback
	public void testQueSePuedanBuscarLockersPorSucursalYTamanio() {
		Sucursal sucRamos = dadoQueTengoUnaSucursalConLockersDisponibles();
		
		List<Locker> lockersEncontrados = cuandoBuscoLockersDisponiblesPorSucursalYTamanio(sucRamos.getId(), TAMANIO_CHICO);

		entoncesEncuentroLockersDisponibles(lockersEncontrados, 1);
	}

	private List<Locker> cuandoBuscoLockersDisponiblesPorSucursalYTamanio(Long id, String tamanioChico) {
		return repositorioLocker.buscarLockersDisponiblesPorSucursalYTamanio(id, tamanioChico);
	}

}
