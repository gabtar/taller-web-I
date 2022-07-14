package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mercadopago.resources.preference.Preference;

import ar.edu.unlam.tallerweb1.servicios.ServicioAlquiler;
import ar.edu.unlam.tallerweb1.servicios.ServicioMercadoPago;

@Controller
public class ControladorPago {
	
	private ServicioMercadoPago servicioMercadoPago;
	private ServicioAlquiler servicioAlquiler;

	@Autowired
	public ControladorPago(ServicioMercadoPago servicioMercadoPago, ServicioAlquiler servicioAlquiler) {
		this.servicioMercadoPago = servicioMercadoPago;
		this.servicioAlquiler=servicioAlquiler;
	}

	@RequestMapping(path = "/payment/{alquilerId}", method = RequestMethod.GET)
	public ModelAndView inicio(@PathVariable("alquilerId") Long alquilerId) {
		ModelMap modelo = new ModelMap();
		
		// TODO falta manejar las exceptions de mercadopago con try catch
		Preference preference = this.servicioMercadoPago.generarPago(alquilerId);
		
		modelo.put("preference", preference); 

		return new ModelAndView("pago", modelo);
	}
	
	@RequestMapping(path = "/payment/success/{alquilerId}", method = RequestMethod.GET)
	public ModelAndView success(@PathVariable("alquilerId") Long alquilerId, RedirectAttributes redirectAttributes) {
		servicioAlquiler.setEstadoAlquiler(alquilerId);
		redirectAttributes.addFlashAttribute("mensaje", "Pago exitoso");
		return new ModelAndView("redirect:/homeLogeado");
	}
	
	@RequestMapping(path = "/payment/failure", method = RequestMethod.GET)
	public ModelAndView failure(RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("error", "No se pudo comprobar el pago. Intente nuevamente más tarde");
		return new ModelAndView("redirect:/homeLogeado");
	}
}
