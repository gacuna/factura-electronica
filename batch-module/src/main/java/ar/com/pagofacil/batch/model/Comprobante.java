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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="Comprobante")
public class Comprobante implements Serializable {

	private static final long serialVersionUID = 2115214912257474433L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="comprobante_id", unique=true)
	private Long id;
	
	private String idInterno;
	
	private String puntoVenta;
	
	private String tipoComprobante;
	
	private Character letraFactura;
	
	private String fechaFactura;
	
	private String fechaVencimiento;
	
	private String condicionPago;
	
	private String moneda;
	
	private Double tipoCambio;
	
	private Double totalComprobante;
	
	private Double gravado;
	
	private Double noGravado;
	
	private Double descuento;
	
	private Double montoIVADF;
	
	private Double percepcionesIVA;
	
	private Double percepcionesIIBB;
	
	private Double redondeo;
	
	@OneToMany(mappedBy="comprobante", cascade=CascadeType.ALL)
	private List<Jurisdiccion> jurisdicciones = new ArrayList<Jurisdiccion>();
	
	@OneToOne(mappedBy="comprobante", cascade=CascadeType.ALL)
	private TotalJurisdiccion totalJurisdiccion;
	
	@OneToMany(mappedBy="comprobante", cascade=CascadeType.ALL)
	private List<Impuesto> impuestos = new ArrayList<Impuesto>();
	
	@OneToOne(mappedBy="comprobante", cascade=CascadeType.ALL)
	private TotalImpuesto totalImpuesto;

	@ManyToOne
	@JoinColumn(name="comprador_id")
	private Comprador comprador;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdInterno() {
		return idInterno;
	}

	public void setIdInterno(String idInterno) {
		this.idInterno = idInterno;
	}

	public String getPuntoVenta() {
		return puntoVenta;
	}

	public void setPuntoVenta(String puntoVenta) {
		this.puntoVenta = puntoVenta;
	}

	public String getTipoComprobante() {
		return tipoComprobante;
	}

	public void setTipoComprobante(String tipoComprobante) {
		this.tipoComprobante = tipoComprobante;
	}

	public Character getLetraFactura() {
		return letraFactura;
	}

	public void setLetraFactura(Character letraFactura) {
		this.letraFactura = letraFactura;
	}

	public String getFechaFactura() {
		return fechaFactura;
	}

	public void setFechaFactura(String fechaFactura) {
		this.fechaFactura = fechaFactura;
	}

	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public String getCondicionPago() {
		return condicionPago;
	}

	public void setCondicionPago(String condicionPago) {
		this.condicionPago = condicionPago;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public Double getTipoCambio() {
		return tipoCambio;
	}

	public void setTipoCambio(Double tipoCambio) {
		this.tipoCambio = tipoCambio;
	}

	public Double getTotalComprobante() {
		return totalComprobante;
	}

	public void setTotalComprobante(Double totalComprobante) {
		this.totalComprobante = totalComprobante;
	}

	public Double getGravado() {
		return gravado;
	}

	public void setGravado(Double gravado) {
		this.gravado = gravado;
	}

	public Double getNoGravado() {
		return noGravado;
	}

	public void setNoGravado(Double noGravado) {
		this.noGravado = noGravado;
	}

	public Double getDescuento() {
		return descuento;
	}

	public void setDescuento(Double descuento) {
		this.descuento = descuento;
	}

	public Double getMontoIVADF() {
		return montoIVADF;
	}

	public void setMontoIVADF(Double montoIVADF) {
		this.montoIVADF = montoIVADF;
	}

	public Double getPercepcionesIVA() {
		return percepcionesIVA;
	}

	public void setPercepcionesIVA(Double percepcionesIVA) {
		this.percepcionesIVA = percepcionesIVA;
	}

	public Double getPercepcionesIIBB() {
		return percepcionesIIBB;
	}

	public void setPercepcionesIIBB(Double percepcionesIIBB) {
		this.percepcionesIIBB = percepcionesIIBB;
	}

	public Double getRedondeo() {
		return redondeo;
	}

	public void setRedondeo(Double redondeo) {
		this.redondeo = redondeo;
	}

	public List<Jurisdiccion> getJurisdicciones() {
		return jurisdicciones;
	}

	public void setJurisdicciones(List<Jurisdiccion> jurisdicciones) {
		this.jurisdicciones = jurisdicciones;
	}

	public TotalJurisdiccion getTotalJurisdiccion() {
		return totalJurisdiccion;
	}

	public void setTotalJurisdiccion(TotalJurisdiccion totalJurisdiccion) {
		this.totalJurisdiccion = totalJurisdiccion;
	}

	public List<Impuesto> getImpuestos() {
		return impuestos;
	}

	public void setImpuestos(List<Impuesto> impuestos) {
		this.impuestos = impuestos;
	}

	public TotalImpuesto getTotalImpuesto() {
		return totalImpuesto;
	}

	public void setTotalImpuesto(TotalImpuesto totalImpuesto) {
		this.totalImpuesto = totalImpuesto;
	}

	public Comprador getComprador() {
		return comprador;
	}

	public void setComprador(Comprador comprador) {
		this.comprador = comprador;
	}

}
