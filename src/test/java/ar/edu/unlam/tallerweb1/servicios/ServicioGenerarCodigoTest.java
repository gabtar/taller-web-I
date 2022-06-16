package ar.edu.unlam.tallerweb1.servicios;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.servlet.ModelAndView;

public class ServicioGenerarCodigoTest {
	
	
	private ServicioGenerarCodigo servicioGenerarCodigo;
	private ServicioEmail servicioEmail;

	@Before
	public void setUp() throws Exception {
		servicioGenerarCodigo=new ServicioGenerarCodigoImpl();
		servicioEmail = mock(ServicioEmail.class);
	
	}

	@Test
	public void testQueSeEnvieUnMail() {

		//servicioGenerarCodigo.generarCodigo("pepe@pepe");
		//verify(servicioEmail, times(1)).enviarMail(eq("pepe@pepe"), any(String.class), any());
	}

}
