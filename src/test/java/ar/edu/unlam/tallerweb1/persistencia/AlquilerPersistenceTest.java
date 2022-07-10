package ar.edu.unlam.tallerweb1.persistencia;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Alquiler;
import ar.edu.unlam.tallerweb1.modelo.EstadoAlquiler;
import ar.edu.unlam.tallerweb1.modelo.Locker;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public class AlquilerPersistenceTest extends SpringTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	@Transactional
	@Rollback
	public void testCrearAlquilerYValidarEstado() {
		
		Alquiler nuevoAlquiler = dadoQueExisteUnAlquiler();
		Alquiler alquilerBuscado = cuandoBuscoElAqluiler(nuevoAlquiler.getId());
		entoncesElAlquilerPoseeElEstado(alquilerBuscado.getEstadoAlquiler(), EstadoAlquiler.ACTIVO);
		
	}

	private void entoncesElAlquilerPoseeElEstado(EstadoAlquiler estadoEsperado, EstadoAlquiler estadoActivo) {
		assertThat(estadoEsperado).isEqualTo(estadoActivo);
		
	}

	private Alquiler cuandoBuscoElAqluiler(Long idAlquiler) {
		return session().get(Alquiler.class, idAlquiler);
	}

	private Alquiler dadoQueExisteUnAlquiler() {
		Locker locker = new Locker();
		Usuario usuario = new Usuario();
		Alquiler alquilerNuevo = new Alquiler();
		alquilerNuevo.setLocker(locker);
		alquilerNuevo.setUsuario(usuario);
		
		session().save(alquilerNuevo);
		return alquilerNuevo;
	}

}
