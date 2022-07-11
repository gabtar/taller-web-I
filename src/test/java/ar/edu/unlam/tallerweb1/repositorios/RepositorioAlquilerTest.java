package ar.edu.unlam.tallerweb1.repositorios;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Alquiler;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public class RepositorioAlquilerTest extends SpringTest {

	private static final Integer NUMERO_DE_ALQUILERES = 2;
	
	@Autowired
	private RepositorioAlquiler repositorioAlquiler;

	@Test
	@Transactional
	@Rollback
	public void testQueSePuedaCrearUnNuevoAlquiler() {
		Alquiler alquilerNuevo = dadoQueExisteUnAlquilerNuevo();
		
		cuandoAgregarElNuevoAlquilerAlquiler(alquilerNuevo);
		
		entoncesExisteElAlquiler(alquilerNuevo);
		
	}

	private void entoncesExisteElAlquiler(Alquiler alquiler) {
		Alquiler buscado = session().get(Alquiler.class, alquiler.getId());
		assertThat(buscado).isEqualTo(alquiler);
	}

	private void cuandoAgregarElNuevoAlquilerAlquiler(Alquiler alquilerNuevo) {
		repositorioAlquiler.nuevoAlquiler(alquilerNuevo);
	}

	private Alquiler dadoQueExisteUnAlquilerNuevo() {
		return new Alquiler();
	}
	
	@Test
	@Transactional
	@Rollback
	public void testQueSePuedaModificarUnAlquiler() {
		Alquiler alquiler = dadoQueExisteUnAlquiler();
		
		cuandoModificoElAlquiler(alquiler);
		
		entoncesElAlquilerQuedaModificado(alquiler);
	}

	private void entoncesElAlquilerQuedaModificado(Alquiler alquiler) {
		Alquiler buscado = session().get(Alquiler.class, alquiler.getId());
		assertThat(buscado.getFechaFinalizacion()).isNotNull();
	}

	private void cuandoModificoElAlquiler(Alquiler alquiler) {
		alquiler.setFechaFinalizacion(new Date(Calendar.getInstance().getTimeInMillis()));
		repositorioAlquiler.modificar(alquiler);
	}

	private Alquiler dadoQueExisteUnAlquiler() {
		Alquiler alquiler = new Alquiler();
		session().save(alquiler);
		
		return alquiler;
	}

	@Test
	@Transactional
	@Rollback
	public void testQueSeObtenerLosAlquileresDeUnUsuario() {
		Usuario usuario = dadoQueUnUsuarioTieneAlquileres(NUMERO_DE_ALQUILERES);
		List<Alquiler> alquileres = cuandoPidoLosAlquileresDelUsuario(usuario.getId());
		entoncesObtengoAlquileres(alquileres.size(), NUMERO_DE_ALQUILERES);
	}

	private void entoncesObtengoAlquileres(Integer alquileresObtenidos, Integer numeroDeAlquileres) {
		assertThat(alquileresObtenidos).isEqualTo(numeroDeAlquileres);
	}

	private List<Alquiler> cuandoPidoLosAlquileresDelUsuario(Long usuarioId) {
		return repositorioAlquiler.listarAlquileresDelUsuario(usuarioId);
	}

	private Usuario dadoQueUnUsuarioTieneAlquileres(Integer numeroDeAlquileres) {
		Usuario usuario = new Usuario();
		session().save(usuario);
		
		for (int i = 0; i < numeroDeAlquileres; i++) {
			Alquiler alquilerNuevo = new Alquiler();
			alquilerNuevo.setUsuario(usuario);
			session().save(alquilerNuevo);
		}
		
		return usuario;
	}

}
