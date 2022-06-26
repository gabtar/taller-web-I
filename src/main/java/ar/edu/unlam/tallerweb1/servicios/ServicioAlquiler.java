package ar.edu.unlam.tallerweb1.servicios;



import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.DatosGestorAlquiler;
import ar.edu.unlam.tallerweb1.modelo.Locker;
import ar.edu.unlam.tallerweb1.modelo.Usuario;


public interface ServicioAlquiler {

	Boolean alquilarLocker(int lockerId, Long usuarioId);
	void cancelarLocker(int lockerId, Long usuarioId);
	 Boolean getEstadoLocker(int lockerId);
	List<Locker> verAlquileresPropios(Usuario usuario);
	List<Locker> buscarAlquileresDisponibles();

	List <DatosGestorAlquiler> GestinarAlquilerUsuario(Usuario usuario);

	void ModificarNotaDeLocker(int lockerId, String texto);

	String NotaDelocker(int lockerId);
	
	List<Locker> buscarLockersDisponiblesPorSucursal(Long idSucursal);
	
	List<Locker> buscarLockersDisponiblesPorSucursalYTamanio(Long idRamos, String tamanioChico);
}
