package ar.edu.unlam.tallerweb1.servicios;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ar.edu.unlam.tallerweb1.modelo.DatosGestorAlquiler;
import ar.edu.unlam.tallerweb1.modelo.Sucursal;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioSucursal;
import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.tallerweb1.modelo.Locker;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioLocker;

import java.util.ArrayList;
import java.util.List;

public class ServicioAlquilerTest {
	ServicioAlquiler servicioAlquiler;
	RepositorioLocker repositorioLockerDAO;
	ServicioSucursal servicioSucursal;
	RepositorioSucursal repositorioSucursal;
	Locker locker;
	Usuario usuario;

	@Before
	public void setUp() throws Exception {
		repositorioLockerDAO = mock(RepositorioLocker.class);
		locker= mock(Locker.class);
		usuario= mock(Usuario.class);
		servicioAlquiler = new ServicioAlquilerImpl(repositorioLockerDAO);
		repositorioSucursal = mock(RepositorioSucursal.class);
		servicioSucursal = new ServicioSucursalImpl(repositorioSucursal);

	}
	@Test
	public void queSePuedaAlquilarUnLocker() {
		when(repositorioLockerDAO.getEstadoLocker(locker)).thenReturn(false);
		Boolean actual = servicioAlquiler.alquilarLocker(locker,usuario);
		assertTrue(actual);
		
	}
	@Test
	public void queNoSePuedaAlquilarUnLocker() {
		when(repositorioLockerDAO.getEstadoLocker(locker)).thenReturn(true);
		Boolean actual = servicioAlquiler.alquilarLocker(locker,usuario);
		assertFalse(actual);
		
	}
	@Test
	public void queSePuedaObtenerElEstadoDeUnLocker() {
		when(repositorioLockerDAO.getEstadoLocker(locker)).thenReturn(false);
		Boolean actual = servicioAlquiler.getEstadoLocker(locker);
		assertFalse(actual);
	}
	@Test
	public void mostrarAlquileresDeUnUsuarioLlamaAlMetodoBuscarAlquileresPropios() {
		servicioAlquiler.verAlquileresPropios(usuario);
		verify(repositorioLockerDAO, times(1)).buscarAlquileresActivosDeUsuario(any());
	}


	@Test
	public void elServicioDeAlquilerCambiaTextoDelLocker(){
		Usuario usuario1= new Usuario();
		usuario1.setId(1L);
		Locker locker1= new Locker();
		locker1.setId(1);
		locker1.setTextoDelUsuario("asd");
		String texto="tiene Bananas";
		when(repositorioLockerDAO.NotaDelLocker(1L)).thenReturn(texto);
		servicioAlquiler.ModificarNotaDeLocker(locker1.getId(),texto);
		assertThat(servicioAlquiler.NotaDelocker(locker1.getId())).isEqualTo(texto);
	}
	@Test
	public void elServicioDeAlquilerCambiaTextoDelLocker2(){
		Usuario usuario1= new Usuario();
		usuario1.setId(1L);
		Locker locker1= new Locker();
		locker1.setId(1);
		locker1.setTextoDelUsuario("asd");
		String texto="tiene Bananas";
		when(repositorioLockerDAO.NotaDelLocker(1L)).thenReturn(texto);
		servicioAlquiler.ModificarNotaDeLocker(locker1.getId(),texto);
		assertThat(servicioAlquiler.NotaDelocker(locker1.getId())).isEqualTo(texto);
	}
	@Test
	public void elServicioDEAlguilerTieneUnMetodoParaMostrarTodoLosDatosDeLosAlquileres(){
		// preparacion
		Usuario usuario1 = new Usuario();
		usuario1.setId(1L);
		Sucursal sucursal1=new Sucursal();
		sucursal1.setId(1L);
		Locker locker1 = new Locker();
		locker1.setId(1);
		locker1.setIdSucursal(1L);
		locker1.setUsuarioId(1L);

		List <DatosGestorAlquiler> lista= new ArrayList<>();
		DatosGestorAlquiler datos= new DatosGestorAlquiler();
		lista.add(datos);
		when(repositorioLockerDAO.GestorAlquileresDelUsuario(usuario1)).thenReturn(lista);

		// ejecucion
		List<DatosGestorAlquiler> gestor = servicioAlquiler.GestinarAlquilerUsuario(usuario1);
		// comparacion
		assertThat(gestor.size()).isEqualTo(1);
	}

}
