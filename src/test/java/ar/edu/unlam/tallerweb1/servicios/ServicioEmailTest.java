package ar.edu.unlam.tallerweb1.servicios;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


import org.junit.Before;
import org.junit.Test;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class ServicioEmailTest {
	
	private JavaMailSender mailSender;
	private ServicioEmail servicioEmail;

	@Before
	public void setUp() throws Exception {
		mailSender = mock(JavaMailSender.class);
		servicioEmail = new ServicioEmail(mailSender);
	}

	@Test
	public void testQueSeEnvieUnMail() {
		servicioEmail.enviarMail("mail@mail.com", "Rent Lock", "Email de prueba");
		verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
	}

}
