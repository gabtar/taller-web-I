package ar.edu.unlam.tallerweb1.servicios;

public interface ServicioCodigo {
	
	public void generarCodigo(String usuario, int lockerId);
	
	public void validarCodigo(String usuario, String codigo, int lockerId);

}
