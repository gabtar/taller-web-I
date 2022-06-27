package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Sucursal;

public interface ServicioSucursal {
	
	List<Sucursal> buscarSucursal(String localidad);
    List<Sucursal>  listarSucursales();
	Sucursal buscarSucursalPorId(Long idSucursal);
}
