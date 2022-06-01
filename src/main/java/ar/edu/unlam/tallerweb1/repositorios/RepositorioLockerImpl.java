package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Locker;
import ar.edu.unlam.tallerweb1.modelo.Sucursal;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

@Repository("repositorioLocker")

public class RepositorioLockerImpl implements RepositorioLocker{

	private SessionFactory sessionFactory;
	
	@Autowired
	public RepositorioLockerImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	@Override
	public boolean alquilarLocker(Locker locker) {
		if(getEstadoLocker(locker)) {
			
			return false;
		}setEstadoLocker(false);
		return true;
	}

	@Override
	public Boolean getEstadoLocker(Locker locker) {
		return   true;
	}

	@Override
	public Object setEstadoLocker(boolean b) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Locker> buscarLockers() {
		final Session session = sessionFactory.getCurrentSession();
		
		return session.createCriteria(Locker.class).list();
	}

	@Override
	public Locker buscarLockersPorId(int id) {

		return (Locker)  sessionFactory.getCurrentSession().createCriteria(Locker.class)
				.add(Restrictions.like("id", id)).uniqueResult();
		
	}
}
