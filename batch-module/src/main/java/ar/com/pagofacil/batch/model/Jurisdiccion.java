package ar.com.pagofacil.batch.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Jurisdiccion")
public class Jurisdiccion extends AbstractTextLine implements Serializable {

	private static final long serialVersionUID = -2067188594696302598L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="jurisdiccion_id")
	private Long id;
	
	private int secuencia;
	private Double precioUnitario;
	private Double cantidad;
	private String unidadMedida;
	private String descripcion;
	private Double importeBase;

	@ManyToOne
	@JoinColumn(name="comprobante_id")
	private Comprobante comprobante;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getSecuencia() {
		return secuencia;
	}

	public void setSecuencia(int secuencia) {
		this.secuencia = secuencia;
	}

	public Double getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(Double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public String getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getImporteBase() {
		return importeBase;
	}

	public void setImporteBase(Double importeBase) {
		this.importeBase = importeBase;
	}

	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	public Comprobante getComprobante() {
		return comprobante;
	}

	public void setComprobante(Comprobante comprobante) {
		this.comprobante = comprobante;
	}

}
