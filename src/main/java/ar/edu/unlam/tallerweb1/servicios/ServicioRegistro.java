package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface ServicioRegistro {
	
	void registrar(String email, String contrasenia);

	Usuario buscarUsuarioPorEmail(String email);

}
