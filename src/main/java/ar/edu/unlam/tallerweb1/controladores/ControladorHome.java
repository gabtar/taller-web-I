package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.DatosRegistro;
import ar.edu.unlam.tallerweb1.modelo.Locker;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioHome;
import ar.edu.unlam.tallerweb1.servicios.ServicioRegistro;

@Controller
public class ControladorHome {

	private ServicioHome servicioHome;
	
	@Autowired
	public ControladorHome(ServicioHome servicioHome) {
		this.servicioHome = servicioHome;
	}

	
	public ModelAndView alquilarLocker(Locker locker,Usuario usuario) {
		// TODO Auto-generated method stub
		ModelMap model = new ModelMap();
		if(servicioHome.alquilarLocker(locker,usuario)) {
			
			model.put("error","alquiler Exitoso");
		}else {
			model.put("error","Locker no disponible");
		}
		return new ModelAndView("home", model);
	}
}
