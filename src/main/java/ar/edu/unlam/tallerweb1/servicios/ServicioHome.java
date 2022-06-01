package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Locker;
import ar.edu.unlam.tallerweb1.modelo.Producto;

public interface ServicioHome {

	 void guardarProducto(Producto producto, Locker locker);
}
