package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.servicios.ServicioSucursal;
import ar.edu.unlam.tallerweb1.servicios.ServicioGenerarCodigoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.servicios.ServicioAlquiler;
import ar.edu.unlam.tallerweb1.servicios.ServicioGenerarCodigo;


@Controller
public class ControladorHomeLogeado {

	private ServicioAlquiler servicioAlquiler;
	private ServicioSucursal servicioSucursal;
	private ServicioGenerarCodigo servicioGenerarCodigo;

	@Autowired
	public ControladorHomeLogeado(ServicioAlquiler servicioAlquiler, ServicioSucursal servicioSucursal, ServicioGenerarCodigo servicioGenerarCodigo) {
		this.servicioSucursal = servicioSucursal;
		this.servicioAlquiler = servicioAlquiler;
		this.servicioGenerarCodigo= servicioGenerarCodigo;
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
	
	@RequestMapping(path = "/codigoApertura/{lockerId}")
	public ModelAndView generarCodigoApertura(HttpServletRequest request, @PathVariable("lockerId") int lockerId) {
		// TODO Auto-generated method stub
		String usuario=(String) request.getSession().getAttribute("nombreUsuario");
		servicioGenerarCodigo.generarCodigo(usuario, lockerId);
		return new ModelAndView("codigo-apertura");
	}

	@RequestMapping(path = "/retirarProducto")
	public ModelAndView retirarProducto(HttpServletRequest request) {
		ModelMap modelo = new ModelMap();
		modelo.put("tipoOperacion", "Para poder retirar un producto necesitamos que coloque el codigo que recibio por email");
		modelo.put("validarCodigo", new ValidarCodigo());
		return new ModelAndView("retirar-producto", modelo);
	}

	@RequestMapping(path = "/agregarProducto")
	public ModelAndView agregarProducto(HttpServletRequest request) {
		ModelMap modelo = new ModelMap();
		modelo.put("tipoOperacion", "Para poder agregar un producto necesitamos que coloque el codigo que recibio por email");
		modelo.put("validarCodigo", new ValidarCodigo());
		return new ModelAndView("agregar-producto", modelo);
	}
	

}