package ar.edu.unlam.tallerweb1.repositorios;

import static org.junit.Assert.*;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public class RepositorioUsuarioTest extends SpringTest {
	
	private static final String MAIL_MODIFICADO = "nuevoEmail@mail.com";
	private static final String PASSWORD = "1234";
	private static final String MAIL = "mail@mail.com";
	
	@Autowired
	private RepositorioUsuario repositorioUsuarios;

	@Test @Transactional @Rollback
	public void testQueSePuedaBuscarUnUsuarioExistentePorEmailYPassword() {
		// Given
		Usuario user1 = new Usuario();
		user1.setEmail(MAIL);
		user1.setPassword(PASSWORD);
		user1.setActivo(true);
		
		session().save(user1);
		
		// Then
		Usuario usuarioBuscado = repositorioUsuarios.buscarUsuario(MAIL, PASSWORD);
		
		// When
		assertThat(usuarioBuscado).isEqualTo(user1);
	}
	
	@Test @Transactional @Rollback
	public void testQueSiNoExisteElUsuarioConMailYContraseniaPasadosDevuelvaNull() {
		// Given -> Usuario usuarioEsperado = null;

		// Then
		Usuario usuarioBuscado = repositorioUsuarios.buscarUsuario(MAIL, PASSWORD);
		
		// When
		assertThat(usuarioBuscado).isNull();
	}
	
	@Test @Transactional @Rollback
	public void testQueSePuedaGuardarUnUsuario() {
		// Given
		Usuario usuarioAGuardar = new Usuario();
		usuarioAGuardar.setEmail(MAIL);
		usuarioAGuardar.setPassword(PASSWORD);
		
		// When
		repositorioUsuarios.guardar(usuarioAGuardar);
		
		// Con createCriteria
		List usuarios = session().createCriteria(Usuario.class)
				.add(Restrictions.eq("email", MAIL))
				.add(Restrictions.eq("password", PASSWORD))
				.list();
		
		// con HQL (Hibernate Query Languaje)
		List usuariosHQL = session().createQuery("FROM Usuario WHERE email = :email")
				.setParameter("email", MAIL)
				.list();
		
		// Then
		assertThat(usuarios).hasSize(1);
		assertThat(usuariosHQL).hasSize(1);
	}
	
	@Test @Transactional @Rollback
	public void testQueSePuedaBuscarUnUsuarioPorEmail() {
		// Given
		Usuario user1 = new Usuario();
		user1.setEmail(MAIL);
		user1.setPassword(PASSWORD);
		
		session().save(user1);
		
		// When
		Usuario usuarioBuscado = repositorioUsuarios.buscar(MAIL);
		
		// Then
		assertThat(usuarioBuscado).isEqualTo(user1);
	}
	
	@Test @Transactional @Rollback
	public void testQueSePuedaModificarUnUsuarioPorEmail() {
		// Given
		Usuario user1 = new Usuario();
		user1.setEmail(MAIL);
		user1.setPassword(PASSWORD);
		
		session().save(user1);
		
		// When
		user1.setEmail(MAIL_MODIFICADO);
		repositorioUsuarios.modificar(user1);
		
		Usuario usuarioModificado = repositorioUsuarios.buscar(MAIL_MODIFICADO);
		
		// Then
		assertThat(usuarioModificado.getEmail()).isEqualTo(MAIL_MODIFICADO);
	}
	
	@Test
	@Transactional
	@Rollback
	public void testQueSePuedaBuscarUnUsuarioPorId() {
		Usuario usuarioExistente = dadoQueExisteUnUsuarioConId();
		Usuario usuarioEncontrado = cuandoBuscoElUsuarioPorId(usuarioExistente.getId());
		entoncesEncuentroAlUsuario(usuarioEncontrado, usuarioExistente);
	}

	private void entoncesEncuentroAlUsuario(Usuario usuarioEncontrado, Usuario usuarioExistente) {
		assertThat(usuarioEncontrado).isEqualTo(usuarioExistente);
		assertThat(usuarioEncontrado.getId()).isEqualTo(usuarioExistente.getId());
	}

	private Usuario cuandoBuscoElUsuarioPorId(Long usuarioId) {
		return repositorioUsuarios.buscarUsuarioPorId(usuarioId);
	}

	private Usuario dadoQueExisteUnUsuarioConId() {
		Usuario user1 = new Usuario();
		user1.setEmail(MAIL);
		user1.setPassword(PASSWORD);
		user1.setActivo(true);
		
		session().save(user1);
		return user1;
	}

}
