package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.servicios.ServicioAlquiler;
import ar.edu.unlam.tallerweb1.servicios.ServicioGenerarCodigo;
import ar.edu.unlam.tallerweb1.servicios.ServicioProducto;
import ar.edu.unlam.tallerweb1.servicios.ServicioSucursal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ControladorProducto {

    private ServicioProducto servicioProducto;
    private ServicioGenerarCodigo servicioGenerarCodigo;

    @Autowired
    public ControladorProducto(ServicioProducto servicioProducto, ServicioGenerarCodigo servicioGenerarCodigo) {
        this.servicioProducto = servicioProducto;
        this.servicioGenerarCodigo= servicioGenerarCodigo;
    }


    @RequestMapping(path = "/agregar-producto", method = RequestMethod.POST)
    public ModelAndView agregarProducto(HttpServletRequest request, @ModelAttribute("validarCodigo") ValidarCodigo validarCodigo) {

        ModelMap modelo = new ModelMap();
        if(servicioProducto.validacionDatos(validarCodigo.getNombre(), validarCodigo.getCodigo(), validarCodigo.getLockerId())) {
            modelo.put("error", "Producto ingresado correctamente");
            return new ModelAndView("validacionProducto", modelo);
        }

        modelo.put("error", "Los datos ingresados son invalidos");
        return  new ModelAndView("validacionProducto", modelo);

    }

    @RequestMapping(path = "/retirar-producto", method = RequestMethod.POST)
    public ModelAndView retirarProducto(HttpServletRequest request, @ModelAttribute("validarCodigo") ValidarCodigo validarCodigo) {

        ModelMap modelo = new ModelMap();
        if(servicioProducto.validacionDatos(validarCodigo.getNombre(), validarCodigo.getCodigo(), validarCodigo.getLockerId())) {
            modelo.put("error", "Producto retirado correctamente");
            return new ModelAndView("validacionProducto", modelo);
        }

        modelo.put("error", "Los datos ingresados son invalidos");
        return  new ModelAndView("validacionProducto", modelo);

    }
}
