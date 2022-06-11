package ar.edu.unlam.tallerweb1.controladores;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Locker;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioHome;
public class ControladorHomeLogeadoTest {
	ServicioHome servicioHome;
	ControladorHomeLogeado controladorHomeLogeado;
	@Before
	public void setUp() throws Exception {
		servicioHome = mock(ServicioHome.class);
		controladorHomeLogeado = new ControladorHomeLogeado(servicioHome);
		
	}
	@Test
	public void queSePuedaAlquilarUnLocker() {
		Locker locker=new Locker();
		Usuario usuario=new Usuario();
		when(servicioHome.alquilarLocker(locker,usuario)).thenReturn(true);
		ModelAndView mav = controladorHomeLogeado.alquilarLocker(locker,usuario);
		String error="alquiler Exitoso";
		assertThat("homeLogeado").isEqualTo(mav.getViewName());
		assertThat(mav.getModel().get("error")).isEqualTo(error);
		
	}
	@Test
	public void queNoSePuedaAlquilarUnLockerYaAlquilado() {
		Locker locker=new Locker();
		Usuario usuario=new Usuario();
		when(servicioHome.alquilarLocker(locker,usuario)).thenReturn(false);
		ModelAndView mav = controladorHomeLogeado.alquilarLocker(locker,usuario);
		String error="Locker no disponible";
		assertThat("homeLogeado").isEqualTo(mav.getViewName());
		assertThat(mav.getModel().get("error")).isEqualTo(error);
		
	}
	
	
}
