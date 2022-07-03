package ar.edu.unlam.tallerweb1.controladores;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.edu.unlam.tallerweb1.servicios.ServicioEmail;
import ar.edu.unlam.tallerweb1.modelo.CodigoExpiradoException;
import ar.edu.unlam.tallerweb1.modelo.CodigoInvalidoException;
import ar.edu.unlam.tallerweb1.modelo.DatosValidarCodigo;
import ar.edu.unlam.tallerweb1.servicios.ServicioCodigo;

@Controller
@RequestMapping("/codigo")
public class ControladorCodigo {
	
	private ServicioCodigo servicioGenerarCodigo;
	
	@Autowired
	public ControladorCodigo(ServicioCodigo servicioGenerarCodigo) {
		this.servicioGenerarCodigo = servicioGenerarCodigo;
	}


	@RequestMapping(path = "/generar/{lockerId}")
	public ModelAndView generarCodigoApertura(HttpServletRequest request, @PathVariable("lockerId") int lockerId, RedirectAttributes redirectAttributes) {
		String usuario=(String) request.getSession().getAttribute("nombreUsuario");
		servicioGenerarCodigo.generarCodigo(usuario, lockerId);
		redirectAttributes.addFlashAttribute("mensaje", "Se ha enviado un nuevo email. Verifique su casilla");
		return new ModelAndView("redirect:/homeLogeado");
	}
	
	// De acá para abajo son las rutas que estarían en la app
	// que se muestra en la sucursal al abrir el locker
	@RequestMapping(path = "/validar", method = RequestMethod.GET)
    public ModelAndView irAValidacion(@ModelAttribute("validarCodigo") DatosValidarCodigo validarCodigo) {

        return  new ModelAndView("agregar-producto");

    }
	
	// TODO -> POST /validar/ Reemplaza a controladorProducto
	// Post con lockerid, usuarioid y numero código
	// Validar borra el código
	// Casos -> El código ha expirado
	//  	 -> El código es inválido
	@RequestMapping(path = "/validar", method = RequestMethod.POST)
    public ModelAndView validar(@ModelAttribute("validarCodigo") DatosValidarCodigo validarCodigo) {
        ModelMap modelo = new ModelMap();
        
        try {
        	servicioGenerarCodigo.validarCodigo(validarCodigo.getNombre(), validarCodigo.getCodigo(), validarCodigo.getLockerId());
        	modelo.put("mensaje", "Código válido. Locker abierto");
		} catch (CodigoInvalidoException e) {
			modelo.put("error", e.getMessage());
		} catch (CodigoExpiradoException e) {
			modelo.put("error", e.getMessage());
		} catch (Exception e) {
			modelo.put("error", "Datos Inválidos");
		}

        return  new ModelAndView("agregar-producto", modelo);

    }
}
