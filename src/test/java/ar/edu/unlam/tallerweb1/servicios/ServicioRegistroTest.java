package ar.edu.unlam.tallerweb1.servicios;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;

public class ServicioRegistroTest {

	private RepositorioUsuario repositorioUsuario;
	private ServicioRegistroImpl servicioRegistro;

	@Before
	public void setUp() throws Exception {
		
		repositorioUsuario = mock(RepositorioUsuario.class);
		servicioRegistro = new ServicioRegistroImpl(repositorioUsuario);
	}

	@Test
	public void testQueSePuedaGuardarUnUsuario() {
		// Given
		
		// When
		servicioRegistro.registrar("mail@mail.com", "1234");
		
		// Then
		verify(repositorioUsuario, times(1)).guardar(any(Usuario.class));
	}

}
