package ar.edu.unlam.tallerweb1.servicios;

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
	
	private RepositorioSucursal servicioSucursalDao;
	
	@Autowired
	public ServicioSucursalImpl(RepositorioSucursal servicioSucursalDao){
		this.servicioSucursalDao = servicioSucursalDao;
	}

	@Override
	public List<Sucursal> buscarSucursal(String localidad) {
		// TODO Auto-generated method stub
		return servicioSucursalDao.buscarPorLocalidad(localidad);
	}

}
