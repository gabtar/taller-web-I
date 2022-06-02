package ar.edu.unlam.tallerweb1.servicios;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.hibernate.mapping.List;
import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.tallerweb1.modelo.Locker;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioLocker;

public class ServicioHomeTest {
	ServicioHomeImpl  servicioHome;
	RepositorioLocker repositorioLockerDAO;
	Locker locker;
	Usuario usuario;

	@Before
	public void setUp() throws Exception {
		repositorioLockerDAO = mock(RepositorioLocker.class);
		locker= mock(Locker.class);
		usuario= mock(Usuario.class);
		
		servicioHome = new ServicioHomeImpl(repositorioLockerDAO);
	}
	@Test
	public void queSePuedaAlquilarUnLocker() {
		when(repositorioLockerDAO.getEstadoLocker(locker)).thenReturn(false);
		Boolean actual = servicioHome.alquilarLocker(locker,usuario);
		assertTrue(actual);
		
	}
	@Test
	public void queNoSePuedaAlquilarUnLocker() {
		when(repositorioLockerDAO.getEstadoLocker(locker)).thenReturn(true);
		Boolean actual = servicioHome.alquilarLocker(locker,usuario);
		assertFalse(actual);
		
	}
	@Test
	public void queSePuedaObtenerElEstadoDeUnLocker() {
		when(repositorioLockerDAO.getEstadoLocker(locker)).thenReturn(false);
		Boolean actual = servicioHome.getEstadoLocker(locker);
		assertFalse(actual);
	}
	@Test
	public void mostrarAlquileresDeUnUsuario() {
		
		servicioHome.verAlquileresPropios(usuario);
		
		verify(repositorioLockerDAO, times(1)).buscarAlquileresActivosDeUsuario(any());
		
	}

}
