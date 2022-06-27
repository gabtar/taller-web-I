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
<<<<<<< HEAD
=======
	private ServicioLocalidad servicioLocalidad;
	private HttpSession session;

>>>>>>> c052e64bbea9e5d7975a9c72d9a5c3f3fd69c023
	@Before
	public void setUp() throws Exception {
		session = mock(HttpSession.class);
		servicioSucursal = mock(ServicioSucursal.class);
<<<<<<< HEAD
		controladorSucursal = new ControladorSucursal(servicioSucursal);
=======
		servicioLocalidad = mock(ServicioLocalidad.class);
		controladorSucursal = new ControladorSucursal(servicioSucursal, servicioLocalidad);
>>>>>>> c052e64bbea9e5d7975a9c72d9a5c3f3fd69c023
	}

	@Test
	public void testQueSeMuestreElListadoDeSucursales() {
<<<<<<< HEAD
		dadoQueTengoUnaListaDeSucursales();
		ModelAndView mav = cuandoBuscoLaListaDeSucursales();
		esperoQueMeMuestreLaVistaConLasSucursales(mav);
	}
=======

		when(servicioLocalidad.obtenerLocalidades()).thenReturn(new ArrayList<Localidad>());
		when(servicioSucursal.buscarSucursal("ramos")).thenReturn(new ArrayList<Sucursal>());
		ModelAndView mav = controladorSucursal.mostrarSucursales("ramos", session);
>>>>>>> c052e64bbea9e5d7975a9c72d9a5c3f3fd69c023

	private void dadoQueTengoUnaListaDeSucursales() {
		List<Sucursal> listaSucursales = new ArrayList<>();
		Sucursal A= new Sucursal();
		Sucursal B= new Sucursal();
		Sucursal C= new Sucursal();
		listaSucursales.add(A);
		listaSucursales.add(B);
		listaSucursales.add(C);

		when(servicioSucursal.buscarSucursal("ramos")).thenReturn(listaSucursales);
	}

	private ModelAndView cuandoBuscoLaListaDeSucursales() {

		return controladorSucursal.mostrarSucursales("ramos");
	}

	private void esperoQueMeMuestreLaVistaConLasSucursales(ModelAndView mav) {
		List<Sucursal> lista = (List<Sucursal>) mav.getModel().get("sucursales");
<<<<<<< HEAD
		assertThat(lista).hasSize(3);
=======

		assertThat(lista).hasSize(0);

>>>>>>> c052e64bbea9e5d7975a9c72d9a5c3f3fd69c023
	}

	@Test
	public void testQueSiNoEncuentraSucursalesSeMuestreElMensajeDeError() {
<<<<<<< HEAD
		dadoQueNoTengoLaSucursal();
		ModelAndView mav = cuandoObtengoLaSucursal();
		esperoElSiguienteError(mav);
	}

	private void dadoQueNoTengoLaSucursal() {
		when(servicioSucursal.buscarSucursal("ramos")).thenReturn(new ArrayList<Sucursal>());
	}

	private ModelAndView cuandoObtengoLaSucursal() {
		return controladorSucursal.mostrarSucursales("ramos");
	}

	private void esperoElSiguienteError(ModelAndView mav) {
		assertThat(mav.getModel().get("error")).isEqualTo("No se encontraron sucursales");
=======

		when(servicioLocalidad.obtenerLocalidades()).thenReturn(new ArrayList<Localidad>());
		when(servicioSucursal.buscarSucursal("ramos")).thenReturn(new ArrayList<Sucursal>());
		ModelAndView mav = controladorSucursal.mostrarSucursales("ramos", session);

		assertThat(mav.getModel().get("error")).isEqualTo("No se encontraron sucursales. Busque por otra localidad");

>>>>>>> c052e64bbea9e5d7975a9c72d9a5c3f3fd69c023
	}

}
