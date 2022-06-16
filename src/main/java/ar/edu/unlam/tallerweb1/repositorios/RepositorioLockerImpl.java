package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.DatosGestorAlquiler;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Locker;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

@Repository("repositorioLocker")

public class RepositorioLockerImpl implements RepositorioLocker{
	
	private SessionFactory sessionFactory;
	@Autowired
	public RepositorioLockerImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	@Override
	public boolean alquilarLocker(int lockerId,Long usuarioId) {

		setEstadoLocker(lockerId);
		setUsuarioALocker(usuarioId, lockerId);
		return true;
	}

	@Override
	public void cancelarLocker(int lockerId,Long usuarioId) {
		setEstadoLocker(lockerId);
		setUsuarioALocker(usuarioId, lockerId);
	}

	@Override
	public void setUsuarioALocker(Long usuarioId, int lockerId) {
		final Session session = sessionFactory.getCurrentSession();
		Locker locker = (Locker) session.createCriteria(Locker.class).add(Restrictions.eq("id", lockerId)).uniqueResult();
		if(locker.getUsuarioId() == null){

			locker.setUsuario(usuarioId);
		}
		else{

			locker.setUsuario(null);
		}
		session.update(locker);
	}

	@Override
	public Boolean getEstadoLocker(int lockerId) {

		final Session session = sessionFactory.getCurrentSession();
		Locker locker = (Locker) session.createCriteria(Locker.class).add(Restrictions.eq("id", lockerId)).uniqueResult();
		Boolean estado = locker.isOcupado();
		return estado;
	}

	@Override
	public void setEstadoLocker(int lockerId) {
		final Session session = sessionFactory.getCurrentSession();
		Locker locker = (Locker) session.createCriteria(Locker.class).add(Restrictions.eq("id",lockerId)).uniqueResult();
		if(!locker.isOcupado()){
			locker.setOcupado(true);
		}
		else{
			locker.setOcupado(false);
		}

		session.update(locker);
	}

	@Override
	public List<Locker> buscarLockers() {
		final Session session = sessionFactory.getCurrentSession();
		
		return session.createCriteria(Locker.class).add(Restrictions.eq("ocupado", Boolean.FALSE )).list();
	}

	@Override
	public Locker buscarLockersPorId(int id) {
		return (Locker)  sessionFactory.getCurrentSession().createCriteria(Locker.class)
				.add(Restrictions.eq("id", id)).uniqueResult();
		
	}


	@Override
	public List<Locker> buscarAlquileresActivosDeUsuario(Usuario usuario) {
		final Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Locker.class).add(Restrictions.eq("usuarioId", usuario.getId())).list();
	}


	@Override
	public Locker buscarLockersPorUsuario(Usuario usuario) {
		return (Locker)  sessionFactory.getCurrentSession().createCriteria(Locker.class)
				.add(Restrictions.eq("usuarioId", usuario.getId())).uniqueResult();
	}

	@Override
	public List<DatosGestorAlquiler> GestorAlquileresDelUsuario(Usuario usuario) {

		// esto es lo que tenia que modificar gabriel
		final Session session = sessionFactory.getCurrentSession();
		long numero= usuario.getId();
		String sql="SELECT * FROM Locker JOIN Sucursal on locker.idSucursal = sucursal.id WHERE idSucursal="+numero+")";
		return (List<DatosGestorAlquiler>) session.createNativeQuery(sql);
	}

	@Override
	public String NotaDelLocker(long l) {
		//String nota= String.valueOf(sessionFactory.getCurrentSession().createNativeQuery("select textoDelUsuario from locker where id=l").setParameter("l",l));
		final Session session = sessionFactory.getCurrentSession();
		Locker locker = (Locker) session.createCriteria(Locker.class).add(Restrictions.eq("id",l)).uniqueResult();
		String nota = locker.getTextoDelUsuario();
		return nota;
	}
	@Override
	public void ModificarNotaDeLocker(int lockerId, String texto) {
		final Session session = sessionFactory.getCurrentSession();
		Locker locker = (Locker) session.createCriteria(Locker.class).add(Restrictions.eq("id",lockerId)).uniqueResult();
		locker.setTextoDelUsuario(texto);
		session.update(locker);
	}
}
