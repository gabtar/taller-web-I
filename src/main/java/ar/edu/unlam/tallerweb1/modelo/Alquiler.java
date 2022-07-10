package ar.edu.unlam.tallerweb1.modelo;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;

@Entity
public class Alquiler {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaInicio;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaFinalizacion;
	
	@OneToOne
	@JoinColumn(name = "locker_id")
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	private Locker locker;
	
	@OneToOne
	@JoinColumn(name = "usuario_id")
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	private Usuario usuario;
	
	@Enumerated(EnumType.STRING)
	public EstadoAlquiler estadoAlquiler;
	// TODO field @transient con el precio segun diferencia de fechas y
	// multiplicador segun tamanio del locker

	public Alquiler() {
		this.fechaInicio = new Date(Calendar.getInstance().getTimeInMillis());
		this.estadoAlquiler = EstadoAlquiler.ACTIVO;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFinalizacion() {
		return fechaFinalizacion;
	}

	public void setFechaFinalizacion(Date fechaFinalizacion) {
		this.fechaFinalizacion = fechaFinalizacion;
	}

	public Locker getLocker() {
		return locker;
	}

	public void setLocker(Locker locker) {
		this.locker = locker;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public EstadoAlquiler getEstadoAlquiler() {
		return estadoAlquiler;
	}

	public void setEstadoAlquiler(EstadoAlquiler estadoAlquiler) {
		this.estadoAlquiler = estadoAlquiler;
	}
}
