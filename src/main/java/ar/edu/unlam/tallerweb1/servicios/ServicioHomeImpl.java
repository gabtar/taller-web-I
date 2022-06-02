package ar.edu.unlam.tallerweb1.servicios;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Locker;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
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
	public Boolean alquilarLocker(Locker locker,Usuario usuario) {
		if(!repositorioLockerDAO.getEstadoLocker(locker)) {
			
			repositorioLockerDAO.alquilarLocker(locker,usuario);
			return true;
		}
		return false;
	}

	public Boolean getEstadoLocker(Locker locker) {
		// TODO Auto-generated method stub
		return repositorioLockerDAO.getEstadoLocker(locker);
	}

	public Locker verAlquileresPropios(Usuario usuario) {
		// TODO Auto-generated method stub
		return repositorioLockerDAO.buscarAlquileresActivosDeUsuario(usuario);
	}

	
	

}
