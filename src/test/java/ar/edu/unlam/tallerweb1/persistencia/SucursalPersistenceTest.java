package ar.edu.unlam.tallerweb1.persistencia;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Localidad;
import ar.edu.unlam.tallerweb1.modelo.Sucursal;

public class SucursalPersistenceTest extends SpringTest {

	@Test
	@Transactional
	@Rollback
	public void testQueSeCreeUnaSucursalYSeBusquePorNombre() {
		Localidad ramos = new Localidad();
		ramos.setNombre("ramos");
		session().save(ramos);

		Sucursal sucRamos = new Sucursal();
		sucRamos.setLocalidad(ramos);
		sucRamos.setNombre("Ramos Lockers");
		session().save(sucRamos);

		Sucursal sucursalBuscada = cuandoBuscoLaSucursal(sucRamos.getId());

		assertThat(sucursalBuscada).isEqualTo(sucRamos);
		assertThat(sucursalBuscada.getLocalidad()).isEqualTo(ramos);
		assertThat(sucursalBuscada.getNombre()).isEqualTo(sucRamos.getNombre());
	}
	
	@Test
	@Transactional
	@Rollback
	public void testQueSeCreeUnaSucursalYObtengaLatitudYLongitud() {
		
		Sucursal sucRamos = dadoQueExisteUnaSucursalConLatitudYLongitud();
		
		Sucursal sucursalBuscada = cuandoBuscoLaSucursal(sucRamos.getId());
		
		entoncesLaSucursalPosee(sucursalBuscada.getLatitud(), sucRamos.getLatitud());
		entoncesLaSucursalPosee(sucursalBuscada.getLongitud(), sucRamos.getLongitud());
	}

	private void entoncesLaSucursalPosee(Double valorObtenido, Double valorEsperado) {
		assertThat(valorEsperado).isEqualTo(valorObtenido);
	}

	private Sucursal cuandoBuscoLaSucursal(Long sucursalId) {
		return session().get(Sucursal.class, sucursalId);
	}

	private Sucursal dadoQueExisteUnaSucursalConLatitudYLongitud() {
		Localidad ramos = new Localidad();
		ramos.setNombre("ramos");
		session().save(ramos);

		Sucursal sucRamos = new Sucursal();
		sucRamos.setLocalidad(ramos);
		sucRamos.setNombre("Ramos Lockers");
		sucRamos.setLatitud(0.0);
		sucRamos.setLongitud(0.0);
		session().save(sucRamos);
		return sucRamos;
	}

}
