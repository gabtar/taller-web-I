package ar.edu.unlam.tallerweb1.servicios;



import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Alquiler;
import ar.edu.unlam.tallerweb1.modelo.Locker;
import ar.edu.unlam.tallerweb1.modelo.Usuario;


public interface ServicioAlquiler {

	Boolean alquilarLocker(int lockerId, Long usuarioId);
	Boolean cancelarLocker(int lockerId, Long usuarioId);
	Boolean getEstadoLocker(int lockerId);
	void setEstadoAlquiler(Long alquilerId);
	List<Locker> verAlquileresPropios(Usuario usuario);
	List<Locker> buscarAlquileresDisponibles();

	void modificarNotaDeLocker(int lockerId, String texto);
	
	List<Locker> buscarLockersDisponiblesPorSucursal(Long idSucursal);
	
	List<Locker> buscarLockersDisponiblesPorSucursalYTamanio(String localidad, String tamanioChico);
	
	List<Alquiler> obtenerRegistroDeAlquileres(Long usuarioId);
	
}
