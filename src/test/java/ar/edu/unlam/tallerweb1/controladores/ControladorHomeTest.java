package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Sucursal;
import ar.edu.unlam.tallerweb1.servicios.ServicioSucursal;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ControladorHomeTest {
    // se tienen que cargar los controladores y los servicios para poder usarlos en los test

    ServicioSucursal servicioSucursal;
    ControladorHome controladorHome;


    // before carga los recursos o clases una sola vez antes de cada test, lo reutiliza antes de cada Test
    @Before
    public void init(){
        servicioSucursal = Mockito.mock(ServicioSucursal.class);
        controladorHome = new ControladorHome(servicioSucursal);
    }

    @Test
    public void cuandoSeCargaInicioSeCargaListaDeSucursales(){
        dadoQueTengoUnaListaDeSucursales();
        ModelAndView model = cuandoBuscoLaListaDeSucursales();
        esperoQueMeMuestreLaListaDeSucursales(model);
    }

    private void dadoQueTengoUnaListaDeSucursales() {
        List<Sucursal> listaSucursales = new ArrayList<>();
        Sucursal A= new Sucursal();
        Sucursal B= new Sucursal();
        Sucursal C= new Sucursal();
        listaSucursales.add(A);
        listaSucursales.add(B);
        listaSucursales.add(C);

        when(servicioSucursal.listarSucursales()).thenReturn(listaSucursales);
    }

    private ModelAndView cuandoBuscoLaListaDeSucursales() {
        return controladorHome.inicio();
    }

    private void esperoQueMeMuestreLaListaDeSucursales(ModelAndView model) {
        List <Sucursal>listaRegresada = new ArrayList<Sucursal>();
        listaRegresada= (List<Sucursal>) model.getModelMap().get("listaSucursales");
        assertThat(model.getViewName()).isEqualTo("home");
        assertThat(listaRegresada.size()).isEqualTo(3);
    }

    @Test
    public void cuandoSeCargaElInicioSeLLamaAlServicioUnaVEz(){
        ModelAndView modelo = controladorHome.inicio();
        verify(servicioSucursal, times(1)).listarSucursales();
    }
}
