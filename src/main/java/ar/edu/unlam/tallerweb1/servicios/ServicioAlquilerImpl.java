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
	public Boolean alquilarLocker(int lockerId, Long usuarioId) {
		if(!repositorioLockerDAO.getEstadoLocker(lockerId)) {
			repositorioLockerDAO.alquilarLocker(lockerId, usuarioId);
			return true;
		}
		return false;
	}
	@Override
	public void cancelarLocker(int lockerId, Long usuarioId) {
		repositorioLockerDAO.cancelarLocker(lockerId, usuarioId);
	}
	public Boolean getEstadoLocker(int lockerId) {
		// TODO Auto-generated method stub
		return repositorioLockerDAO.getEstadoLocker(lockerId);
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
	public void ModificarNotaDeLocker(int lockerId, String texto) {
		repositorioLockerDAO.ModificarNotaDeLocker(lockerId, texto);
	}

	@Override
	public String NotaDelocker(int lockerId) {
		return repositorioLockerDAO.NotaDelLocker(lockerId);
	}

	@Override
	public List<Locker> buscarLockersDisponiblesPorSucursal(Long idSucursal) {
		return repositorioLockerDAO.buscarLockersDisponiblesPorSucursal(idSucursal);
	}
}
