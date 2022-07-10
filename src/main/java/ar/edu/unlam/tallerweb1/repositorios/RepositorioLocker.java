package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Locker;
import ar.edu.unlam.tallerweb1.modelo.Usuario;


public interface RepositorioLocker {

	boolean alquilarLocker(int lockerId, Long usuarioId);

	void setUsuarioALocker(Long usuarioId, int lockerId);
	boolean cancelarLocker(int lockerId, Long usuarioId);

	Boolean getEstadoLocker(int lockerId);

	void setEstadoLocker(int lockerId);

	List<Locker> buscarLockersLibres();

	Locker buscarLockerPorId(int id);

	List<Locker> buscarLockersPorUsuario(Usuario usuario);

	void modificarNotaDeLocker(int lockerId, String texto);

	List<Locker> buscarLockersDisponiblesPorSucursal(Long idSucursal);

	List<Locker> buscarLockersDisponiblesPorSucursalYTamanio(String nombreSucursal, String tamanio);

	void guardarCodigo(int lockerId, String codigo);

	void borrarCodigoALocker(Integer lockerId);

	void actualizarLocker(Locker locker);
	
}
