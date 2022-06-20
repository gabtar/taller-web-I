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

		return session.createCriteria(Sucursal.class).createAlias("localidad", "loc")
				.add(Restrictions.like("loc.nombre", localidad, MatchMode.ANYWHERE).ignoreCase()).list();
	}

	@Override
	public List<Sucursal> listarSucursales() {
		// de esta forma se llama a una seccion
		final Session session = sessionFactory.getCurrentSession();
		// mundo DB
		List lista1 = session.createSQLQuery("select * FROM Sucursal").list();
		// mundo objetos Hibernet query lenguage
		// session.createQuery();
		// mundo objeto
		List lista2 = session.createCriteria(Sucursal.class).list();
		return lista2;
	}

	@Override
	public Sucursal buscarSucursalporId() {
		return null;
	}

}
