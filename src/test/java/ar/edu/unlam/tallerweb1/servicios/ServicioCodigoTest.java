package ar.edu.unlam.tallerweb1.servicios;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Codigo;
import ar.edu.unlam.tallerweb1.modelo.CodigoExpiradoException;
import ar.edu.unlam.tallerweb1.modelo.CodigoInvalidoException;
import ar.edu.unlam.tallerweb1.modelo.Locker;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioLocker;

public class ServicioCodigoTest {
	
	
	private static final int ID_LOCKER = 1;
	private ServicioCodigoImpl servicioGenerarCodigo;
	private ServicioEmail servicioEmail;
	private RepositorioLocker repositorioLocker;
	private JavaMailSender mailSender;
	private Locker locker;
	private Codigo codigo;
	private Usuario usuario;
	private Date fechaAnterior;
	private Date fechaPosterior;

	@Before
	public void setUp() throws Exception {
		// Fecha ahora menos 10minutos
		this.fechaAnterior = new Date(Calendar.getInstance().getTimeInMillis() - (10 * 60 * 1000));
		// Fecha ahora más 10minutos
		this.fechaPosterior = new Date(Calendar.getInstance().getTimeInMillis() + (10 * 60 * 1000));
		
		mailSender = mock(JavaMailSender.class);
		servicioEmail = new ServicioEmail(mailSender);
		usuario = mock(Usuario.class);
		locker = mock(Locker.class);
		codigo = mock(Codigo.class);
		repositorioLocker = mock(RepositorioLocker.class);
		servicioGenerarCodigo= new ServicioCodigoImpl(servicioEmail, repositorioLocker);
	}

	@Test
	public void testQueSeEnvieUnMailAlGenerarUnCodigo() {
		doNothing().when(repositorioLocker).guardarCodigo(ID_LOCKER, "11111");
		servicioGenerarCodigo.generarCodigo("pepe@pepe", ID_LOCKER);
		verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
	}
	
	@Test(expected = CodigoInvalidoException.class)
	public void testLanzarCodigoInvalidoExceptionSiElUsuarioNoEsPropietarioDelLocker() {
		dadoQueTengoUnLockerDelUsuario("mail@mail.com");
		
		cuandoValidoConUnUsuarioQueNoTieneEseLocker("usuario@mail.com");
	}

	private void cuandoValidoConUnUsuarioQueNoTieneEseLocker(String codigo) {
		servicioGenerarCodigo.validarCodigo("mail@mail.com", codigo, ID_LOCKER);
	}

	private void dadoQueTengoUnLockerDelUsuario(String mailDelUsuario) {
		when(repositorioLocker.buscarLockerPorId(ID_LOCKER)).thenReturn(locker);
		when(locker.getPropietario()).thenReturn(usuario);
		when(locker.getCodigoApertura()).thenReturn(codigo);
		when(codigo.getCodigo()).thenReturn("111");
		when(usuario.getEmail()).thenReturn(mailDelUsuario);
	}
	
	@Test(expected = CodigoInvalidoException.class)
	public void testLanzarCodigoInvalidoExceptionSiConincideElUsuarioPeroNoElCodigo() {
		dadoQueTengoUnLockerDelUsuarioConElCodigo("mail@mail.com", "1234");
		
		cuandoValidoConElUsuarioCorrectoYCodigoInvalido("mail@mail.com", "1111");
	}

	private void cuandoValidoConElUsuarioCorrectoYCodigoInvalido(String email, String codigoApertura) {
		servicioGenerarCodigo.validarCodigo(email, codigoApertura, ID_LOCKER);
	}

	private void dadoQueTengoUnLockerDelUsuarioConElCodigo(String email, String codigoApertura) {
		when(repositorioLocker.buscarLockerPorId(ID_LOCKER)).thenReturn(locker);
		when(locker.getPropietario()).thenReturn(usuario);
		when(locker.getCodigoApertura()).thenReturn(codigo);
		when(codigo.getCodigo()).thenReturn(codigoApertura);
		when(usuario.getEmail()).thenReturn(email);
	}
	
	@Test(expected = CodigoExpiradoException.class)
	public void testLanzarCodigoExpiradoExecptionSiElCodigoEstaVencido() {
		dadoQueTengoUnLockerDelUsuarioConElCodigoYFecha("mail@mail.com", "1234", fechaAnterior);
		
		cuandoValidoConElUsuarioCorrectoYCodigoCorrectoLanzaCodigoExpiradoException("mail@mail.com", "1234");
	}

	private void cuandoValidoConElUsuarioCorrectoYCodigoCorrectoLanzaCodigoExpiradoException(String email,
			String codigoApertura) {
		servicioGenerarCodigo.validarCodigo(email, codigoApertura, ID_LOCKER);
	}

	private void dadoQueTengoUnLockerDelUsuarioConElCodigoYFecha(String email, String codigoApertura, Date fechaCodigo) {
		when(repositorioLocker.buscarLockerPorId(ID_LOCKER)).thenReturn(locker);
		when(locker.getPropietario()).thenReturn(usuario);
		when(locker.getCodigoApertura()).thenReturn(codigo);
		when(codigo.getCodigo()).thenReturn(codigoApertura);
		when(usuario.getEmail()).thenReturn(email);
		when(codigo.getFechaVencimiento()).thenReturn(fechaCodigo);
	}
	
	// TODO test codigo correcto y que se borre
	@Test
	public void testCuandoSeIngresaElCodigoCorrectoVerificoQueBorreElCodigo() {
		dadoQueTengoUnLockerDelUsuarioConElCodigoYFecha("mail@mail.com", "1234", fechaPosterior);
		
		cuandoValidoElCodigoCorrecto("mail@mail.com", "1234");
		
		entoncesSeBorraElCodigoDelLocker(ID_LOCKER);
	}

	private void entoncesSeBorraElCodigoDelLocker(Integer lockerId) {
		verify(repositorioLocker, times(1)).borrarCodigoALocker(lockerId);
	}

	private void cuandoValidoElCodigoCorrecto(String email, String codigoApertura) {
		servicioGenerarCodigo.validarCodigo(email, codigoApertura, ID_LOCKER);
	}

}
