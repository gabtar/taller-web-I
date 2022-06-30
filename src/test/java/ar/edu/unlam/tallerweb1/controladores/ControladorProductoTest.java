package ar.edu.unlam.tallerweb1.controladores;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.ValidarCodigo;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioLocker;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioLockerImpl;
import ar.edu.unlam.tallerweb1.servicios.ServicioAlquiler;
import ar.edu.unlam.tallerweb1.servicios.ServicioEmail;
import ar.edu.unlam.tallerweb1.servicios.ServicioGenerarCodigo;
import ar.edu.unlam.tallerweb1.servicios.ServicioGenerarCodigoImpl;
import ar.edu.unlam.tallerweb1.servicios.ServicioProducto;
import ar.edu.unlam.tallerweb1.servicios.ServicioProductoImpl;
import ar.edu.unlam.tallerweb1.servicios.ServicioSucursal;

public class ControladorProductoTest {
	ServicioProducto servicioProducto;
	ServicioGenerarCodigo servicioGenerarCodigo;
	ControladorProducto controladorProducto;
	HttpServletRequest request;
	HttpSession session;
	@Before
	public void setUp() throws Exception {
		servicioProducto=mock(ServicioProducto.class);
		session = mock(HttpSession.class);
		request = mock(HttpServletRequest.class);
		controladorProducto= new ControladorProducto(servicioProducto, servicioGenerarCodigo);
	}
	@Test

	public void queSePuedaAgregarUnProducto() {
		dadoQueTengoUnProductoParaAgregarYUnCodigo();
		ModelMap model=new ModelMap();
		model.put("error", "Producto ingresado correctamente");
		ModelAndView vistaObtenida=ingresoElCodigoParaAgregarMiProducto();
		ModelAndView vistaEsperada = new ModelAndView("validacionProducto",model);
		verificoQueSeGuardoExitosamente(vistaObtenida,vistaEsperada);
	}
	private void verificoQueSeGuardoExitosamente(ModelAndView vistaObtenida, ModelAndView vistaEsperada) {
		assertThat(vistaEsperada.getViewName()).isEqualTo(vistaObtenida.getViewName());
		assertThat(vistaEsperada.getModel()).isInstanceOf(vistaObtenida.getModel().getClass());
		assertThat(vistaEsperada.getModel().get("error")).isEqualTo(vistaObtenida.getModel().get("error"));
	}
	private ModelAndView ingresoElCodigoParaAgregarMiProducto() {
		ValidarCodigo validarCodigo=new ValidarCodigo();
		validarCodigo.setNombre("pepe@pepe");
		validarCodigo.setCodigo("123456");
		validarCodigo.setLockerId(1);
		when(request.getSession()).thenReturn(session);
		when(servicioProducto.validacionDatos(validarCodigo.getNombre(), validarCodigo.getCodigo(), validarCodigo.getLockerId())).thenReturn(true);
		ModelAndView mav=controladorProducto.agregarProducto(request, validarCodigo);
		return mav;
	}
	private void dadoQueTengoUnProductoParaAgregarYUnCodigo() {
		String nombre="TecladoGamer";
		Integer codigo= 123456;
		
	}
	@Test
	public void queNoSePuedaAgregarUnProducto() {
		ModelMap model=new ModelMap();
		model.put("error", "Los datos ingresados son invalidos");
		dadoQueTengoUnProductoParaAgregarYUnCodigo();
		
		ModelAndView vistaObtenida=ingresoElCodigoParaAgregarMiProductoYFalla();
		ModelAndView vistaEsperada = new ModelAndView("validacionProducto",model);
		verificoQueNoSeGuardoExitosamente(vistaObtenida,vistaEsperada);
	}
	private void verificoQueNoSeGuardoExitosamente(ModelAndView vistaObtenida, ModelAndView vistaEsperada) {
		assertThat(vistaEsperada.getViewName()).isEqualTo(vistaObtenida.getViewName());
		assertThat(vistaEsperada.getModel()).isInstanceOf(vistaObtenida.getModel().getClass());
		assertThat(vistaObtenida.getModel().get("error")).isEqualTo(vistaEsperada.getModel().get("error"));
		
	}
	private ModelAndView ingresoElCodigoParaAgregarMiProductoYFalla() {
		ValidarCodigo validarCodigo=new ValidarCodigo();
		validarCodigo.setNombre("pepe@pepe");
		validarCodigo.setCodigo("123456");
		validarCodigo.setLockerId(1);
		when(request.getSession()).thenReturn(session);
		when(servicioProducto.validacionDatos(validarCodigo.getNombre(), validarCodigo.getCodigo(), validarCodigo.getLockerId())).thenReturn(false);
		ModelAndView mav=controladorProducto.agregarProducto(request, validarCodigo);
		return mav;
	}
	@Test
	public void queNoSePuedaRetirarUnProducto() {
		ModelMap model=new ModelMap();
		model.put("error", "Los datos ingresados son invalidos");
		dadoQueTengoUnProductoParaRetirarYUnCodigo();
		
		ModelAndView vistaObtenida=ingresoElCodigoParaRetirarMiProductoYFalla();
		ModelAndView vistaEsperada = new ModelAndView("validacionProducto",model);
		verificoQueNoSeRetiroExitosamente(vistaObtenida,vistaEsperada);
	}
	private void verificoQueNoSeRetiroExitosamente(ModelAndView vistaObtenida, ModelAndView vistaEsperada) {
		assertThat(vistaEsperada.getViewName()).isEqualTo(vistaObtenida.getViewName());
		assertThat(vistaEsperada.getModel()).isInstanceOf(vistaObtenida.getModel().getClass());
		assertThat(vistaObtenida.getModel().get("error")).isEqualTo(vistaEsperada.getModel().get("error"));
		
	}
	private ModelAndView ingresoElCodigoParaRetirarMiProductoYFalla() {
		ValidarCodigo validarCodigo=new ValidarCodigo();
		validarCodigo.setNombre("pepe@pepe");
		validarCodigo.setCodigo("123456");
		validarCodigo.setLockerId(1);
		when(request.getSession()).thenReturn(session);
		when(servicioProducto.validacionDatos(validarCodigo.getNombre(), validarCodigo.getCodigo(), validarCodigo.getLockerId())).thenReturn(false);
		ModelAndView mav=controladorProducto.retirarProducto(request, validarCodigo);
		return mav;
	}
	private void dadoQueTengoUnProductoParaRetirarYUnCodigo() {
		String nombre="TecladoGamer";
		Integer codigo= 123456;
		
	}
	@Test
	public void queSePuedaRetirarUnProducto() {
		ModelMap model=new ModelMap();
		model.put("error", "Producto retirado correctamente");
		dadoQueTengoUnProductoParaRetirarYUnCodigo();
		
		ModelAndView vistaObtenida=ingresoElCodigoParaRetirarMiProductoYLoRetiro();
		ModelAndView vistaEsperada = new ModelAndView("validacionProducto",model);
		verificoQueSeRetiroExitosamente(vistaObtenida,vistaEsperada);
	}
	private void verificoQueSeRetiroExitosamente(ModelAndView vistaObtenida, ModelAndView vistaEsperada) {
		assertThat(vistaEsperada.getViewName()).isEqualTo(vistaObtenida.getViewName());
		assertThat(vistaEsperada.getModel()).isInstanceOf(vistaObtenida.getModel().getClass());
		assertThat(vistaObtenida.getModel().get("error")).isEqualTo(vistaEsperada.getModel().get("error"));
		
	}
	private ModelAndView ingresoElCodigoParaRetirarMiProductoYLoRetiro() {
		ValidarCodigo validarCodigo=new ValidarCodigo();
		validarCodigo.setNombre("pepe@pepe");
		validarCodigo.setCodigo("123456");
		validarCodigo.setLockerId(1);
		when(request.getSession()).thenReturn(session);
		when(servicioProducto.validacionDatos(validarCodigo.getNombre(), validarCodigo.getCodigo(), validarCodigo.getLockerId())).thenReturn(true);
		ModelAndView mav=controladorProducto.retirarProducto(request, validarCodigo);
		return mav;
	}
	
	
	
	
}
