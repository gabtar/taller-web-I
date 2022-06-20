package ar.edu.unlam.tallerweb1.repositorios;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

import ar.edu.unlam.tallerweb1.modelo.Localidad;
import ar.edu.unlam.tallerweb1.SpringTest;

public class RepositorioLocalidadTest extends SpringTest {

	@Autowired
	private RepositorioLocalidad repositorioLocalidad;

	@Test
	@Transactional
	@Rollback
	public void testListarTodasLasLocalidades() {
		dadoQueExistenLocalidades();

		List<Localidad> localidades = cuandoPidoTodasLasLocalidades();

		entoncesEncuentroUnaListaCon(localidades.size(), 3);
	}


	private void entoncesEncuentroUnaListaCon(Integer cantidadObtenida, Integer cantidadEsperada) {
		assertThat(cantidadObtenida).isEqualTo(cantidadEsperada);
	}


	private List<Localidad> cuandoPidoTodasLasLocalidades() {
		return this.repositorioLocalidad.listarLocalidades();
	}

	private void dadoQueExistenLocalidades() {
		Localidad ramos = new Localidad();
		ramos.setNombre("Ramos");
		session().save(ramos);

		Localidad moron = new Localidad();
		moron.setNombre("Morón");
		session().save(moron);

		Localidad ciudadela = new Localidad();
		ciudadela.setNombre("Ciudadela");
		session().save(ciudadela);
	}

}
