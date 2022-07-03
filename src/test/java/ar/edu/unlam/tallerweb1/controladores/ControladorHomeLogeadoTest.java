package ar.edu.unlam.tallerweb1.controladores;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ar.edu.unlam.tallerweb1.servicios.ServicioSucursal;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Locker;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioAlquiler;
import ar.edu.unlam.tallerweb1.servicios.ServicioCodigo;
import ar.edu.unlam.tallerweb1.servicios.ServicioCodigoTest;

public class ControladorHomeLogeadoTest {
	ServicioAlquiler servicioAlquiler;
	ControladorHomeLogeado controladorHomeLogeado;
	ServicioCodigo servicioGenerarCodigo;
	ServicioSucursal servicioSucursal;
	HttpServletRequest request;
	HttpSession session;

	@Before
	public void setUp() throws Exception {
		servicioGenerarCodigo = mock(ServicioCodigo.class);
		servicioAlquiler = mock(ServicioAlquiler.class);
		servicioSucursal = mock(ServicioSucursal.class);
		session = mock(HttpSession.class);
		request = mock(HttpServletRequest.class);
		controladorHomeLogeado = new ControladorHomeLogeado(servicioAlquiler, servicioSucursal, servicioGenerarCodigo);
	}

	@Test
	public void queMelleveAunaPaginaParaCargarElCodigoDeApertura() {
		dadoQueTengoLaSiguienteSesion();
		ModelAndView vistaObtenida = cuandoGeneroElCodigoDeApertura();
		ModelAndView vistaEsperada = new ModelAndView("codigo-apertura");
		esperoQueMeLleveALaPaginaParaCargarElCodigo(vistaObtenida, vistaEsperada);
	}

	private void dadoQueTengoLaSiguienteSesion() {
		when(request.getSession()).thenReturn(session);
	}

	private ModelAndView cuandoGeneroElCodigoDeApertura() {
		int lockerId = 1;
		when(request.getSession()).thenReturn(session);
		return controladorHomeLogeado.generarCodigoApertura(request, lockerId);
	}

	private void esperoQueMeLleveALaPaginaParaCargarElCodigo(ModelAndView vistaObtenida, ModelAndView vistaEsperada) {
		int lockerId = 1;
		when(request.getSession().getAttribute("nombreUsuario")).thenReturn("pepe@pepe");
		verify(servicioGenerarCodigo, times(1)).generarCodigo((String) request.getAttribute("nombreUsuario"), lockerId);
		assertThat(vistaEsperada.getViewName()).isEqualTo(vistaObtenida.getViewName());
		assertThat(vistaEsperada.getModel()).isInstanceOf(vistaObtenida.getModel().getClass());
	}
}
