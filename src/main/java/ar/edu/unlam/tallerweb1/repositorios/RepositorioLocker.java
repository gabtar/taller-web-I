package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Locker;


public interface RepositorioLocker {

	boolean alquilarLocker(Locker locker);

	Boolean getEstadoLocker(Locker locker);

	Object setEstadoLocker(boolean b);

	List<Locker> buscarLockers();

	Locker buscarLockersPorId(int id);

}
