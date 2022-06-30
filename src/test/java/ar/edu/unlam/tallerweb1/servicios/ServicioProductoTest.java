package ar.edu.unlam.tallerweb1.servicios;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioLocker;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;

public class ServicioProductoTest {
	ServicioProductoImpl servicioProducto;
	RepositorioLocker repositorioLocker;
	RepositorioUsuario repositorioUsuario;

		@Before
		public void setUp() throws Exception {
			repositorioLocker=mock(RepositorioLocker.class);
			repositorioUsuario=mock(RepositorioUsuario.class);
			servicioProducto = new ServicioProductoImpl(repositorioLocker,repositorioUsuario);
		}
	@Test
	public void queSeValidenLosDatos() {
		dadoQueTengoLosDatos();
		ingresoLosDatos();
		verificoSiSeLLamoALaFuncion();
	}
	private void verificoSiSeLLamoALaFuncion() {
		// TODO Auto-generated method stub
		verify(repositorioLocker, times(1)).validarCodigo(1, "pepe", "123456");
	}
	private void ingresoLosDatos() {
		// TODO Auto-generated method stub
		servicioProducto.validacionDatos("pepe","123456",1);
	}
	private void dadoQueTengoLosDatos() {
		// TODO Auto-generated method stub
		
	}
		
}
