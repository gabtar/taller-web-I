package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Sucursal;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioSucursal;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class ServicioSucursalTest {
    private ServicioSucursal servicioSucursal;
    private RepositorioSucursal repositorioSucursal;

    @Before
    public void init(){
        repositorioSucursal = mock(RepositorioSucursal.class);
        servicioSucursal= new ServicioSucursalImpl(repositorioSucursal);
    }

    /*
    @Test
    public void elServicioRegresaListaDeSucuralesNull(){
        List <Sucursal> lista = null;
        when(repositorioSucursal.listarSucursales()).thenReturn(lista);
        servicioSucursal= new ServicioSucursalImpl(repositorioSucursal);
        List <Sucursal> listaEsperada = servicioSucursal.listarSucursales();
        Assertions.assertThat(listaEsperada).isNull();
    }*/
    @Test
    public void elServicioRecibeSucursalesDesdeElREpositorio(){
        //preparacion
        List <Sucursal> lista = new ArrayList<Sucursal>();
        Sucursal a = new Sucursal();
        lista.add(a);
        when(repositorioSucursal.listarSucursales()).thenReturn(lista);
        // ejecucion
        servicioSucursal= new ServicioSucursalImpl(repositorioSucursal);
        List <Sucursal> listaEsperada = servicioSucursal.listarSucursales();
        //comparacion
        Assertions.assertThat(listaEsperada.size()).isEqualTo(1);
    }
    @Test
    public void elServicioCambiaLaListaDeNullAVacia(){
        List <Sucursal> lista=null;
        when(repositorioSucursal.listarSucursales()).thenReturn(null);
        servicioSucursal= new ServicioSucursalImpl(repositorioSucursal);
       List <Sucursal> listaEsperada= servicioSucursal.listarSucursales();
        Assertions.assertThat(listaEsperada.isEmpty()).isTrue();
    }
    
    @Test
    public void testQueSePuedaBuscarSucursalPorId() {
    	servicioSucursal.buscarSucursalPorId(1L);
    	verify(repositorioSucursal, times(1)).buscarSucursalPorId(any(Long.class));
    }

}

