package ar.edu.unlam.tallerweb1.persistencia;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Codigo;

public class CodigoPersistenceTest extends SpringTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	@Transactional
	@Rollback
	public void testCrearCodigoYAsignarFechaVencimiento() {
		
		Codigo codigo = new Codigo();
		session().save(codigo);
		
		Codigo codigoBuscado = session().get(Codigo.class, codigo.getId());
		
		assertThat(codigoBuscado.getFechaVencimiento()).isAfter(new Date(Calendar.getInstance().getTimeInMillis()));
	}
	

}
