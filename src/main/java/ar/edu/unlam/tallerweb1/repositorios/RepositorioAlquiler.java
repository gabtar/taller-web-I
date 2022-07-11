package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Alquiler;

public interface RepositorioAlquiler {

	void nuevoAlquiler(Alquiler alquiler);
	
	void modificar(Alquiler alquiler);

	List<Alquiler> listarAlquileresDelUsuario(Long usuarioId);

}
