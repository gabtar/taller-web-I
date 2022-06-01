package ar.edu.unlam.tallerweb1.servicios;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.tallerweb1.modelo.Locker;
import ar.edu.unlam.tallerweb1.modelo.Producto;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioLocker;

public class ServicioHomeTest {
	ServicioHomeImpl  servicioHome;
	RepositorioLocker repositorioLocker;
	@Before
	public void setUp() throws Exception {
		repositorioLocker = mock(RepositorioLocker.class);
	}
	@Test
	public void queSePuedaGuardarUnProducto() {
		Producto producto = new Producto();
		Locker locker= new Locker();
		servicioHome.guardarProducto(producto,locker);
		
		verify(repositorioLocker, times(1)).guardarProductoEnLocker(producto,locker);
	}

}
