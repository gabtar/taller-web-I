package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Calendar;
import java.util.Date;

@Entity
public class Locker {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private Long idSucursal;
	private String tamano;
	private Calendar fechaDeAlta;
	private Date fecha;



	private String textoDelUsuario;
	
	public String getTamano() {
		return tamano;
	}
	public void setTamano(String tamano) {
		this.tamano = tamano;
	}
	public Long getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}
	private boolean ocupado;
	private Long usuarioId;

	
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Long getIdSucursal() {
		return idSucursal;
	}
	public void setIdSucursal(Long idSucursal) {
		this.idSucursal = idSucursal;
	}
	public boolean isOcupado() {
		return ocupado;
	}
	public void setOcupado(boolean ocupado) {
		this.ocupado = ocupado;
	}
	public void setUsuario(Long usuarioId) {
		// TODO Auto-generated method stub
		this.usuarioId = usuarioId;
	}
	public Long usuarioId() {
		return usuarioId;
	}

	public String getTextoDelUsuario() {
		return textoDelUsuario;
	}

	public void setTextoDelUsuario(String textoDelUsuario) {
		this.textoDelUsuario = textoDelUsuario;
	}
}
