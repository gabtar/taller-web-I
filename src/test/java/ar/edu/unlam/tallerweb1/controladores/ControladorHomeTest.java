package ar.edu.unlam.tallerweb1.controladores;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Locker;
import ar.edu.unlam.tallerweb1.modelo.Producto;
import ar.edu.unlam.tallerweb1.servicios.ServicioHome;
public class ControladorHomeTest {
	ServicioHome servicioHome;
	ControladorHome controladorHome;
	@Before
	public void setUp() throws Exception {
		servicioHome = mock(ServicioHome.class);
		controladorHome= new ControladorHome(servicioHome);
		
	}
	@Test
	public void queSePuedaGuardarUnProductoEnElLocker() {
		
		Producto producto=new Producto();
		Locker locker=new Locker();
		when(servicioHome.guardarProducto(producto, locker)).thenReturn(true);
		ModelAndView mav=controladorHome.guardarProducto(producto,locker);
		String error="producto guardado con exito";
		
		assertThat(mav.getViewName()).isEqualTo("home");
		assertThat(mav.getModel().get("error")).isEqualTo(error);
	
	}
	@Test
	public void queNoSeGuardenDosProductosEnElMismoLocker() {
		Producto producto=new Producto();
		Locker locker=new Locker();
		controladorHome.guardarProducto(producto,locker);
		when(servicioHome.guardarProducto(producto, locker)).thenReturn(false);
		ModelAndView mav=controladorHome.guardarProducto(producto,locker);
		String error="sucedio un error en su solicitud";
		assertThat(mav.getViewName()).isEqualTo("home");
		assertThat(mav.getModel().get("error")).isEqualTo(error);
	}
	@Test
	public void queNoSeGuardeUnProductoMasGrandeQueElLocker() {
		Producto producto=new Producto();
		Locker locker=new Locker();
		controladorHome.guardarProducto(producto,locker);
		when(servicioHome.guardarProducto(producto, locker)).thenReturn(false);
		ModelAndView mav=controladorHome.guardarProducto(producto,locker);
		String error="sucedio un error en su solicitud";
		assertThat(mav.getViewName()).isEqualTo("home");
		assertThat(mav.getModel().get("error")).isEqualTo(error);
	}

}
