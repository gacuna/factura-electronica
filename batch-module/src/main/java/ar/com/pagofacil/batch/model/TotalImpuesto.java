package ar.com.pagofacil.batch.model;

import java.io.Serializable;

public class TotalImpuesto implements Serializable {
	private static final long serialVersionUID = 2048653237000518526L;

	private int cantidadAProcesar;
	private Double total;
	
	public int getCantidadAProcesar() {
		return cantidadAProcesar;
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

}
