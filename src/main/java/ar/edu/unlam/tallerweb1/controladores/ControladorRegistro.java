package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.DatosLogin;
import ar.edu.unlam.tallerweb1.modelo.DatosRegistro;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioRegistro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorRegistro {
	
	private ServicioRegistro servicioRegistro;
	
	@Autowired
	public ControladorRegistro(ServicioRegistro servicioRegistro) {
		this.servicioRegistro = servicioRegistro;
	}
	
	@RequestMapping("/registro")
	public ModelAndView irAlRegistro(DatosRegistro datosRegistro) {
		ModelMap modelo = new ModelMap();
		
		modelo.put("datosRegistro", new DatosRegistro());
		
		return new ModelAndView("registro", modelo);
	}

	@RequestMapping(path = "/validar-registro", method = RequestMethod.POST)
	public ModelAndView registrarNuevoUsuario(@ModelAttribute("datosRegistro") DatosRegistro datosRegistro) {
		
		ModelMap model = new ModelMap();
        String viewName = "home";
       if( buscarUsuario(datosRegistro.getEmail())==null) {
    	   if(datosRegistro.getContrasenia().equals(datosRegistro.getRepetirContrasenia())) {
           	servicioRegistro.registrar(datosRegistro.getEmail(), datosRegistro.getContrasenia());
           	// Lo mando al login
           	model.put("error", "Nuevo usuario creado, ya puede loguearse");
           	model.put("datosLogin", new DatosLogin());
           	viewName = "login";
           } else {
           	model.put("error", "Las claves no coinciden");
           	model.put("datosRegistro", datosRegistro);
           	viewName = "registro";
           }
   		
    	   
       }else {
          	model.put("error", "Ya existe un usuario registrado con el Email indicado");
          	model.put("datosRegistro", datosRegistro);
          	viewName = "registro";
          	}
        // FALTAN LA PARTE DE VALIDAR SI YA EXISTE EL USUARIO
        
		return new ModelAndView(viewName, model);
		
	}
	private Usuario buscarUsuario(String Email) {
		
		return servicioRegistro.buscarUsuarioPorEmail(Email);
	}

}
