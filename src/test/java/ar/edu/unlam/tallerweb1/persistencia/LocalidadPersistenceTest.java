package ar.edu.unlam.tallerweb1.persistencia;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Localidad;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public class LocalidadPersistenceTest extends SpringTest {

	@Test
	@Transactional
	@Rollback
	public void testCrearLocalidadYBuscarPorNombre() {
		Localidad moron = new Localidad();
		moron.setId(1L);
		moron.setNombre("Moron");
		session().save(moron);

		Localidad ramos = new Localidad();
		ramos.setId(2L);
		ramos.setNombre("Ramos");
		session().save(ramos);

		Localidad localidadBuscada = session().get(Localidad.class, moron.getId());

		assertThat(localidadBuscada.getNombre()).isEqualTo(moron.getNombre());
	}

}
