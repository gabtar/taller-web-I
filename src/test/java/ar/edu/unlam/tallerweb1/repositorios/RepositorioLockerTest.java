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

public class RepositorioLockerTest extends SpringTest{
	
	@Autowired
	private RepositorioLocker repositorioLocker;
	
	@Test @Transactional @Rollback
	public void queSePuedaCambiarElEstadoDeUnLockerAlquilado() {
		int id= 1;
		Locker locker = new Locker();
		locker.setId(id);
		locker.setIdSucursal((long)2);
		locker.setOcupado(false);
		session().save(locker);
		
		repositorioLocker.alquilarLocker(locker);
		
		
	}

	@Test @Transactional @Rollback
	public void queSePuedaBuscarUnLockerPorId() {
		int id= 1;
		Locker locker = new Locker();
		locker.setId(id);
		locker.setIdSucursal((long)2);
		locker.setOcupado(false);
		session().save(locker);
		Locker lockerEsperado=repositorioLocker.buscarLockersPorId(id);
		assertThat(lockerEsperado).isEqualTo(locker);
	}

}
