package ar.edu.unlam.tallerweb1.servicios;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.tallerweb1.modelo.Locker;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioLocker;

public class ServicioHomeTest {
	ServicioHomeImpl  servicioHome;
	RepositorioLocker repositorioLockerDAO;
	Locker locker;
	@Before
	public void setUp() throws Exception {
		repositorioLockerDAO = mock(RepositorioLocker.class);
		locker= mock(Locker.class);
		servicioHome = new ServicioHomeImpl(repositorioLockerDAO);
	}
	@Test
	public void queSePuedaAlquilarUnLocker() {
		when(repositorioLockerDAO.getEstadoLocker(locker)).thenReturn(false);
		Boolean actual = servicioHome.alquilarLocker(locker);
		assertTrue(actual);
		
	}
	@Test
	public void queNoSePuedaAlquilarUnLocker() {
		when(repositorioLockerDAO.getEstadoLocker(locker)).thenReturn(true);
		Boolean actual = servicioHome.alquilarLocker(locker);
		assertFalse(actual);
		
	}
	@Test
	public void queSePuedaObtenerElEstadoDeUnLocker() {
		when(repositorioLockerDAO.getEstadoLocker(locker)).thenReturn(false);
		Boolean actual = servicioHome.getEstadoLocker(locker);
		assertFalse(actual);
	}

}
