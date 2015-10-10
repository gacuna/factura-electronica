package ar.com.pagofacil.batch;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.util.Assert;

import ar.com.pagofacil.batch.model.Comprador;
import ar.com.pagofacil.batch.model.Comprobante;
import ar.com.pagofacil.batch.model.Impuesto;
import ar.com.pagofacil.batch.model.Jurisdiccion;
import ar.com.pagofacil.batch.model.TotalImpuesto;
import ar.com.pagofacil.batch.model.TotalJurisdiccion;
import ar.com.pagofacil.batch.model.Vendedor;

/**
 * @author gacuna
 * @since 1.0
 */
public class MultiLineItemReader implements ItemReader<Vendedor>, ItemStream {
	private FlatFileItemReader<FieldSet> delegate;

	/**
	 * @see org.springframework.batch.item.ItemReader#read()
	 */
	@Override
	public Vendedor read() throws Exception {
		Vendedor vendedor = null;
		List<Comprador> compradores = new ArrayList<Comprador>();
		
		for (FieldSet line; (line = this.delegate.read()) != null;) {
			String prefix = line.readString(0);
			if (prefix.equals("01")) { //Vendedor
				vendedor = new Vendedor(); // Record must start with '01'
				vendedor.setCuit(line.readLong(1));
				vendedor.setNombre(line.readString(2));
				vendedor.setDomicilio(line.readString(3));
				vendedor.setLocalidad(line.readString(4));
				vendedor.setCodigoPostal(line.readString(5));
				vendedor.setPais(line.readString(6));
			} else if (prefix.equals("02")) { //Comprador
				Assert.notNull(vendedor, "No se definio un vendedor");
				Comprador comprador = new Comprador();
				
				comprador.setCuit(line.readLong(1));
				comprador.setRazonSocial(line.readString(2));
				comprador.setDomicilio(line.readString(3));
				comprador.setLocalidad(line.readString(4));
				comprador.setTelefono(line.readString(5));
				comprador.setCodigoPostal(line.readString(6));
				comprador.setPais(line.readString(7));
				comprador.setCategoriaAfip(line.readString(8));
				comprador.setNumeroCliente(line.readLong(9));
				comprador.setTipoEntidad(line.readChar(10));
				
				compradores.add(comprador);
			} else if (prefix.equals("03")) { //Mails Comprador
				Assert.notNull(vendedor, "No se definio un vendedor");
				
				Comprador comprador = getCompradorActual(compradores);
				comprador.getMails().add(line.readString(1));
				
			} else if (prefix.equals("04")) { //Comprobantes
				Assert.notNull(vendedor, "No se definio un vendedor");
				Comprobante comprobante = new Comprobante();
				
				comprobante.setIdInterno(line.readString(1));
				comprobante.setPuntoVenta(line.readString(2));
				comprobante.setTipoComprobante(line.readString(3));
				comprobante.setLetraFactura(line.readChar(4));
				comprobante.setFechaFactura(line.readString(5));
				comprobante.setFechaVencimiento(line.readString(6));
				comprobante.setCondicionPago(line.readString(7));
				comprobante.setMoneda(line.readString(8));
				comprobante.setTipoCambio(line.readDouble(9));
				comprobante.setTotalComprobante(line.readDouble(10));
				comprobante.setGravado(line.readDouble(10));
				comprobante.setNoGravado(line.readDouble(11));
				comprobante.setDescuento(line.readDouble(12));
				comprobante.setMontoIVADF(line.readDouble(13));
				comprobante.setPercepcionesIVA(line.readDouble(14));
				comprobante.setPercepcionesIIBB(line.readDouble(15));
				comprobante.setRedondeo(line.readDouble(16));

				Comprador comprador = getCompradorActual(compradores);
				comprador.getComprobantes().add(comprobante);
				
			} else if (prefix.equals("05")) { //Jurisdiccion
				Jurisdiccion jurisdiccion = new Jurisdiccion();
				jurisdiccion.setSecuencia(line.readInt(1));
				jurisdiccion.setCantidad(line.readDouble(2));
				jurisdiccion.setPrecioUnitario(line.readDouble(3));
				jurisdiccion.setUnidadMedida(line.readString(4));
				jurisdiccion.setDescripcion(line.readString(5));
				jurisdiccion.setImporteBase(line.readDouble(6));
		
				Comprador comprador = getCompradorActual(compradores);
				Comprobante comprobante = getComprobanteActual(comprador);
				comprobante.getJurisdicciones().add(jurisdiccion);
					
			} else if (prefix.equals("95")) { //Total Jurisdiccion
				Comprador comprador = getCompradorActual(compradores);
				Comprobante comprobante = getComprobanteActual(comprador);
				
				TotalJurisdiccion total = new TotalJurisdiccion();
				total.setCantidadAProcesar(line.readInt(1));
				total.setTotal(line.readDouble(2));
				comprobante.setTotalJurisdiccion(total);
				
			} else if (prefix.equals("06")) { //Impuesto
				Impuesto impuesto = new Impuesto();
				
				impuesto.setCodigo(line.readString(1));
				impuesto.setDescripcion(line.readString(2));
				impuesto.setAlicuota(line.readDouble(3));
				impuesto.setCodigoAfip(line.readString(4));
				impuesto.setMonto(line.readDouble(5));
				impuesto.setMontoBase(line.readDouble(6));

				Comprador comprador = getCompradorActual(compradores);
				Comprobante comprobante = getComprobanteActual(comprador);
				comprobante.getImpuestos().add(impuesto);
				
			} else if (prefix.equals("96")) { //Total Impuesto
				Comprador comprador = getCompradorActual(compradores);
				Comprobante comprobante = getComprobanteActual(comprador);
				
				TotalImpuesto total = new TotalImpuesto();
				total.setCantidadAProcesar(line.readInt(1));
				total.setTotal(line.readDouble(2));
				comprobante.setTotalImpuesto(total);
				
			} else if (prefix.equals("92")) { //Cierre del Comprador
				Assert.notNull(vendedor, "No se definio un vendedor");

				Comprador comprador = getCompradorActual(compradores);
				
				comprador.setCantidadMailsAProcesar(line.readInt(2));
				comprador.setCantidadComprobantesAProcesar(line.readInt(3));
				
			} else if (prefix.equals("91")) { //Cierre del vendedor
				Assert.notNull(vendedor, "No se definio un vendedor");
				vendedor.setCantidadCompradores(Integer.valueOf(line.readInt(2)));
				vendedor.setCompradores(compradores);
				return vendedor; // Record must end with 'FOT'
			}
		}
		Assert.isNull(vendedor, "No '01' was found.");
		return null;
	}

	private Comprobante getComprobanteActual(Comprador comprador) {
		List<Comprobante> comprobantes = comprador.getComprobantes();
		if (!comprobantes.isEmpty())
			return comprobantes.get(comprobantes.size() - 1);
		
		return null;
	}

	private Comprador getCompradorActual(List<Comprador> compradores) {
		if (!compradores.isEmpty())
			return compradores.get(compradores.size() - 1);
		
		return null;
	}

	public void setDelegate(FlatFileItemReader<FieldSet> delegate) {
		this.delegate = delegate;
	}

	@Override
	public void close() throws ItemStreamException {
		this.delegate.close();
	}

	@Override
	public void open(ExecutionContext executionContext) throws ItemStreamException {
		this.delegate.open(executionContext);
	}

	@Override
	public void update(ExecutionContext executionContext) throws ItemStreamException {
		this.delegate.update(executionContext);
	}
}