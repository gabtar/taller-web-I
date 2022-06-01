package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;

@Entity
public class Deposito {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
    @JoinColumn(name = "sucursal_id")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Sucursal sucursal;
	
	private Boolean disponible;
	private Double metrosCuadrados;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Sucursal getSucursal() {
		return sucursal;
	}
	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}
	public Boolean getDisponible() {
		return disponible;
	}
	public void setDisponible(Boolean disponible) {
		this.disponible = disponible;
	}
	public Double getMetrosCuadrados() {
		return metrosCuadrados;
	}
	public void setMetrosCuadrados(Double metrosCuadrados) {
		this.metrosCuadrados = metrosCuadrados;
	}

}
