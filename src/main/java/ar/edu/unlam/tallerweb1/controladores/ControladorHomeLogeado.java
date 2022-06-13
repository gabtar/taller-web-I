package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import ar.edu.unlam.tallerweb1.modelo.DatosLogin;
import ar.edu.unlam.tallerweb1.modelo.DatosModificarTextoLocker;
import ar.edu.unlam.tallerweb1.servicios.ServicioSucursal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Locker;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioAlquiler;

@Controller
public class ControladorHomeLogeado {

	private ServicioAlquiler servicioAlquiler;
	private ServicioSucursal servicioSucursal;

	@Autowired
	public ControladorHomeLogeado(ServicioAlquiler servicioAlquiler, ServicioSucursal servicioSucursal) {
		this.servicioSucursal = servicioSucursal;
		this.servicioAlquiler = servicioAlquiler;
	}

	@RequestMapping("/homeLogeado")
	public ModelAndView irHome(HttpServletRequest request) {
		// Si no estas logueado te redirecciona al login
		if (request.getSession().getAttribute("userId") == null) {
			return new ModelAndView("redirect:/login");
		}
		ModelMap modelo = new ModelMap();
		modelo.put("userID", request.getSession().getAttribute("userId"));
		modelo.put("usuario", request.getSession().getClass());
		modelo.put("modificarTextoLocker", new DatosModificarTextoLocker());

		// esto esta mal, no se como llamar al usuario para que se guarde como clase
		// usuario
		Usuario usuario = new Usuario();
		usuario.setId((Long) request.getSession().getAttribute("userId"));
		List<Locker> listaDeLocker;

		// Esto esta mal, me tendria que traer un servicio completo con todas las cosas
		listaDeLocker = this.servicioAlquiler.verAlquileresPropios(usuario);

		modelo.addAttribute("listaAlquileres", listaDeLocker);
		return new ModelAndView("homeLogeado", modelo);
	}

	// @RequestMapping("/home")
	public ModelAndView alquilarLocker(Locker locker, Usuario usuario) {
		// TODO Auto-generated method stub
		ModelMap model = new ModelMap();
		if (servicioAlquiler.alquilarLocker(locker, usuario)) {

			model.put("error", "alquiler Exitoso");
		} else {
			model.put("error", "Locker no disponible");
		}
		return new ModelAndView("homeLogeado", model);
	}

	@RequestMapping(path = "/modificar-texto/{lockerId}", method = RequestMethod.POST)
	public ModelAndView modificarTextoLocker(HttpServletRequest request, @PathVariable("lockerId") Long lockerId,
											 @ModelAttribute("modificarTextoLocker") DatosModificarTextoLocker datosModificarTextoLocker) {

		String textoAModificar = datosModificarTextoLocker.getTextoModificado();
		int id= Math.toIntExact(lockerId);
		servicioAlquiler.ModificarNotaDeLocker(id,textoAModificar);

		System.out.println(lockerId);
		System.out.println(textoAModificar);

		return new ModelAndView("redirect:/homeLogeado");
	}

}