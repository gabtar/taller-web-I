package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Locker;
import ar.edu.unlam.tallerweb1.modelo.Sucursal;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioAlquiler;
import ar.edu.unlam.tallerweb1.servicios.ServicioSucursal;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;



public class ControladorAlquilerTest {
    private ServicioAlquiler servicioAlquiler;
    private ServicioSucursal servicioSucursal;
    private ControladorAlquiler controladorAlquiler;

    HttpServletRequest request;

    @Before
    public void setUp() throws Exception {
        servicioAlquiler = mock(ServicioAlquiler.class);
        servicioSucursal = mock(ServicioSucursal.class);
        request = mock(HttpServletRequest.class);
        controladorAlquiler = new ControladorAlquiler(servicioAlquiler,servicioSucursal);
    }

    @Test
    public void queSePuedaAlquilarUnLocker() {
        Locker locker=new Locker();
        Usuario usuario=new Usuario();
        when(servicioAlquiler.alquilarLocker(locker,usuario)).thenReturn(true);
        ModelAndView mav = controladorAlquiler.alquilarLocker(locker,usuario);
        String error="Alquiler exitoso";
        assertThat("lista-alquileres").isEqualTo(mav.getViewName());
        assertThat(mav.getModel().get("error")).isEqualTo(error);
    }

    @Test
    public void queNoSePuedaAlquilarUnLockerYaAlquilado() {
        Locker locker=new Locker();
        Usuario usuario=new Usuario();
        when(servicioAlquiler.alquilarLocker(locker,usuario)).thenReturn(false);
        ModelAndView mav = controladorAlquiler.alquilarLocker(locker,usuario);
        String error="Locker no disponible";
        assertThat("lista-alquileres").isEqualTo(mav.getViewName());
        assertThat(mav.getModel().get("error")).isEqualTo(error);
    }

    @Test
    public void queMustreLosAlquileresDisponibles(){
        //preparacion
        List<Locker> listaLockers = new ArrayList<>();
        Locker A= new Locker();
        Locker B= new Locker();
        Locker C= new Locker();
        listaLockers.add(A);
        listaLockers.add(B);
        listaLockers.add(C);
        List <Locker>listaRegresada = new ArrayList<Locker>();

        // ejecucion
        Mockito.when(servicioAlquiler.buscarAlquileresDisponibles()).thenReturn(listaLockers);
        ModelAndView model = controladorAlquiler.mostrarAlquileresDisponibles(request);
        listaRegresada = (List<Locker>) model.getModelMap().get("listaAlquileres");

        //testeo
        assertThat(model.getViewName()).isEqualTo("lista-alquileres");
        assertThat(listaRegresada.size()).isEqualTo(3);
    }
}
