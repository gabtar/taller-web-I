package ar.edu.unlam.tallerweb1.servicios;



import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Locker;
import ar.edu.unlam.tallerweb1.modelo.Usuario;


public interface ServicioHome {

	Boolean alquilarLocker(Locker locker,Usuario usuario);
	 Boolean getEstadoLocker(Locker locker);
	 Locker verAlquileresPropios(Usuario usuario);
	List<Locker> buscarAlquileresDisponibles();
}
