
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

<<<<<<< HEAD
    private static final String LOCALIDAD_MODIFICADA = "haedo";
    private static final String LOCALIDAD_ERRONEA = "error";
    @Autowired
    private RepositorioSucursal repositorioSucursal;
    @Test
    @Transactional @Rollback
    public void busquedaDeSucursalPorLocalidad() {
        dadoQueTengoUnaListaDeSucursales();
        //ejecucion
        List<Sucursal> lista = cuandoBuscoLaSucursalPorLocalidad();
        //validacion
        esperoObtenerLasSucursalesPorLocalidad(lista);
    }

    private List<Sucursal> cuandoBuscoLaSucursalPorLocalidad() {
        return repositorioSucursal.buscarPorLocalidad(LOCALIDAD_MODIFICADA);
    }

    private void esperoObtenerLasSucursalesPorLocalidad(List<Sucursal> lista) {
        assertThat(lista).hasSize(1);
    }

    @Test
    @Transactional @Rollback
    public void queSiNoEncentraLocalidadesDevuelvaLaListaVacias() {
        //preparacion
        dadoQueTengoUnaListaDeSucursales();
        
        //ejecucion
        List<Sucursal> localidadesEncontradas = cuandoBuscoPorLocalidadNoExistente();
        
        //validacion
        esperoUnaListaVacia(localidadesEncontradas);
    }
=======
	private static final String LOCALIDAD_MODIFICADA = "haedo";
	private static final String LOCALIDAD_ERRONEA = "error";
	private static final Long ID_SUCURSAL_HAEDO = 1L;
>>>>>>> c052e64bbea9e5d7975a9c72d9a5c3f3fd69c023

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
		sucHaedo.setId(ID_SUCURSAL_HAEDO);
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
	
	@Test
	@Transactional
	@Rollback
	public void testQueSePuedaBuscarUnaSucursalPorId() {
		Sucursal sucursalEsperada = dadoQueTengoUnaSucursalConId(ID_SUCURSAL_HAEDO);
		
		Sucursal sucursalObtenida = cuandoBuscoUnaSucursalPorId(sucursalEsperada.getId());
		
		encuentroLaSucursalConEseId(sucursalEsperada, sucursalObtenida);
	}

	private void encuentroLaSucursalConEseId(Sucursal sucursalEsperada, Sucursal sucursalObtenida) {
		assertThat(sucursalEsperada).isEqualTo(sucursalObtenida);
	}

	private Sucursal cuandoBuscoUnaSucursalPorId(Long idSucursalHaedo) {
		return repositorioSucursal.buscarSucursalPorId(idSucursalHaedo);
	}

	private Sucursal dadoQueTengoUnaSucursalConId(Long idSucursalHaedo) {
		Localidad haedo = new Localidad();
		haedo.setNombre("Haedo");
		session().save(haedo);
		
		Sucursal sucHaedo = new Sucursal();
		sucHaedo.setLocalidad(haedo);
		sucHaedo.setNombre("Lockers Haedo");
		sucHaedo.setId(ID_SUCURSAL_HAEDO);
		session().save(sucHaedo);
		
		return sucHaedo;
	}

}
