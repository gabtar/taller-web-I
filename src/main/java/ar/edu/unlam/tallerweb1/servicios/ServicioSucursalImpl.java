package ar.edu.unlam.tallerweb1.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Sucursal;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioSucursal;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;

@Service("servicioSucursal")
@Transactional
public class ServicioSucursalImpl implements ServicioSucursal {
	
	private RepositorioSucursal repositorioSucursal;
	
	@Autowired
	public ServicioSucursalImpl(RepositorioSucursal repositorioSucursal){
		this.repositorioSucursal = repositorioSucursal;
	}

	@Override
	public List<Sucursal> buscarSucursal(String localidad) {
		// TODO Auto-generated method stub
		return repositorioSucursal.buscarPorLocalidad(localidad);
	}

	@Override
	public List<Sucursal> listarSucursales() {
		List <Sucursal> lista= repositorioSucursal.listarSucursales();
		if(lista==null){
			lista= new ArrayList<>();
		}

		return lista;
	}

}
