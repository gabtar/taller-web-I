package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.modelo.Localidad;

@Service
public interface ServicioLocalidad {

	List<Localidad> obtenerLocalidades();
	
}
