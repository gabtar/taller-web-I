package ar.edu.unlam.tallerweb1.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;

@Service("servicioEmail")
public class ServicioEmail {
	
	private JavaMailSender mailSender;
	
	@Autowired
	public ServicioEmail(JavaMailSender mailSender){
		this.mailSender = mailSender;
	}

	public void enviarMail(String destinatario, String asunto, String body) {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(destinatario);
		mail.setSubject(asunto);
		mail.setText(body);
		mailSender.send(mail);
	}
}