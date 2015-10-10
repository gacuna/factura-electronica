package ar.com.pagofacil.batch.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "record")
public class Vendedor implements Serializable {
	
	private static final long serialVersionUID = 3485112577323297821L;
	
	private Long cuit;
	private String nombre;
	private String domicilio;
	private String localidad;
	private String codigoPostal;
	private String pais;
	private int cantidadCompradores;
	private List<Comprador> compradores = new ArrayList<Comprador>();

	public Long getCuit() {
		return cuit;
	}

	public void setCuit(Long cuit) {
		this.cuit = cuit;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public int getCantidadCompradores() {
		return cantidadCompradores;
	}

	public void setCantidadCompradores(int cantidadCompradores) {
		this.cantidadCompradores = cantidadCompradores;
	}

	public List<Comprador> getCompradores() {
		return compradores;
	}

	public void setCompradores(List<Comprador> compradores) {
		this.compradores = compradores;
	}

}
