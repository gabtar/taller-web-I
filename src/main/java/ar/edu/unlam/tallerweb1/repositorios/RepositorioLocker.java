package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.DatosGestorAlquiler;
import ar.edu.unlam.tallerweb1.modelo.Locker;
import ar.edu.unlam.tallerweb1.modelo.Usuario;


public interface RepositorioLocker {

	boolean alquilarLocker(int lockerId, Long usuarioId);

	void setUsuarioALocker(Long usuarioId, int lockerId);
	boolean cancelarLocker(int lockerId, Long usuarioId);

	Boolean getEstadoLocker(int lockerId);

	void setEstadoLocker(int lockerId);

	List<Locker> buscarLockers();

	Locker buscarLockersPorId(int id);

	List<Locker> buscarAlquileresActivosDeUsuario(Usuario usuario);

	Locker buscarLockersPorUsuario(Usuario usuario);

	List<DatosGestorAlquiler> GestorAlquileresDelUsuario(Usuario usuario);

    String NotaDelLocker(long l);
	void ModificarNotaDeLocker(int lockerId, String texto);
}
