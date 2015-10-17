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
@Table(name="Impuesto")
public class Impuesto extends AbstractTextLine implements Serializable {

	private static final long serialVersionUID = 6328938926201079568L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="impuesto_id")
	private Long id;

	private String codigo;
	private String descripcion;
	private Double alicuota;
	private String codigoAfip;
	private Double monto;
	private Double montoBase;

	@ManyToOne
	@JoinColumn(name="comprobante_id")
	private Comprobante comprobante;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getAlicuota() {
		return alicuota;
	}

	public void setAlicuota(Double alicuota) {
		this.alicuota = alicuota;
	}

	public String getCodigoAfip() {
		return codigoAfip;
	}

	public void setCodigoAfip(String codigoAfip) {
		this.codigoAfip = codigoAfip;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public Double getMontoBase() {
		return montoBase;
	}

	public void setMontoBase(Double montoBase) {
		this.montoBase = montoBase;
	}

	public Comprobante getComprobante() {
		return comprobante;
	}

	public void setComprobante(Comprobante comprobante) {
		this.comprobante = comprobante;
	}

}
