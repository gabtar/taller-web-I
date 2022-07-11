package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Alquiler;
import ar.edu.unlam.tallerweb1.modelo.Locker;
import ar.edu.unlam.tallerweb1.servicios.ServicioAlquiler;
import ar.edu.unlam.tallerweb1.servicios.ServicioEmail;
import ar.edu.unlam.tallerweb1.servicios.ServicioSucursal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
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
    
    @RequestMapping("/alquileres/buscar")
    public ModelAndView buscarLockersDisponibles(
    		@RequestParam(required = false) String localidad,
    		@RequestParam(required = false) String tamanio
    		) {
    	
    	ModelMap modelo = new ModelMap();
    	
    	List<Locker> listaLockers = servicioAlquiler.buscarLockersDisponiblesPorSucursalYTamanio(localidad, tamanio);
    	
    	if (listaLockers.isEmpty()) {
    		modelo.put("error", "No se encontraron lockers");
    	}
    	
    	modelo.addAttribute("alquileres", listaLockers);
    	
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
    
    @RequestMapping(path = "/alquileres/registro") 
    public ModelAndView mostarRegistrosDelAlquileres(HttpServletRequest request){
    	ModelMap modelo = new ModelMap();
    	
    	Long usuarioId = (Long) request.getSession().getAttribute("userId");
    	
    	List<Alquiler> registroAlquileres = servicioAlquiler.obtenerRegistroDeAlquileres(usuarioId);
    	System.out.println(registroAlquileres);
    	
    	if(registroAlquileres.isEmpty()) {
    		modelo.put("error", "No se encontraron alquilres");
    	}
    	
    	modelo.addAttribute("registro", registroAlquileres);
    	
    	return new ModelAndView("registro-alquileres", modelo);
    }
}
