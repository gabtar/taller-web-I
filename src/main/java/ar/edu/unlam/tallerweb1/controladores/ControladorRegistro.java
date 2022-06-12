package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.DatosLogin;
import ar.edu.unlam.tallerweb1.modelo.DatosRegistro;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioEmail;
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

	private final String TEXTO_EMAIL_REGISTRO = "Bienvenido a Rent-Lock. Registro exitoso. "
			+ "Su cuenta ya esta activada. " + "Ya puede ingresar a su cuenta y contratar nuestros servicios";

	private ServicioRegistro servicioRegistro;
	private ServicioEmail servicioEmail;

	@Autowired
	public ControladorRegistro(ServicioRegistro servicioRegistro, ServicioEmail servicioEmail) {
		this.servicioRegistro = servicioRegistro;
		this.servicioEmail = servicioEmail;
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
		if (buscarUsuario(datosRegistro.getEmail()) == null) {
			if (datosRegistro.getContrasenia().equals(datosRegistro.getRepetirContrasenia())) {
				servicioRegistro.registrar(datosRegistro.getEmail(), datosRegistro.getContrasenia());
				servicioEmail.enviarMail(datosRegistro.getEmail(), "Rent-Lock", TEXTO_EMAIL_REGISTRO);

				model.put("error", "Nuevo usuario creado, ya puede loguearse");
				model.put("datosLogin", new DatosLogin());
				viewName = "login";
			} else {
				model.put("error", "Las claves no coinciden");
				model.put("datosRegistro", datosRegistro);
				viewName = "registro";
			}

		} else {
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
