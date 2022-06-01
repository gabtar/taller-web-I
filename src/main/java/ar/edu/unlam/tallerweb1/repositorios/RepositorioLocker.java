package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Locker;
import ar.edu.unlam.tallerweb1.modelo.Producto;

public interface RepositorioLocker {
	Locker buscarProductoEnLocker(Producto producto);

	

	boolean guardarProductoEnLocker(Producto producto, Locker locker); 
}
