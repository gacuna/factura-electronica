package ar.com.pagofacil.batch.model;

import java.io.Serializable;

public class TotalJurisdiccion implements Serializable {

	private static final long serialVersionUID = -5173857795396388183L;
	
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
