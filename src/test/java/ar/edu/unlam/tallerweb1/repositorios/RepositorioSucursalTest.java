
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
    @Autowired
    private RepositorioSucursal repositorioSucursal;
    @Test
    @Transactional @Rollback
    public void busquedaDeSucursalPorLocalidad() {
        //preparacion
        Sucursal haedo = new Sucursal();
        haedo.setLocalidad(LOCALIDAD_MODIFICADA);
        haedo.setNombre("haedo");
        session().save(haedo);
        Sucursal moron = new Sucursal();
        moron.setLocalidad("moron");
        moron.setNombre("moron");
        session().save(moron);
        //ejecucion
        List<Sucursal> resultado = repositorioSucursal.buscarPorLocalidad(LOCALIDAD_MODIFICADA);
        //validacion
        assertThat(resultado).hasSize(1);
    }

}



