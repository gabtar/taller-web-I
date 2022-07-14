package ar.edu.unlam.tallerweb1.servicios;


import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Alquiler;
import ar.edu.unlam.tallerweb1.modelo.EstadoAlquiler;
import ar.edu.unlam.tallerweb1.modelo.Locker;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioAlquiler;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioLocker;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;

@Service("servicioHome")
@Transactional
public class ServicioAlquilerImpl implements ServicioAlquiler {
	
	private RepositorioLocker repositorioLockerDAO;
	private RepositorioAlquiler repositorioAlquilerDAO;
	private RepositorioUsuario repositorioUsuarioDAO;
	
	@Autowired
	public ServicioAlquilerImpl(RepositorioLocker repositorioLockerDAO, RepositorioAlquiler repositorioAlquilerDAO, RepositorioUsuario repositorioUsuarioDAO){
		this.repositorioLockerDAO = repositorioLockerDAO;
		this.repositorioAlquilerDAO = repositorioAlquilerDAO;
		this.repositorioUsuarioDAO = repositorioUsuarioDAO;
	}
	
	@Override
	public Boolean alquilarLocker(int lockerId, Long usuarioId) {
		// TODO, el método debería ser void y lanzar exception si el locker
		// ya está alquilado
		
		if(!repositorioLockerDAO.getEstadoLocker(lockerId)) {
			
			Alquiler alquilerLocker = new Alquiler();
			Locker locker = repositorioLockerDAO.buscarLockerPorId(lockerId);
			Usuario usuario = repositorioUsuarioDAO.buscarUsuarioPorId(usuarioId);
			alquilerLocker.setUsuario(usuario);
			alquilerLocker.setLocker(locker);
			
			repositorioAlquilerDAO.nuevoAlquiler(alquilerLocker);
			
			// Asocio el alquiler al locker y hago update al locker en la db
			// Asi después cuando se finaliza el alquiler se puede moficar la fecha
			locker.setAlquilerActivo(alquilerLocker);
			repositorioLockerDAO.actualizarLocker(locker);
			
			repositorioLockerDAO.alquilarLocker(lockerId, usuarioId);
			
			return true;
		}
		return false;
	}
	@Override
	public Boolean cancelarLocker(int lockerId, Long usuarioId) {
		if(repositorioLockerDAO.getEstadoLocker(lockerId)) {
			
			Locker locker = repositorioLockerDAO.buscarLockerPorId(lockerId);
			Alquiler alquilerActual = locker.getAlquilerActivo();
			
			// Libera el locker y escribe los datos del alquiler
			locker.setAlquilerActivo(null);
			locker.setCodigoApertura(null);
			alquilerActual.setFechaFinalizacion(new Date(Calendar.getInstance().getTimeInMillis()));
			alquilerActual.setEstadoAlquiler(EstadoAlquiler.FINALIZADO);
			Long duracionAlquiler=(long) (alquilerActual.getFechaFinalizacion().getTime()-alquilerActual.getFechaInicio().getTime());
			TimeUnit time = TimeUnit.DAYS; 
	        long diffrence = time.convert(duracionAlquiler, TimeUnit.MILLISECONDS);
	        if(diffrence < 1) {
	        	diffrence = 1;
	        }
	        
			Integer precio=(int) (diffrence*alquilerActual.getLocker().getTamanio().getPrecio());
			alquilerActual.setPrecio(precio);
			repositorioAlquilerDAO.modificar(alquilerActual);
			repositorioLockerDAO.actualizarLocker(locker);
			
			repositorioLockerDAO.cancelarLocker(lockerId, usuarioId);
			
			return true;
		}

		return false;
	}
	public Boolean getEstadoLocker(int lockerId) {
		return repositorioLockerDAO.getEstadoLocker(lockerId);
	}

	public List<Locker> verAlquileresPropios(Usuario usuario) {
		return repositorioLockerDAO.buscarLockersPorUsuario(usuario);
	}

	@Override
	public List<Locker> buscarAlquileresDisponibles() {
		return (List<Locker>) repositorioLockerDAO.buscarLockersLibres();
	}

	@Override
	public void modificarNotaDeLocker(int lockerId, String texto) {
		repositorioLockerDAO.modificarNotaDeLocker(lockerId, texto);
	}

	@Override
	public List<Locker> buscarLockersDisponiblesPorSucursal(Long idSucursal) {
		return repositorioLockerDAO.buscarLockersDisponiblesPorSucursal(idSucursal);
	}

	@Override
	public List<Locker> buscarLockersDisponiblesPorSucursalYTamanio(String localidad, String tamanio) {
		return repositorioLockerDAO.buscarLockersDisponiblesPorSucursalYTamanio(localidad, tamanio);
	}

	@Override
	public List<Alquiler> obtenerRegistroDeAlquileres(Long usuarioId) {
		return repositorioAlquilerDAO.listarAlquileresDelUsuario(usuarioId);
	}

	@Override
	public void setEstadoAlquiler(Long alquilerId) {
		// TODO Auto-generated method stub
		Alquiler alquiler= repositorioAlquilerDAO.buscarAlquilerPorId(alquilerId);
		alquiler.setEstadoAlquiler(EstadoAlquiler.PAGADO);
		repositorioAlquilerDAO.modificar(alquiler);
	}
}
