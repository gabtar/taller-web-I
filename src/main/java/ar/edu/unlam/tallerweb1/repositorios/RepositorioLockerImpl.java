package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.access.SimpleRemoteStatelessSessionProxyFactoryBean;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Codigo;
import ar.edu.unlam.tallerweb1.modelo.Locker;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

@Repository("repositorioLocker")

public class RepositorioLockerImpl implements RepositorioLocker {

	private SessionFactory sessionFactory;

	@Autowired
	public RepositorioLockerImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean alquilarLocker(int lockerId, Long usuarioId) {

		setEstadoLocker(lockerId);
		setUsuarioALocker(usuarioId, lockerId);
		return true;
	}

	@Override
	public boolean cancelarLocker(int lockerId, Long usuarioId) {
		setEstadoLocker(lockerId);
		setUsuarioALocker(usuarioId, lockerId);
		return true;
	}

	@Override
	public void setUsuarioALocker(Long usuarioId, int lockerId) {
		final Session session = sessionFactory.getCurrentSession();
		Locker locker = (Locker) session.createCriteria(Locker.class).add(Restrictions.eq("id", lockerId))
				.uniqueResult();
		Usuario usuario = (Usuario) session.createCriteria(Usuario.class).add(Restrictions.eq("id", usuarioId))
				.uniqueResult();
		
		if (locker.getPropietario() == null) {

			locker.setPropietario(usuario);
		} else {

			locker.setPropietario(null);
		}
		session.update(locker);
	}

	@Override
	public Boolean getEstadoLocker(int lockerId) {

		final Session session = sessionFactory.getCurrentSession();
		Locker locker = (Locker) session.createCriteria(Locker.class).add(Restrictions.eq("id", lockerId))
				.uniqueResult();
		Boolean estado = locker.isOcupado();
		return estado;
	}

	@Override
	public void setEstadoLocker(int lockerId) {
		final Session session = sessionFactory.getCurrentSession();
		Locker locker = (Locker) session.createCriteria(Locker.class).add(Restrictions.eq("id", lockerId))
				.uniqueResult();
		if (!locker.isOcupado()) {
			locker.setOcupado(true);
		} else {
			locker.setOcupado(false);
			locker.setTextoDelUsuario(null);
		}

		session.update(locker);
	}

	@Override
	public List<Locker> buscarLockersLibres() {
		final Session session = sessionFactory.getCurrentSession();

		return session.createCriteria(Locker.class).add(Restrictions.eq("ocupado", Boolean.FALSE)).list();
	}

	@Override
	public Locker buscarLockerPorId(int id) {
		return (Locker) sessionFactory.getCurrentSession().createCriteria(Locker.class).add(Restrictions.eq("id", id))
				.uniqueResult();

	}

	@Override
	public List<Locker> buscarLockersPorUsuario(Usuario usuario) {
		return (List<Locker>) sessionFactory.getCurrentSession().createCriteria(Locker.class)
				.add(Restrictions.eq("propietario", usuario)).list();
	}

	@Override
	public void modificarNotaDeLocker(int lockerId, String texto) {
		final Session session = sessionFactory.getCurrentSession();
		Locker locker = (Locker) session.createCriteria(Locker.class).add(Restrictions.eq("id", lockerId))
				.uniqueResult();
		locker.setTextoDelUsuario(texto);
		session.update(locker);
	}

	@Override
	public List<Locker> buscarLockersDisponiblesPorSucursal(Long idSucursal) {
		final Session session = sessionFactory.getCurrentSession();

		return session.createCriteria(Locker.class)
				.createAlias("sucursal", "suc")
				.add(Restrictions.eq("suc.id", idSucursal))
				.add(Restrictions.eq("ocupado", false))
				.list();
	}

	@Override
	public List<Locker> buscarLockersDisponiblesPorSucursalYTamanio(String localidad, String tamanio) {
		final Session session = sessionFactory.getCurrentSession();
		
		Criteria criteria = session.createCriteria(Locker.class);
		
		if(localidad != null) {
			criteria.createAlias("sucursal", "suc")
			.createAlias("suc.localidad", "loc")
			.add(Restrictions.like("loc.nombre", localidad, MatchMode.ANYWHERE));
		}
	
		if(tamanio != null) {
			criteria.createAlias("tamanio", "tam")
				.add(Restrictions.like("tam.tamanio", tamanio, MatchMode.ANYWHERE));
		}
		
		return criteria.add(Restrictions.eq("ocupado", false)).list();
	}

	@Override
	public void guardarCodigo(int lockerId, String codigo) {
		final Session session = sessionFactory.getCurrentSession();
		Locker locker = (Locker) session.createCriteria(Locker.class).add(Restrictions.eq("id", lockerId))
				.uniqueResult();
		
		// Tendria que ir en un repositorio de codigo como: Codigo codigo = repositorioCodigo.crearCodigo(String codigo);
		// Si ya existe un c�digo deber�a limpiarlo de la bd cuando lo genera en el servicio
		Codigo codigoApertura = new Codigo();
		if(locker.getCodigoApertura() != null) {
			session.remove(locker.getCodigoApertura());
		}
		codigoApertura.setCodigo(codigo);
		session.save(codigoApertura);		
		
		locker.setCodigoApertura(codigoApertura);
		session.update(locker);
	}

	@Override
	public void borrarCodigoALocker(Integer lockerId) {
		final Session session = sessionFactory.getCurrentSession();
		
		Locker locker = this.buscarLockerPorId(lockerId);
		Codigo codigoApertura = locker.getCodigoApertura();
		
		locker.setCodigoApertura(null);
		session.update(locker);
		session.remove(codigoApertura);
	}

	@Override
	public void actualizarLocker(Locker locker) {
		sessionFactory.getCurrentSession().update(locker);
	}
}
