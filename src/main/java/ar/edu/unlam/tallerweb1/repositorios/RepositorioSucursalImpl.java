package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Sucursal;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

@Repository("repositorioSucursal")
public class RepositorioSucursalImpl implements RepositorioSucursal {
	
	private SessionFactory sessionFactory;
	
	@Autowired
	public RepositorioSucursalImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Sucursal> buscarPorLocalidad(String localidad) {
		final Session session = sessionFactory.getCurrentSession();
		
		return session.createCriteria(Sucursal.class)
				.add(Restrictions.like("localidad", localidad,MatchMode.ANYWHERE))
				.list();
	}

}
