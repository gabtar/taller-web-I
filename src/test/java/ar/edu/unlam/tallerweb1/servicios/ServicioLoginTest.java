package ar.edu.unlam.tallerweb1.servicios;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;

public class ServicioLoginTest {

	private static final String MAIL = "mail@mail.com";
	private static final String PASSWORD = "password";
	
	
	private RepositorioUsuario repositorioUsuario;
	private ServicioLoginImpl servicioLogin;
	private Usuario usuarioMock;

	@Before
	public void setUp() throws Exception {
		usuarioMock = mock(Usuario.class);
		repositorioUsuario = mock(RepositorioUsuario.class);
		servicioLogin = new ServicioLoginImpl(repositorioUsuario);
	}

	@Test
	public void testCuandoBuscoConEmailYPasswordDeUnUsuarioExistenteMeDevuelveAlUsuario() {
		// Given
		when(repositorioUsuario.buscarUsuario(MAIL, PASSWORD)).thenReturn(usuarioMock);

		// When
		Usuario usuarioBuscado = servicioLogin.consultarUsuario(MAIL, PASSWORD);

		// Then
		assertThat(usuarioBuscado).isEqualTo(usuarioMock);
	}

	@Test
	public void testCuandoBuscoConEmailYPasswordDeUnUsuarioInexistenteMeDevuelveNull() {
		// Given
		when(repositorioUsuario.buscarUsuario(MAIL, PASSWORD)).thenReturn(null);

		// When
		Usuario usuarioBuscado = servicioLogin.consultarUsuario(MAIL, PASSWORD);

		// Then
		assertThat(usuarioBuscado).isEqualTo(null);
	}

}
