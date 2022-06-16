package ar.edu.unlam.tallerweb1.servicios;

 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("servicioGenerarCodigo")
@Transactional
public class ServicioGenerarCodigoImpl implements ServicioGenerarCodigo{
	
	private ServicioEmail servicioEmail;
	
	@Autowired
	public void ServicioGenerarCodigo(ServicioEmail servicioEmail){
		this.servicioEmail = servicioEmail;
	}
	
	@Override
	public void generarCodigo(String usuario) {
		String codigo="";
		for (int i=0 ;i<6 ; i++) {
			int numero= (int)(Math.random()*10);
			codigo+= numero;
		}
		enviarCodigo(usuario,codigo);
	}

	private void enviarCodigo(String usuario,String codigo) {
		// TODO Auto-generated method stub
		
		servicioEmail.enviarMail(usuario,"RENT-LOCK", "codigo de desbloqueo es "+ codigo);
	}



}
