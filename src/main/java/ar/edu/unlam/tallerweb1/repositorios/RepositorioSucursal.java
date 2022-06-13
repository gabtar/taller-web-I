package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Sucursal;

public interface RepositorioSucursal {
	
	List<Sucursal> buscarPorLocalidad(String localidad);

    List<Sucursal> listarSucursales();

     Sucursal buscarSucursalporId();
}
