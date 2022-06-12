package ar.edu.unlam.tallerweb1.servicios;


import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.DatosGestorAlquiler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Locker;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioLocker;

@Service("servicioHome")
@Transactional
public class ServicioAlquilerImpl implements ServicioAlquiler {
	
	private RepositorioLocker repositorioLockerDAO;
	
	@Autowired
	public ServicioAlquilerImpl(RepositorioLocker repositorioLockerDAO){
		this.repositorioLockerDAO = repositorioLockerDAO;
	}
	
	@Override
	public Boolean alquilarLocker(Locker locker,Usuario usuario) {
		if(!repositorioLockerDAO.getEstadoLocker(locker)) {
			repositorioLockerDAO.alquilarLocker(locker,usuario);
			return true;		}
		return false;
	}

	public Boolean getEstadoLocker(Locker locker) {
		// TODO Auto-generated method stub
		return repositorioLockerDAO.getEstadoLocker(locker);
	}

	public List<Locker> verAlquileresPropios(Usuario usuario) {
		// TODO Auto-generated method stub
		return repositorioLockerDAO.buscarAlquileresActivosDeUsuario(usuario);
	}

	@Override
	public List<Locker> buscarAlquileresDisponibles() {
		// TODO Auto-generated method stub
		return (List) repositorioLockerDAO.buscarLockers();
	}

	@Override
	public List<DatosGestorAlquiler> GestinarAlquilerUsuario(Usuario usuario) {
		List<DatosGestorAlquiler> gestor =repositorioLockerDAO.GestorAlquileresDelUsuario(usuario);
		return gestor;
	}

	@Override
	public void ModificarNotaDeLocker(Locker locker, String texto) {

	}


}
