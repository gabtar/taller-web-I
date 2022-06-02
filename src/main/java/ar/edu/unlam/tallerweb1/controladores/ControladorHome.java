package ar.edu.unlam.tallerweb1.controladores;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.DatosLogin;
import ar.edu.unlam.tallerweb1.modelo.DatosRegistro;
import ar.edu.unlam.tallerweb1.modelo.Locker;
import ar.edu.unlam.tallerweb1.modelo.Sucursal;
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
	@RequestMapping("/home")
	public ModelAndView irHome() {
		
		ModelMap modelo = new ModelMap();
		// Se agrega al modelo un objeto con key 'datosLogin' para que el mismo sea asociado
		// al model attribute del form que esta definido en la vista 'login'
		List<Locker> listaDeLocker;

		listaDeLocker = this.servicioHome.buscarAlquileresDisponibles();
	
		modelo.addAttribute("sucursales", listaDeLocker);
		// Se va a la vista login (el nombre completo de la lista se resuelve utilizando el view resolver definido en el archivo spring-servlet.xml)
		// y se envian los datos a la misma  dentro del modelo
		
		return new ModelAndView("lista-sucursales", modelo);
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
