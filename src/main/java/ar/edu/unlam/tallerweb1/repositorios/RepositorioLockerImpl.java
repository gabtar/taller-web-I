package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Locker;
import ar.edu.unlam.tallerweb1.modelo.Sucursal;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

@Repository("repositorioLocker")

public class RepositorioLockerImpl implements RepositorioLocker{
	
	private SessionFactory sessionFactory;
	private RepositorioUsuario repositorioUsuario;
	
	@Autowired
	public RepositorioLockerImpl(SessionFactory sessionFactory,RepositorioUsuario repositorioUsuario) {
		this.sessionFactory = sessionFactory;
		this.repositorioUsuario=repositorioUsuario;
	}


	@Override
	public boolean alquilarLocker(Locker locker,Usuario usuario) {
		if(getEstadoLocker(locker)) {
			
			return false;
		}
		setEstadoLocker(locker, true);
		setUsuarioALocker(usuario,locker);
		return true;
	}

	private void setUsuarioALocker(Usuario usuario,Locker locker) {
		locker.setUsuario(usuario);
		
	}


	@Override
	public Boolean getEstadoLocker(Locker locker) {
		//return (Boolean) sessionFactory.getCurrentSession().createQuery("SELECT ocupado FROM Locker WHERE id = :id")
			//	.setParameter("id", locker.getId())
				//.uniqueResult();
		return   locker.isOcupado();
	}

	@Override
	public void setEstadoLocker(Locker locker,Boolean b) {
		//sessionFactory.getCurrentSession().createQuery("UPDATE Locker  SET ocupado = :b WHERE id = :id")
		//.setParameter("b", b)
		//.setParameter("id", locker.getId()).executeUpdate();
		locker.setOcupado(b);
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


	@Override
	public Locker buscarAlquileresActivosDeUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Locker buscarLockersPorUsuario(Usuario usuario) {
		return (Locker)  sessionFactory.getCurrentSession().createCriteria(Locker.class)
				.add(Restrictions.eq("usuarioId", usuario.getId())).uniqueResult();
	}
}
