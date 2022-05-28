package ar.edu.unlam.tallerweb1.controladores;

import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.servicios.ServicioRegistro;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ControladorRegistroTest {
	
	private DatosRegistro datosRegistro;
	private ControladorRegistro controladorRegistro;
	private ServicioRegistro servicioRegistro;

	@Before
	public void setUp() throws Exception {
		// datosRegistro = mock(DatosRegistro.class);
		datosRegistro = new DatosRegistro();
		servicioRegistro = mock(ServicioRegistro.class);
		controladorRegistro = new ControladorRegistro(servicioRegistro);
	}

	@Test
	public void testQueSeAccederALaPaginaDeRegistro() {
		
		// When
		ModelAndView mav = controladorRegistro.irAlRegistro(datosRegistro);
		
		// Then
		assertThat(mav.getViewName()).isEqualTo("registro");
	}
	
	@Test
	public void testQueSePuedaRegistrarUnUsuario() {
		// Given - Datos correctos del registro
		datosRegistro.setEmail("mail@mail.com");
		datosRegistro.setContrasenia("1234");
		datosRegistro.setRepetirContrasenia("1234");
		
		// When 
		ModelAndView mav = controladorRegistro.registrarNuevoUsuario(datosRegistro);
		
		// Then
		assertThat(mav.getViewName()).isEqualTo("login");
	}
	
	@Test
	public void testSiLasClavesNoCoincidenMuestraLaPaginaDeRegistroConMensajeDeError() {
		// Given - no coinciden las claves
		datosRegistro.setContrasenia("1234");
		datosRegistro.setRepetirContrasenia("1111");
		
		String error = "Las claves no coinciden";
		
		// When
		ModelAndView mav = controladorRegistro.registrarNuevoUsuario(datosRegistro);
		
		// Then
		assertThat(mav.getViewName()).isEqualTo("registro");
		assertThat(mav.getModel().get("error")).isEqualTo(error);
	}

}
