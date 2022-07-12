package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.mercadopago.resources.preference.Preference;

import ar.edu.unlam.tallerweb1.servicios.ServicioMercadoPago;

@Controller
public class ControladorPago {
	
	private ServicioMercadoPago servicioMercadoPago;

	@Autowired
	public ControladorPago(ServicioMercadoPago servicioMercadoPago) {
		this.servicioMercadoPago = servicioMercadoPago;
	}

	@RequestMapping(path = "/payment", method = RequestMethod.GET)
	public ModelAndView inicio() {
		ModelMap modelo = new ModelMap();
		
		// TODO falta manejar las exceptions de mercadopago con try catch
		Preference preference = this.servicioMercadoPago.generarPago();
		
		modelo.put("preference", preference); 

		return new ModelAndView("pago", modelo);
	}
	
	@RequestMapping(path = "/payment/success", method = RequestMethod.GET)
	public ModelAndView success() {
		return new ModelAndView("success");
	}
	
	@RequestMapping(path = "/payment/failure", method = RequestMethod.GET)
	public ModelAndView failure() {
		return new ModelAndView("failure");
	}
}
