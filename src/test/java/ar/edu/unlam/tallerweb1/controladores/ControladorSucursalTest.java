package ar.edu.unlam.tallerweb1.controladores;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import static org.assertj.core.api.Assertions.*;

import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Localidad;
import ar.edu.unlam.tallerweb1.modelo.Sucursal;
import ar.edu.unlam.tallerweb1.servicios.ServicioLocalidad;
import ar.edu.unlam.tallerweb1.servicios.ServicioSucursal;

public class ControladorSucursalTest {
	private ServicioSucursal servicioSucursal;
	private ControladorSucursal controladorSucursal;
	private ServicioLocalidad servicioLocalidad;
	private HttpSession session;

	@Before
	public void setUp() throws Exception {
		session = mock(HttpSession.class);
		servicioSucursal = mock(ServicioSucursal.class);
		servicioLocalidad = mock(ServicioLocalidad.class);
		controladorSucursal = new ControladorSucursal(servicioSucursal, servicioLocalidad);
	}

	@Test
	public void testQueSeMuestreElListadoDeSucursales() {
		dadoQueTengoUnaListaDeSucursales();
		ModelAndView mav = cuandoBuscoLaListaDeSucursales();
		esperoQueMeMuestreLaVistaConLasSucursales(mav);
	}
		
	private void dadoQueTengoUnaListaDeSucursales() {
		List<Sucursal> listaSucursales = new ArrayList<>();
		Sucursal A= new Sucursal();
		Sucursal B= new Sucursal();
		Sucursal C= new Sucursal();
		listaSucursales.add(A);
		listaSucursales.add(B);
		listaSucursales.add(C);

		when(servicioLocalidad.obtenerLocalidades()).thenReturn(new ArrayList<Localidad>());
		when(servicioSucursal.buscarSucursal("ramos")).thenReturn(listaSucursales);
	}

	private ModelAndView cuandoBuscoLaListaDeSucursales() {

		return controladorSucursal.mostrarSucursales("ramos", session);
	}

	private void esperoQueMeMuestreLaVistaConLasSucursales(ModelAndView mav) {
		List<Sucursal> lista = (List<Sucursal>) mav.getModel().get("sucursales");
		assertThat(lista).hasSize(3);
	}

	@Test
	public void testQueSiNoEncuentraSucursalesSeMuestreElMensajeDeError() {
		dadoQueNoTengoLaSucursal();
		ModelAndView mav = cuandoObtengoLaSucursal();
		esperoElSiguienteError(mav);
	}

	private void dadoQueNoTengoLaSucursal() {
		when(servicioSucursal.buscarSucursal("ramos")).thenReturn(new ArrayList<Sucursal>());
	}

	private ModelAndView cuandoObtengoLaSucursal() {
		return controladorSucursal.mostrarSucursales("ramos", session);
	}

	private void esperoElSiguienteError(ModelAndView mav) {
		assertThat(mav.getModel().get("error")).isEqualTo("No se encontraron sucursales. Busque por otra localidad");
	}

}
