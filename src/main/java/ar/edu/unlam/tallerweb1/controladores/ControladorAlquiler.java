package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.DatosModificarTextoLocker;
import ar.edu.unlam.tallerweb1.modelo.Locker;
import ar.edu.unlam.tallerweb1.modelo.Sucursal;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioAlquiler;
import ar.edu.unlam.tallerweb1.servicios.ServicioEmail;
import ar.edu.unlam.tallerweb1.servicios.ServicioSucursal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ControladorAlquiler {
    
	private ServicioAlquiler servicioAlquiler;
    private ServicioSucursal servicioSucursal;
    private ServicioEmail servicioEmail;
    @Autowired
    public ControladorAlquiler(ServicioAlquiler servicioAlquiler, ServicioSucursal servicioSucursal, ServicioEmail servicioEmail) {
        this.servicioAlquiler = servicioAlquiler;
        this.servicioEmail= servicioEmail;
        this.servicioSucursal = servicioSucursal;
    }

    @RequestMapping("/alquileres")
    public ModelAndView mostrarAlquileresDisponibles(HttpServletRequest request) {

        ModelMap modelo = new ModelMap();

        List<Locker> listaLockers;

        listaLockers = this.servicioAlquiler.buscarAlquileresDisponibles();
        modelo.addAttribute("alquileres", listaLockers);

        if (listaLockers.isEmpty()) {
            modelo.put("error", "No se encontraron lockers");
        }
        //modelo.put("listaAlquileres", listaLockers);
        return new ModelAndView("lista-alquileres", modelo);
    }
    
    @RequestMapping("/alquileres/{idSucursal}")
    public ModelAndView mostrarLockersDisponiblesPorTamanio(
    		HttpServletRequest request, 
    		@PathVariable("idSucursal") Long idSucursal,
    		@RequestParam(required = false) String tamanio) {
    	ModelMap modelo = new ModelMap();
    	Sucursal sucursal;
    	List<Locker> listaLockers;
    	
    	sucursal = this.servicioSucursal.buscarSucursalPorId(idSucursal);
    	modelo.addAttribute("sucursal", sucursal);
    	
    	if (tamanio != null) {
    		listaLockers = servicioAlquiler.buscarLockersDisponiblesPorSucursalYTamanio(idSucursal, tamanio);
    	} else {
    		listaLockers = servicioAlquiler.buscarLockersDisponiblesPorSucursal(idSucursal);
    	}
    	
  
    	modelo.addAttribute("alquileres", listaLockers);
    	
    	if (listaLockers.isEmpty() || sucursal == null) {
            modelo.put("error", "No se encontraron lockers");
        }
    	
    	return new ModelAndView("lista-alquileres", modelo);
    }

    @RequestMapping(path = "/modificar-locker/{lockerId}", method = RequestMethod.POST)
    public ModelAndView alquilarLocker(HttpServletRequest request, @PathVariable("lockerId") int lockerId) {
    	
        Long usuarioId = (Long) request.getSession().getAttribute("userId");
        ModelMap modelo = new ModelMap();
        if(servicioAlquiler.alquilarLocker(lockerId, usuarioId)){
        	String usuario= (String) request.getSession().getAttribute("nombreUsuario");
        	final String TEXTO_EMAIL_REGISTRO = "usted a alquilado el locker " + lockerId + " gracias "+ usuario +" por elegirnos RENTLOCK";
        	modelo.put("error", "Alquiler exitoso");
            servicioEmail.enviarMail(usuario, "Rent-Lock", TEXTO_EMAIL_REGISTRO);
            return new ModelAndView("redirect:/homeLogeado", modelo);
        }

        modelo.put("error", "Locker no disponible");
        return new ModelAndView("redirect:/homeLogeado", modelo);
    }

    @RequestMapping(path = "/cancelar-locker/{lockerId}", method = RequestMethod.POST)
    public ModelAndView cancelarLocker(HttpServletRequest request, @PathVariable("lockerId") int lockerId) {
        Long usuarioId = (Long) request.getSession().getAttribute("userId");
        ModelMap modelo = new ModelMap();
        servicioAlquiler.cancelarLocker(lockerId, usuarioId);
        modelo.put("error", "Cancelacion exitosa");
        return new ModelAndView("redirect:/homeLogeado", modelo);
    }
}
