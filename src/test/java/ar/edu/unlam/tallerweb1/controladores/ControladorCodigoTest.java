package ar.edu.unlam.tallerweb1.controladores;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.edu.unlam.tallerweb1.modelo.CodigoExpiradoException;
import ar.edu.unlam.tallerweb1.modelo.CodigoInvalidoException;
import ar.edu.unlam.tallerweb1.modelo.DatosValidarCodigo;
import ar.edu.unlam.tallerweb1.servicios.ServicioAlquiler;
import ar.edu.unlam.tallerweb1.servicios.ServicioCodigo;
import ar.edu.unlam.tallerweb1.servicios.ServicioCodigoImpl;
import ar.edu.unlam.tallerweb1.servicios.ServicioSucursal;

public class ControladorCodigoTest {

	private static final String MENSAJE_CODIGO_O_USUARIO_INVALIDO = "Código o Usuario inválido";
	private static final String MENSAJE_CODIGO_VALIDO_LOCKER_ABIERTO = "Código válido. Locker abierto";
	private static final String MENSAJE_CODIGO_EXPIRADO = "El código ha expirado. Ya no es válido";
	private ControladorCodigo controladorCodigo;
	private ServicioCodigoImpl servicioGenerarCodigo;
	private HttpServletRequest request;
	private HttpSession session;
	private RedirectAttributes redirectAttributes;
	private DatosValidarCodigo datosValidarCodigo;

	@Before
	public void setUp() throws Exception {
		servicioGenerarCodigo = mock(ServicioCodigoImpl.class);
		session = mock(HttpSession.class);
		request = mock(HttpServletRequest.class);
		redirectAttributes = mock(RedirectAttributes.class);
		controladorCodigo = new ControladorCodigo(servicioGenerarCodigo);

	}

	@Test
	public void queQueSeGenereElCodigoDeApertura() {
		dadoQueTengoLaSiguienteSesion();
		ModelAndView vistaObtenida = cuandoGeneroElCodigoDeApertura();
		esperoQueMeLleveALaPaginaParaCargarElCodigo(vistaObtenida.getViewName(), "redirect:/homeLogeado");
	}

	private void dadoQueTengoLaSiguienteSesion() {
		when(request.getSession()).thenReturn(session);
	}

	private ModelAndView cuandoGeneroElCodigoDeApertura() {
		int lockerId = 1;
		when(request.getSession()).thenReturn(session);
		return controladorCodigo.generarCodigoApertura(request, lockerId, redirectAttributes);
	}

	private void esperoQueMeLleveALaPaginaParaCargarElCodigo(String vistaObtenida, String vistaEsperada) {
		int lockerId = 1;
		when(request.getSession().getAttribute("nombreUsuario")).thenReturn("pepe@pepe");
		verify(servicioGenerarCodigo, times(1)).generarCodigo((String) request.getAttribute("nombreUsuario"), lockerId);
		assertThat(vistaEsperada).isEqualTo(vistaObtenida);
	}
	
	@Test
	public void testIrAValidarElCodigo() {
		dadoQueEstoyEnLaSucursal();
		ModelAndView mav = cuandoVoyAValidarElCodigo();
		entoncesMeLLevaALaVista(mav, "agregar-producto");
	}

	private void dadoQueEstoyEnLaSucursal() {
	}

	private void entoncesMeLLevaALaVista(ModelAndView mav, String vistaEsperada) {
		assertThat(vistaEsperada).isEqualTo(mav.getViewName());
	}

	private ModelAndView cuandoVoyAValidarElCodigo() {
		return controladorCodigo.irAValidacion(new DatosValidarCodigo());
	}
	
	@Test
	public void testValidarConCodigoCorrecto() {
		dadoQueEstoyEnLaSucursal();
		ModelAndView mav = cuandoVoyAValidarElCodigoConDatosValidos(datosValidarCodigo);
		entoncesMeLLevaALaVista(mav, "agregar-producto");
		entoncesMeMuestraMensaje((String) mav.getModel().get("mensaje"), MENSAJE_CODIGO_VALIDO_LOCKER_ABIERTO);
	}

	private void entoncesMeMuestraMensaje(String mensajeObtenido, String mensajeCodigoValidoLockerAbierto) {
		assertThat(mensajeCodigoValidoLockerAbierto).isEqualTo(mensajeObtenido);
	}

	private ModelAndView cuandoVoyAValidarElCodigoConDatosValidos(DatosValidarCodigo datosValidarCodigo) {
		datosValidarCodigo = new DatosValidarCodigo();
		datosValidarCodigo.setNombre("");
		datosValidarCodigo.setCodigo("");
		datosValidarCodigo.setLockerId(0);
		doNothing().when(servicioGenerarCodigo).validarCodigo(datosValidarCodigo.getNombre(), datosValidarCodigo.getCodigo(), datosValidarCodigo.getLockerId());
		return controladorCodigo.validar(datosValidarCodigo);
	}
	
	@Test
	public void testValidarConCodigoIncorrecto() {
		dadoQueEstoyEnLaSucursal();
		ModelAndView mav = cuandoVoyAValidarElCodigoConDatosIncorrectos(datosValidarCodigo);
		entoncesMeLLevaALaVista(mav, "agregar-producto");
		entoncesMeMuestraMensaje((String) mav.getModel().get("error"), MENSAJE_CODIGO_O_USUARIO_INVALIDO);
	}

	private ModelAndView cuandoVoyAValidarElCodigoConDatosIncorrectos(DatosValidarCodigo datosValidarCodigo2) {
		datosValidarCodigo = new DatosValidarCodigo();
		datosValidarCodigo.setNombre("");
		datosValidarCodigo.setCodigo("");
		datosValidarCodigo.setLockerId(0);
		doThrow(new CodigoInvalidoException(MENSAJE_CODIGO_O_USUARIO_INVALIDO)).when(servicioGenerarCodigo).validarCodigo(
				datosValidarCodigo.getNombre(), 
				datosValidarCodigo.getCodigo(),
				datosValidarCodigo.getLockerId());
		return controladorCodigo.validar(datosValidarCodigo);
	}
	
	@Test
	public void testValidarConCodigoExpirado() {
		dadoQueEstoyEnLaSucursal();
		ModelAndView mav = cuandoVoyAValidarElCodigoConCodigoExpirado(datosValidarCodigo);
		entoncesMeLLevaALaVista(mav, "agregar-producto");
		entoncesMeMuestraMensaje((String) mav.getModel().get("error"), MENSAJE_CODIGO_EXPIRADO);
	}

	private ModelAndView cuandoVoyAValidarElCodigoConCodigoExpirado(DatosValidarCodigo datosValidarCodigo2) {
		datosValidarCodigo = new DatosValidarCodigo();
		datosValidarCodigo.setNombre("");
		datosValidarCodigo.setCodigo("");
		datosValidarCodigo.setLockerId(0);
		doThrow(new CodigoExpiradoException(MENSAJE_CODIGO_EXPIRADO)).when(servicioGenerarCodigo).validarCodigo(
				datosValidarCodigo.getNombre(), 
				datosValidarCodigo.getCodigo(),
				datosValidarCodigo.getLockerId());
		return controladorCodigo.validar(datosValidarCodigo);
	}
}
