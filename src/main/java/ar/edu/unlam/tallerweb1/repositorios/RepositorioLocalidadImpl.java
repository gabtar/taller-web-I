package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Localidad;

@Repository("repositorioLocalidad")
public class RepositorioLocalidadImpl implements RepositorioLocalidad {

	private SessionFactory sessionFactory;

	@Autowired
	public RepositorioLocalidadImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Localidad> listarLocalidades() {
		final Session session = sessionFactory.getCurrentSession();

		return session.createCriteria(Localidad.class).list();
	}

}
