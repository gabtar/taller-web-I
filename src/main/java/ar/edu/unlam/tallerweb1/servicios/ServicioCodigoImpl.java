package ar.edu.unlam.tallerweb1.servicios;

 

import ar.edu.unlam.tallerweb1.modelo.Codigo;
import ar.edu.unlam.tallerweb1.modelo.CodigoExpiradoException;
import ar.edu.unlam.tallerweb1.modelo.CodigoInvalidoException;
import ar.edu.unlam.tallerweb1.modelo.Locker;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioLocker;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("servicioGenerarCodigo")
@Transactional
public class ServicioCodigoImpl implements ServicioCodigo{
	
	private ServicioEmail servicioEmail;
	private RepositorioLocker repositorioLocker;
	
	@Autowired
	public ServicioCodigoImpl(ServicioEmail servicioEmail, RepositorioLocker repositorioLocker){
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

	@Override
	public void validarCodigo(String usuario, String codigo, int lockerId) throws CodigoInvalidoException, CodigoExpiradoException{
		final Locker locker = repositorioLocker.buscarLockerPorId(lockerId);
		Codigo codigoApertura = locker.getCodigoApertura();
		String codApertura = null;
		if(codigoApertura != null) codApertura = codigoApertura.getCodigo();
		
		if(!usuario.equals(locker.getPropietario().getEmail()) || !codigo.equals(codApertura)) {
			throw new CodigoInvalidoException("Código o Usuario inválido");
		}
		
		if(codigoApertura.getFechaVencimiento().before(Calendar.getInstance().getTime())) {
			throw new CodigoExpiradoException("El código ha expirado. Ya no es válido");
		}
		
		repositorioLocker.borrarCodigoALocker(lockerId);
	}

}
