package ar.edu.unlam.tallerweb1.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Locker;
import ar.edu.unlam.tallerweb1.modelo.Producto;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioLocker;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;

@Service("servicioHome")
@Transactional
public class ServicioHomeImpl implements ServicioHome{
	
	private RepositorioLocker repositorioLockerDAO;
	
	@Autowired
	public ServicioHomeImpl(RepositorioLocker repositorioLockerDAO){
		this.repositorioLockerDAO = repositorioLockerDAO;
	}
	
	@Override
	public void guardarProducto(Producto producto, Locker locker) {
		
		repositorioLockerDAO.guardarProductoEnLocker(producto,locker);
	}

}
