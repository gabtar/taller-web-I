package ar.edu.unlam.tallerweb1.repositorios;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
	HttpServletRequest request;
	HttpSession session;
	@Autowired
	private RepositorioLocker repositorioLocker;

	@Before
	public void setUp() throws Exception {
		request = mock(HttpServletRequest.class);
		session = mock(HttpSession.class);
	}

	@Test
	@Transactional
	@Rollback
	public void queElLockerSeCreeSinAlquilar() {
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
		Locker locker = new Locker();
		locker.setId(id);
		locker.setIdSucursal((long) 2);
		locker.setOcupado(false);
		locker.setUsuario(usuario.getId());
		session().save(locker);
		Locker lockerEsperado = repositorioLocker.buscarLockersPorUsuario(usuario);
		assertThat(lockerEsperado).isEqualTo(locker);

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
		int lockerId = 1;
		Long usuarioId = 1L;
		Locker locker = new Locker();
		locker.setId(lockerId);
		locker.setUsuario(usuarioId);
		session().save(locker);
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("userId")).thenReturn(1L);
		repositorioLocker.alquilarLocker(locker.getId(), usuarioId);
		repositorioLocker.cancelarLocker(locker.getId(), usuarioId);
		assertThat(locker.isOcupado()).isFalse();
	}

	@Test
	@Transactional
	@Rollback
	public void testQueSePuedanBuscarLockersPorSucursal() {

		Sucursal sucRamos = dadoQueTengoUnaSucursalConLockersDisponibles();
	
		List<Locker> lockersEncontrados = cuandoBuscoLockersPorLocalidad(sucRamos.getId());
		
		// No se porque el CI de github no me toma bien la igualdad
		// System.out.println("SON IGUALES:" + sucRamos.getId().equals(ID_RAMOS);

		entoncesEncuentroLockersDisponibles(lockersEncontrados, 2);
	}

	private void entoncesEncuentroLockersDisponibles(List<Locker> lockersEncontrados, int lockersEsperados) {
		assertThat(lockersEncontrados.size()).isEqualTo(lockersEsperados);
	}

	private List<Locker> cuandoBuscoLockersPorLocalidad(Long idRamos) {
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
		session().save(l1);
		Locker l2 = new Locker();
		l2.setId(2);
		l2.setOcupado(true);
		l2.setSucursal(sucRamos);
		session().save(l2);
		Locker l3 = new Locker();
		l3.setId(3);
		l3.setSucursal(sucRamos);
		session().save(l3);

		// En haedo
		Locker l4 = new Locker();
		l4.setId(4);
		l4.setSucursal(sucHaedo);
		session().save(l4);
		
		return sucRamos;
	}

}
