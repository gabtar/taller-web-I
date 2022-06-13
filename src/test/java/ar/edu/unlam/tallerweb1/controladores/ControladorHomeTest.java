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
        //preparacion
        List<Sucursal> listaSucursales = new ArrayList<>();
        Sucursal A= new Sucursal();
        Sucursal B= new Sucursal();
        Sucursal C= new Sucursal();
        listaSucursales.add(A);
        listaSucursales.add(B);
        listaSucursales.add(C);
        List <Sucursal>listaRegresada = new ArrayList<Sucursal>();
        // ejecucion

        Mockito.when(servicioSucursal.listarSucursales()).thenReturn(listaSucursales);
        ModelAndView model =controladorHome.inicio();
        listaRegresada= (List<Sucursal>) model.getModelMap().get("listaSucursales");

        //testeo
        assertThat(model.getViewName()).isEqualTo("home");
        assertThat(listaRegresada.size()).isEqualTo(3);
    }
    /*   esto se tiene que cambiar para que el metodo cheequee el la exepcion
    @Test
    public void cuandoSeCargaElInicioSiFallaSeTiraExcepcion(){
        when(servicioSucursal.listarSucursales()).thenThrow(new RuntimeException());
        ModelAndView modelo = ControladorHome.inicio();
        assertThat(modelo.getViewName()).isEqualTo("home");

    }*/
    @Test
    public void cuandoSeCargaElInicioSeLLamaAlServicioUnaVEz(){
        ModelAndView modelo = controladorHome.inicio();
        verify(servicioSucursal, times(1)).listarSucursales();

    }
}
