package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Sucursal;
import ar.edu.unlam.tallerweb1.servicios.ServicioSucursal;

@Controller
public class ControladorSucursal {

	@Autowired
	private ServicioSucursal servicioSucursal;

	@Autowired
	public ControladorSucursal(ServicioSucursal servicioSucursal) {
		this.servicioSucursal = servicioSucursal;
	}

	// Por get obtiene el nombre de la sucursal a buscar
	// proyecto-limpio-spring/sucursales?nombre=Ramos
	@RequestMapping("/sucursales")
	public ModelAndView mostrarSucursales(@RequestParam(required = false) String localidad) {

		ModelMap modelo = new ModelMap();

		List<Sucursal> listaDeSucursales;

		listaDeSucursales = this.servicioSucursal.buscarSucursal(localidad);
		modelo.addAttribute("sucursales", listaDeSucursales);

		if (listaDeSucursales.isEmpty()) {
			modelo.put("error", "No se encontraron sucursales");
		}

		modelo.put("busqueda", localidad);

		return new ModelAndView("lista-sucursales", modelo);
	}

}
