package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Localidad;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioLocalidad;

@Service("servicioLocalidad")
@Transactional
public class ServicioLocalidadImpl implements ServicioLocalidad {

	private RepositorioLocalidad servicioLocalidadDAO;

	@Autowired
	public ServicioLocalidadImpl(RepositorioLocalidad servicioLocalidadDAO) {
		super();
		this.servicioLocalidadDAO = servicioLocalidadDAO;
	}

	@Override
	public List<Localidad> obtenerLocalidades() {
		return servicioLocalidadDAO.listarLocalidades();
	}

}
