
package ar.edu.unlam.tallerweb1.repositorios;
import ar.edu.unlam.tallerweb1.SpringTest;
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

	private void esperoUnaListaVacia(List<Sucursal> resultado) {
		// TODO Auto-generated method stub
		assertThat(resultado).hasSize(0);
		
	}

	private List<Sucursal> cuandoBuscoPorLocalidadNoExistente() {
		// TODO Auto-generated method stub
		List<Sucursal> resultado = repositorioSucursal.buscarPorLocalidad(LOCALIDAD_ERRONEA);
		return resultado;
	}

	private void dadoQueTengoUnaListaDeSucursales() {
		// TODO Auto-generated method stub
		Sucursal haedo = new Sucursal();
        haedo.setLocalidad(LOCALIDAD_MODIFICADA);
        haedo.setNombre("haedo");
        session().save(haedo);
        Sucursal moron = new Sucursal();
        moron.setLocalidad("moron");
        moron.setNombre("moron");
        session().save(moron);
	}

}



