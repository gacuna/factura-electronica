package ar.com.pagofacil.batch.model;

import java.io.Serializable;

public class Impuesto implements Serializable {

	private static final long serialVersionUID = 6328938926201079568L;

	private String codigo;
	private String descripcion;
	private Double alicuota;
	private String codigoAfip;
	private Double monto;
	private Double montoBase;

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

}
