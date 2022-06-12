package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.DatosGestorAlquiler;
import ar.edu.unlam.tallerweb1.modelo.Locker;
import ar.edu.unlam.tallerweb1.modelo.Usuario;


public interface RepositorioLocker {

	boolean alquilarLocker(Locker locker,Usuario usuario);

	Boolean getEstadoLocker(Locker locker);

	void setEstadoLocker(Locker locker,Boolean b);

	List<Locker> buscarLockers();

	Locker buscarLockersPorId(int id);

	List<Locker> buscarAlquileresActivosDeUsuario(Usuario usuario);

	Locker buscarLockersPorUsuario(Usuario usuario);



	List<DatosGestorAlquiler> GestorAlquileresDelUsuario(Usuario usuario);
}
