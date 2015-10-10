package ar.com.pagofacil.batch.model;

import java.io.Serializable;

public class Jurisdiccion implements Serializable {

	private static final long serialVersionUID = -2067188594696302598L;

	private int secuencia;
	private Double precioUnitario;
	private Double cantidad;
	private String unidadMedida;
	private String descripcion;
	private Double importeBase;

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

}
