package ar.edu.unlam.tallerweb1.persistencia;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public class UsuarioPersistenceTest extends SpringTest {

	private static final String MAIL_MODIFICADO = "mailModificado@mail.com";
	private static final String MAIL = "mail@mail.com";

	@Before
	public void setUp() throws Exception {
	}

	@Test @Transactional @Rollback
	public void testCrearUnUsuarioYBuscarPorMail() {
		// Given
		Usuario nuevoUsuario = new Usuario();
		nuevoUsuario.setEmail(MAIL);
		nuevoUsuario.setActivo(true);
		
		session().save(nuevoUsuario);
		
		// When
		Usuario usuarioBuscado = session().get(Usuario.class, nuevoUsuario.getId());
		
		// Then
		assertThat(usuarioBuscado).isEqualTo(nuevoUsuario);
		assertThat(usuarioBuscado.getActivo()).isEqualTo(nuevoUsuario.getActivo());
		assertThat(usuarioBuscado.getPassword()).isEqualTo(nuevoUsuario.getPassword());
	}
	
	@Test @Transactional @Rollback
	public void testModificarUnUsuarioYBuscarPorId() {
		// Given
		Usuario nuevoUsuario = new Usuario();
		nuevoUsuario.setEmail(MAIL);
		
		session().save(nuevoUsuario);
		
		// Then
		nuevoUsuario.setEmail(MAIL_MODIFICADO);
		session().save(nuevoUsuario);
		
		Usuario usuarioBuscado = session().get(Usuario.class, nuevoUsuario.getId());
		
		// When
		assertThat(usuarioBuscado.getEmail()).isEqualTo(MAIL_MODIFICADO);
	}
	
	@Test @Transactional @Rollback
	public void testActivarUnUsuario() {
		// Given
		Usuario nuevoUsuario = new Usuario();
		nuevoUsuario.setEmail(MAIL);
		nuevoUsuario.setId(1L);
		nuevoUsuario.activar();
		
		session().save(nuevoUsuario);
		
		// Then
		Usuario usuarioBuscado = session().get(Usuario.class, nuevoUsuario.getId());
		
		// When
		assertThat(usuarioBuscado.getActivo()).isTrue();
		assertThat(usuarioBuscado.activo()).isTrue();
	}

}
