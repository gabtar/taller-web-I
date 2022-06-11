package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Sucursal;
import ar.edu.unlam.tallerweb1.servicios.ServicioSucursal;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ControladorHome {

    private static ServicioSucursal servicioSucursal;
    @Autowired
    public ControladorHome(ServicioSucursal servicioSucursal) {
        this.servicioSucursal=servicioSucursal;

    }
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public static ModelAndView inicio() {
        ModelMap modelo = new ModelMap();
        List<Sucursal> lista =servicioSucursal.listarSucursales();
        modelo.put("listaSucursales", lista);
        return new ModelAndView("home", modelo);
    }
    @RequestMapping(path = "/home", method = RequestMethod.GET)
    public static ModelAndView home() {
        ModelMap modelo = new ModelMap();
        List<Sucursal> lista =servicioSucursal.listarSucursales();
        modelo.put("listaSucursales", lista);
        return new ModelAndView("home", modelo);
    }
}
