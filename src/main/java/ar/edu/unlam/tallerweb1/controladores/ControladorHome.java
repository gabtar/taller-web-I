package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.DatosRegistro;
import ar.edu.unlam.tallerweb1.modelo.Locker;
import ar.edu.unlam.tallerweb1.modelo.Producto;
import ar.edu.unlam.tallerweb1.servicios.ServicioHome;
import ar.edu.unlam.tallerweb1.servicios.ServicioRegistro;

public class ControladorHome {

	private ServicioHome servicioHome;

	public ControladorHome(ServicioHome servicioHome) {
		this.servicioHome = servicioHome;
	}

	public ModelAndView guardarProducto(Producto producto, Locker locker) {
		ModelMap modelo = new ModelMap();
		
		if(servicioHome.guardarProducto(producto,locker)) {
			modelo.put("producto", producto);
			modelo.put("locker", locker);
			modelo.put("error", "producto guardado con exito");
			return new ModelAndView("home", modelo);
			
		}
		modelo.put("error", "sucedio un error en su solicitud");
		return new ModelAndView ("home",modelo);
		
		
	}
}
