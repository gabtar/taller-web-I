package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;

import java.util.Calendar;
import java.util.Date;

@Entity
public class Locker {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private boolean ocupado;
	private Date fechaInicioAlquiler;

	@OneToOne
	@JoinColumn(name = "tamanio_id")
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	private Tamanio tamanio;

	@OneToOne
	@JoinColumn(name = "sucursal_id")
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	private Sucursal sucursal;
	
	@OneToOne
	@JoinColumn(name = "usuario_id")
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	private Usuario propietario;
	
	@OneToOne
	@JoinColumn(name = "codigo_id")
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	private Codigo codigoApertura;

	private String textoDelUsuario;

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

	public Tamanio getTamanio() {
		return tamanio;
	}

	public void setTamanio(Tamanio tamanio) {
		this.tamanio = tamanio;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isOcupado() {
		return ocupado;
	}

	public void setOcupado(boolean ocupado) {
		this.ocupado = ocupado;
	}

	public String getTextoDelUsuario() {
		return textoDelUsuario;
	}

	public void setTextoDelUsuario(String textoDelUsuario) {
		this.textoDelUsuario = textoDelUsuario;
	}
	
	public Codigo getCodigoApertura() {
		return codigoApertura;
	}

	public void setCodigoApertura(Codigo codigoApertura) {
		this.codigoApertura = codigoApertura;
	}

	public Date getFechaInicioAlquiler() {
		return fechaInicioAlquiler;
	}

	public void setFechaInicioAlquiler(Date fechaInicioAlquiler) {
		this.fechaInicioAlquiler = fechaInicioAlquiler;
	}

	public Usuario getPropietario() {
		return propietario;
	}

	public void setPropietario(Usuario propietario) {
		this.propietario = propietario;
	}
}
