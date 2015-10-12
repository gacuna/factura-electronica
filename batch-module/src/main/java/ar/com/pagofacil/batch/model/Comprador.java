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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlElement;

@Entity
@Table(name="Comprador")
public class Comprador implements Serializable, Identificable {

	private static final long serialVersionUID = 3645141440589838268L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="comprador_id")
	private Long id;
	
	private Long cuit;
	
	private String razonSocial;
	
	private String domicilio;
	
	private String localidad;
	
	private String telefono;
	
	private String codigoPostal;
	
	private String pais;
	
	private String categoriaAfip;
	
	private Long numeroCliente;
	
	private Character tipoEntidad;
	
	@Transient
	private List<String> mails = new ArrayList<>();
	
	private Integer cantidadMailsAProcesar;
	
	@OneToMany(mappedBy="comprador", cascade=CascadeType.ALL)
	private List<Comprobante> comprobantes = new ArrayList<>();
	
	private Integer cantidadComprobantesAProcesar;

	@ManyToOne
	@JoinColumn(name="vendedor_id")
	private Vendedor vendedor;

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

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
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

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
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

	public String getCategoriaAfip() {
		return categoriaAfip;
	}

	public void setCategoriaAfip(String categoriaAfip) {
		this.categoriaAfip = categoriaAfip;
	}

	public Long getNumeroCliente() {
		return numeroCliente;
	}

	public void setNumeroCliente(Long numeroCliente) {
		this.numeroCliente = numeroCliente;
	}

	public Character getTipoEntidad() {
		return tipoEntidad;
	}

	public void setTipoEntidad(Character tipoEntidad) {
		this.tipoEntidad = tipoEntidad;
	}

	@XmlElement(name = "mails")
	public List<String> getMails() {
		return mails;
	}

	public void setMails(List<String> mails) {
		this.mails = mails;
	}

	public List<Comprobante> getComprobantes() {
		return comprobantes;
	}

	public void setComprobantes(List<Comprobante> comprobantes) {
		this.comprobantes = comprobantes;
	}

	public Integer getCantidadMailsAProcesar() {
		return cantidadMailsAProcesar;
	}

	public void setCantidadMailsAProcesar(Integer cantidadMailsAProcesar) {
		this.cantidadMailsAProcesar = cantidadMailsAProcesar;
	}

	public Integer getCantidadComprobantesAProcesar() {
		return cantidadComprobantesAProcesar;
	}

	public void setCantidadComprobantesAProcesar(Integer cantidadComprobantesAProcesar) {
		this.cantidadComprobantesAProcesar = cantidadComprobantesAProcesar;
	}

	// No se setea al parsear el archivo
	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}
	
}
