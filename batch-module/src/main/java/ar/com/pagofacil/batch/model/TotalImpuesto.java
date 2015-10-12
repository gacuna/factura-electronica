package ar.com.pagofacil.batch.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name="Total_Impuesto")
public class TotalImpuesto implements Serializable, Identificable {
	private static final long serialVersionUID = 2048653237000518526L;

	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "comprobante"))
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "comprobante_id", unique = true, nullable = false)
	private Long id;

	private int cantidadAProcesar;
	
	private Double total;
	
	@OneToOne
	@PrimaryKeyJoinColumn
	private Comprobante comprobante;
	
	public int getCantidadAProcesar() {
		return cantidadAProcesar;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setCantidadAProcesar(int cantidadAProcesar) {
		this.cantidadAProcesar = cantidadAProcesar;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Comprobante getComprobante() {
		return comprobante;
	}

	public void setComprobante(Comprobante comprobante) {
		this.comprobante = comprobante;
	}

}
