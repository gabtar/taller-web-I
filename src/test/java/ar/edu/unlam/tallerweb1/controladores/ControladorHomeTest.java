package ar.edu.unlam.tallerweb1.controladores;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Locker;

import ar.edu.unlam.tallerweb1.servicios.ServicioHome;
public class ControladorHomeTest {
	ServicioHome servicioHome;
	ControladorHome controladorHome;
	@Before
	public void setUp() throws Exception {
		servicioHome = mock(ServicioHome.class);
		controladorHome= new ControladorHome(servicioHome);
		
	}
	//@Test
	public void queSePuedaAlquilarUnLocker() {
		Locker locker=new Locker();
		
		ModelAndView mav = controladorHome.alquilarLocker(locker);
		String error="alquiler Exitoso";
		
		assertThat("home").isEqualTo(mav.getViewName());
		assertThat(mav.getModel().get("error")).isEqualTo(error);
		
	}
	@Test
	public void queNoSePuedaAlquilarUnLockerYaAlquilado() {
		Locker locker=new Locker();
		ModelAndView mav = controladorHome.alquilarLocker(locker);
		when(servicioHome.alquilarLocker(locker)).thenReturn(false);
		String error="Locker no disponible";
		
		assertThat("home").isEqualTo(mav.getViewName());
		assertThat(mav.getModel().get("error")).isEqualTo(error);
		
	}
	
	
}
