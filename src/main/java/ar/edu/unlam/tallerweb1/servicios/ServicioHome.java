package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Locker;


public interface ServicioHome {

	 Boolean alquilarLocker(Locker locker);
	 Boolean getEstadoLocker(Locker locker);
}
