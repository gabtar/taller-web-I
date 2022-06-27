package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Sucursal;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioSucursal;
import org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
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
    private String localidad = "haedo";

    @Before
    public void init(){
        repositorioSucursal = mock(RepositorioSucursal.class);
        servicioSucursal= new ServicioSucursalImpl(repositorioSucursal);
    }

    @Test
    public void elServicioRecibeSucursalesDesdeElREpositorio(){
        //preparacion
        dadoQueTengoUnaListaDeSucursales();
        // ejecucion
        List <Sucursal> listaEsperada = cuandoObtengoLaListaDeSucursales();
        //comparacion

        esperoQueListeLasSucursales(listaEsperada);
    }

    private void dadoQueTengoUnaListaDeSucursales() {
        List <Sucursal> lista = new ArrayList<>();
        Sucursal a = new Sucursal();
        lista.add(a);
        when(repositorioSucursal.listarSucursales()).thenReturn(lista);
    }

    private List<Sucursal> cuandoObtengoLaListaDeSucursales() {
        return servicioSucursal.listarSucursales();
    }

    private void esperoQueListeLasSucursales(List<Sucursal> listaEsperada) {
        assertThat(listaEsperada.size()).isEqualTo(1);
    }

    @Test
    public void queSePuedaBuscarLaSucursalPorLocalidad() {
        dadoQueTengoLaSucursalesDeHaedo();
        List<Sucursal> listaEsperada = cuandoBuscoLasSucursalesPorLocalidad(localidad);
        esperoQueMeDevuelvaLasSucursalesPorLaLocalidad(listaEsperada);
    }

    private void dadoQueTengoLaSucursalesDeHaedo() {
        List <Sucursal> lista = new ArrayList<>();
        Sucursal a = new Sucursal();
        Sucursal b = new Sucursal();
        a.setLocalidad(localidad);
        b.setLocalidad(localidad);
        lista.add(a);
        lista.add(b);
        when(repositorioSucursal.buscarPorLocalidad(localidad)).thenReturn(lista);
    }

    private List<Sucursal> cuandoBuscoLasSucursalesPorLocalidad(String localidad) {
        return servicioSucursal.buscarSucursal(localidad);
    }

    private void esperoQueMeDevuelvaLasSucursalesPorLaLocalidad(List<Sucursal> listaEsperada) {
        assertThat(listaEsperada.size()).isEqualTo(2);
    }



}

