package ar.edu.unlam.tallerweb1.persistencia;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Tamanio;

public class TamanioPersistenceTest extends SpringTest {

	private static final String TAMANIO_CHICO = "50x50x40";

	@Test
	@Transactional
	@Rollback
	public void testCrearUnTamanioYBuscarPorTamanio() {
		Tamanio tamanioChico = new Tamanio();
		tamanioChico.setTamanio(TAMANIO_CHICO);
		tamanioChico.setId(1L);
		
		session().save(tamanioChico);
		
		Tamanio tamanioBuscado = session().get(Tamanio.class, tamanioChico.getId());
		
		assertThat(tamanioBuscado).isEqualTo(tamanioChico);
		assertThat(tamanioBuscado.getTamanio()).isEqualTo(tamanioChico.getTamanio());
	}

}
