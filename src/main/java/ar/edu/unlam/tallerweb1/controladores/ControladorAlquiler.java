package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.DatosModificarTextoLocker;
import ar.edu.unlam.tallerweb1.modelo.Locker;
import ar.edu.unlam.tallerweb1.modelo.Sucursal;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioAlquiler;
import ar.edu.unlam.tallerweb1.servicios.ServicioSucursal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ControladorAlquiler {
    private ServicioAlquiler servicioAlquiler;
    private ServicioSucursal servicioSucursal;
    @Autowired
    public ControladorAlquiler(ServicioAlquiler servicioAlquiler, ServicioSucursal servicioSucursal) {
        this.servicioAlquiler = servicioAlquiler;
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

    public ModelAndView alquilarLocker(Locker locker, Usuario usuario) {
        ModelMap modelo = new ModelMap();

        if(servicioAlquiler.alquilarLocker(locker, usuario)){
            modelo.put("error", "Alquiler exitoso");
            return new ModelAndView("lista-alquileres", modelo);
        }

        modelo.put("error", "Locker no disponible");
        return new ModelAndView("lista-alquileres", modelo);
    }
}
