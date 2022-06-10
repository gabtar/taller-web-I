package ar.edu.unlam.tallerweb1.controladores;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Locker;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioHome;


@Controller
public class ControladorHome {

	private ServicioHome servicioHome;
	private HttpSession session;
	
	
	@Autowired
	public ControladorHome(ServicioHome servicioHome, HttpSession session) {
		this.servicioHome = servicioHome;
		this.session = session;
	}
	
	@RequestMapping("/home")
	public ModelAndView irHome() {
		
		ModelMap modelo = new ModelMap();
		modelo.put("userID", session.getAttribute("userId"));
		
		List<Locker> listaDeLocker;

		listaDeLocker = this.servicioHome.buscarAlquileresDisponibles();
	
		modelo.addAttribute("listaAlquileres", listaDeLocker);
		
		return new ModelAndView("home", modelo);
	}

	
	//@RequestMapping("/home")
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
