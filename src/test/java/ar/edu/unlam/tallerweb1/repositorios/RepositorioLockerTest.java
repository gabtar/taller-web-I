package ar.edu.unlam.tallerweb1.repositorios;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Locker;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public class RepositorioLockerTest extends SpringTest{
	
	
	@Autowired
	private RepositorioLocker repositorioLocker;
	
	@Test @Transactional @Rollback
	public void queElLockerSeCreeSinAlquilar() {
		int id= 1;
		Usuario usuario=new Usuario();
		usuario.setEmail("g@g");
		usuario.setPassword("1234");
		session().save(usuario);
		Locker locker = new Locker();
		locker.setId(id);
		locker.setIdSucursal((long)2);
		locker.setOcupado(false);
		session().save(locker);
	
		assertThat(repositorioLocker.getEstadoLocker(locker)).isEqualTo(false);
		
	}
	@Test @Transactional @Rollback
	public void queSePuedaCambiarElEstadoDeUnLockerAlquilado() {
		int id= 1;
		Usuario usuario=new Usuario();
		usuario.setEmail("g@g");
		usuario.setPassword("1234");
		session().save(usuario);
		Locker locker = new Locker();
		locker.setId(id);
		locker.setIdSucursal((long)2);
		locker.setOcupado(false);
		session().save(locker);
		
		repositorioLocker.alquilarLocker(locker,usuario);
		assertThat(repositorioLocker.getEstadoLocker(locker)).isEqualTo(true);
		
	}
	@Test @Transactional @Rollback
	public void queNoSeAlquileUnLockerYaAlquilado() {
		int id= 1;
		Usuario usuario=new Usuario();
		usuario.setEmail("g@g");
		usuario.setPassword("1234");
		session().save(usuario);
		Locker locker = new Locker();
		locker.setId(id);
		locker.setIdSucursal((long)2);
		locker.setOcupado(true);
		session().save(locker);	
		assertThat(repositorioLocker.alquilarLocker(locker,usuario)).isEqualTo(false);
		
	}

	@Test @Transactional @Rollback
	public void queSePuedaBuscarUnLockerPorId() {
		Locker locker = new Locker();
		locker.setIdSucursal((long)2);
		locker.setOcupado(false);
		session().save(locker);
		Locker lockerEsperado=repositorioLocker.buscarLockersPorId(locker.getId());
		assertThat(lockerEsperado).isEqualTo(locker);
	}
	@Test @Transactional @Rollback
	public void queSeMuestreLosLockersAlquiladosDeUnCliente() {
		Usuario usuario=new Usuario();
		usuario.setEmail("g@g");
		usuario.setPassword("1234");
		session().save(usuario);
		int id= 1;
		Locker locker = new Locker();
		locker.setId(id);
		locker.setIdSucursal((long)2);
		locker.setOcupado(false);
		locker.setUsuario(usuario);
		session().save(locker);
		Locker lockerEsperado=repositorioLocker.buscarLockersPorUsuario(usuario);
		assertThat(lockerEsperado).isEqualTo(locker);
		
	}
	@Test @Transactional @Rollback
	public void queSeMuestreLosLockersDisponibles() {
		
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
		List lockerEsperado =repositorioLocker.buscarLockers();
		assertThat(lockerEsperado).hasSize(2);
		
	}

}
