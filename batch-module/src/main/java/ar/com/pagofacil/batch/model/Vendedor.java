package ar.com.pagofacil.batch.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Vendedor")
public class Vendedor extends AbstractTextLine implements Serializable {
	
	private static final long serialVersionUID = 3485112577323297821L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="vendedor_id", nullable=false, unique=true)
	private Long id;
	
	private Long cuit;
	
	private String nombre;
	
	private String domicilio;
	
	private String localidad;
	
	private String codigoPostal;
	
	private String pais;
	
	private int cantidadCompradores;
	
	@OneToMany(mappedBy="vendedor", cascade=CascadeType.ALL)
	private List<Comprador> compradores = new ArrayList<Comprador>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
