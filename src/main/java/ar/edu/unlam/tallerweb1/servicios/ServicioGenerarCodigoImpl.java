package ar.edu.unlam.tallerweb1.servicios;

 

import ar.edu.unlam.tallerweb1.repositorios.RepositorioLocker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("servicioGenerarCodigo")
@Transactional
public class ServicioGenerarCodigoImpl implements ServicioGenerarCodigo{
	
	private ServicioEmail servicioEmail;
	private RepositorioLocker repositorioLocker;
	
	@Autowired
	public void ServicioGenerarCodigo(ServicioEmail servicioEmail, RepositorioLocker repositorioLocker){
		this.servicioEmail = servicioEmail;
		this.repositorioLocker = repositorioLocker;
	}
	
	@Override
	public void generarCodigo(String usuario, int lockerId) {
		String codigo="";
		for (int i=0 ;i<6 ; i++) {
			int numero= (int)(Math.random()*10);
			codigo+= numero;
		}
		enviarCodigo(usuario,codigo, lockerId);
	}

	private void enviarCodigo(String usuario,String codigo, int lockerId) {
		// TODO Auto-generated method stub
		
		servicioEmail.enviarMail(usuario,"RENT-LOCK", "codigo de desbloqueo es "+ codigo);
		repositorioLocker.guardarCodigo(lockerId, codigo);
	}



}
