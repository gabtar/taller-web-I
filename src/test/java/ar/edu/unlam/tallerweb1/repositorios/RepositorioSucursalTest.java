
package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Localidad;
import ar.edu.unlam.tallerweb1.modelo.Sucursal;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.*;
import java.util.List;

public class RepositorioSucursalTest extends SpringTest {

	private static final String LOCALIDAD_MODIFICADA = "haedo";
	private static final String LOCALIDAD_ERRONEA = "error";

	@Autowired
	private RepositorioSucursal repositorioSucursal;

	@Test
	@Transactional
	@Rollback
	public void busquedaDeSucursalPorLocalidad() {

		dadoQueTengoUnaListaDeSucursales();

		List<Sucursal> resultado = repositorioSucursal.buscarPorLocalidad(LOCALIDAD_MODIFICADA);

		assertThat(resultado).hasSize(1);
	}

	private void dadoQueTengoUnaListaDeSucursales() {
		Localidad haedo = new Localidad();
		haedo.setNombre("Haedo");
		session().save(haedo);
		Localidad moron = new Localidad();
		moron.setNombre("Moron");
		session().save(moron);

		Sucursal sucHaedo = new Sucursal();
		sucHaedo.setLocalidad(haedo);
		sucHaedo.setNombre("Lockers Haedo");
		session().save(sucHaedo);

		Sucursal sucMoron = new Sucursal();
		sucMoron.setLocalidad(moron);
		sucMoron.setNombre("Lockers Morón");
		session().save(sucMoron);
	}

	@Test
	@Transactional
	@Rollback
	public void queSiNoEncentraLocalidadesDevuelvaLaListaVacias() {
		// preparacion
		dadoQueTengoUnaListaDeSucursales();

		// ejecucion
		List<Sucursal> localidadesEncontradas = cuandoBuscoPorLocalidadNoExistente();

		// validacion
		esperoUnaListaVacia(localidadesEncontradas);
	}

	private void esperoUnaListaVacia(List<Sucursal> resultado) {
		assertThat(resultado).hasSize(0);

	}

	private List<Sucursal> cuandoBuscoPorLocalidadNoExistente() {
		List<Sucursal> resultado = repositorioSucursal.buscarPorLocalidad(LOCALIDAD_ERRONEA);
		return resultado;
	}

}
