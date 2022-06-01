package ar.edu.unlam.tallerweb1.controladores;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Sucursal;
import ar.edu.unlam.tallerweb1.servicios.ServicioSucursal;

public class ControladorSucursalTest {

	private ServicioSucursal servicioSucursal;
	private ControladorSucursal controladorSucursal;

	@Before
	public void setUp() throws Exception {
		servicioSucursal = mock(ServicioSucursal.class);
		controladorSucursal = new ControladorSucursal(servicioSucursal);
		
	}

	@Test
	public void testQueSeMuestreElListadoDeSucursales() {
		
		ModelAndView mav = controladorSucursal.mostrarSucursales("ramos");
		
		when(servicioSucursal.buscarSucursal("ramos")).thenReturn(new ArrayList<Sucursal>());

		List<Sucursal> lista = (List<Sucursal>) mav.getModel().get("sucursales");
		
		assertThat(lista).hasSize(0);
		
	}
	
	@Test
	public void testQueSiNoEncuentraSucursalesSeMuestreElMensajeDeError() {
		
		ModelAndView mav = controladorSucursal.mostrarSucursales("ramos");
		
		when(servicioSucursal.buscarSucursal("ramos")).thenReturn(new ArrayList<Sucursal>());
		
		assertThat(mav.getModel().get("error")).isEqualTo("No se encontraron sucursales");
		
	}

}
