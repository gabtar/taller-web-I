package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Alquiler;

@Repository("repositorioAlquiler")
public class RepositorioAlquilerImpl implements RepositorioAlquiler {

	private SessionFactory sessionFactory;

	@Autowired
	public RepositorioAlquilerImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void nuevoAlquiler(Alquiler alquiler) {
		sessionFactory.getCurrentSession().save(alquiler);
	}

	@Override
	public void modificar(Alquiler alquiler) {
		sessionFactory.getCurrentSession().update(alquiler);
	}

	@Override
	public List<Alquiler> listarAlquileresDelUsuario(Long usuarioId) {
		return (List<Alquiler>) sessionFactory.getCurrentSession().createCriteria(Alquiler.class)
				.createAlias("usuario", "user")
				.add(Restrictions.eq("user.id", usuarioId))
				.list();
		}
}
