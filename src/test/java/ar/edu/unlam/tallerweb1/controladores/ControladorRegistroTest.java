package ar.edu.unlam.tallerweb1.controladores;

import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.DatosRegistro;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioEmail;
import ar.edu.unlam.tallerweb1.servicios.ServicioRegistro;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ControladorRegistroTest {
	
	private final String TEXTO_EMAIL_REGISTRO = "Bienvenido a Rent-Lock. Registro exitoso. "
			+ "Su cuenta ya esta activada. " + "Ya puede ingresar a su cuenta y contratar nuestros servicios";

	
	private DatosRegistro datosRegistro;
	private ControladorRegistro controladorRegistro;
	private ServicioRegistro servicioRegistro;
	private ServicioEmail servicioEmail;

	@Before
	public void setUp() throws Exception {
		// datosRegistro = mock(DatosRegistro.class);
		datosRegistro = new DatosRegistro();
		servicioRegistro = mock(ServicioRegistro.class);
		servicioEmail = mock(ServicioEmail.class);
		controladorRegistro = new ControladorRegistro(servicioRegistro, servicioEmail);
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
	@Test
	public void testQueNoSeRepitanUsuariosPorEmail(){
		datosRegistro.setEmail("pepe@pepe");
		datosRegistro.setContrasenia("1234");
		datosRegistro.setRepetirContrasenia("1234");
		controladorRegistro.registrarNuevoUsuario(datosRegistro);
		
		when(servicioRegistro.buscarUsuarioPorEmail("pepe@pepe")).thenReturn(new Usuario());
		ModelAndView mav = controladorRegistro.registrarNuevoUsuario(datosRegistro);
		
		String error="Ya existe un usuario registrado con el Email indicado";
		
		assertThat(mav.getViewName()).isEqualTo("registro");
		assertThat(mav.getModel().get("error")).isEqualTo(error);
	
	}
	
	@Test
	public void testQueSeEnvieUnEmailConElRegistroExitoso() {
		dadoQueNoExisteUnUsuarioConElMail("mail@mail.com");
		cuandoMeRegistroConLosDatos("mail@mail.com", "1234", "1234");
		entoncesReciboUnEmailA("mail@mail.com");
	}

	private void entoncesReciboUnEmailA(String direccion) {
		verify(servicioEmail, times(1)).enviarMail(eq(direccion), any(String.class), eq(TEXTO_EMAIL_REGISTRO));
	}

	private void cuandoMeRegistroConLosDatos(String email, String contrasenia, String repiteContrasenia) {
		DatosRegistro datosRegistro = new DatosRegistro();
		datosRegistro.setEmail(email);
		datosRegistro.setContrasenia(contrasenia);
		datosRegistro.setRepetirContrasenia(repiteContrasenia);
		controladorRegistro.registrarNuevoUsuario(datosRegistro);
	}

	private void dadoQueNoExisteUnUsuarioConElMail(String string) {	
	}
	

}
