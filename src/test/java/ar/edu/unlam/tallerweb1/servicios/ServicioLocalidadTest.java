package ar.edu.unlam.tallerweb1.servicios;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.tallerweb1.repositorios.RepositorioLocalidadImpl;

public class ServicioLocalidadTest {

	private RepositorioLocalidadImpl repositorioLocalidad;
	private ServicioLocalidadImpl servicioLocalidad;

	@Before
	public void setUp() throws Exception {
		repositorioLocalidad = mock(RepositorioLocalidadImpl.class);
		servicioLocalidad = new ServicioLocalidadImpl(repositorioLocalidad);
	}

	@Test
	public void testQueSePuedanObtenerTodasLasLocalidades() {
		servicioLocalidad.obtenerLocalidades();
		
		verify(repositorioLocalidad, times(1)).listarLocalidades();
	}

}
