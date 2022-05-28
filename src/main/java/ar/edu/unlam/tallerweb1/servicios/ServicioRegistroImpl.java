package ar.edu.unlam.tallerweb1.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;

@Service("servicioRegistro")
@Transactional
public class ServicioRegistroImpl implements ServicioRegistro {

	private RepositorioUsuario servicioRegistroDAO;
	
	@Autowired	
	public ServicioRegistroImpl(RepositorioUsuario servicioRegistroDAO) {
		super();
		this.servicioRegistroDAO = servicioRegistroDAO;
	}

	@Override
	public void registrar(String email, String contrasenia) {
		Usuario nuevoUsuario = new Usuario();
		nuevoUsuario.setEmail(email);
		nuevoUsuario.setPassword(contrasenia);
		
		servicioRegistroDAO.guardar(nuevoUsuario);
	}

}
