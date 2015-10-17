package ar.com.pagofacil.batch.job.ingresarComprobantes;

import java.util.Arrays;
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
import ar.com.pagofacil.batch.model.EstadoComprobante;
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
		
		for (FieldSet line; (line = this.delegate.read()) != null;) {
			int fieldCount = line.getFieldCount();
			
			String prefix = line.readString(0);
			
			if (prefix.equals("01")) { //Vendedor
				if (fieldCount == 7) {
					vendedor = new Vendedor(); // Record must start with '01'
					vendedor.setCuit(line.readLong(1));
					vendedor.setNombre(line.readString(2));
					vendedor.setDomicilio(line.readString(3));
					vendedor.setLocalidad(line.readString(4));
					vendedor.setCodigoPostal(line.readString(5));
					vendedor.setPais(line.readString(6));
				} else {
					vendedor = new Vendedor();
					vendedor.setId(99L);
					vendedor.setLine(Arrays.toString(line.getValues()));
				}
			} else if (prefix.equals("02")) { //Comprador
				
				if (vendedor.getId() == null && fieldCount == 11) {
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
					comprador.setVendedor(vendedor);
					
					vendedor.getCompradores().add(comprador);
				} else {
					Comprador comprador = new Comprador();
					comprador.setId(99L);
					comprador.setLine(Arrays.toString(line.getValues()));
					vendedor.getCompradores().add(comprador);
				}
				
			} else if (prefix.equals("03")) { //Mails Comprador
				Comprador comprador = getCompradorActual(vendedor);
	
				String mail = "";
				
				if (comprador.getId() == null && fieldCount == 2) {
					mail = line.readString(1);
				}
				
				comprador.getMails().add(mail);
				
			} else if (prefix.equals("04")) { //Comprobantes
				Comprador comprador = getCompradorActual(vendedor);
				Comprobante comprobante = new Comprobante();
				
				if (comprador.getId() == null && fieldCount == 18) {
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
					comprobante.setEstado(EstadoComprobante.PENDIENTE_PROCESO);
				} else {
					comprobante.setEstado(EstadoComprobante.ERROR_IMPORTACION);
					comprobante.setLine(Arrays.toString(line.getValues()));
					comprobante.setId(99L);
				}

				comprador.getComprobantes().add(comprobante);
				comprobante.setComprador(comprador);
				
			} else if (prefix.equals("05")) { //Jurisdiccion

				Comprador comprador = getCompradorActual(vendedor);
				Comprobante comprobante = getComprobanteActual(comprador);
				
				Jurisdiccion jurisdiccion = new Jurisdiccion();
				
				if (comprobante.getId() == null && fieldCount == 7) {
					jurisdiccion.setSecuencia(line.readInt(1));
					jurisdiccion.setPrecioUnitario(line.readDouble(2));
					jurisdiccion.setCantidad(line.readDouble(3));
					jurisdiccion.setUnidadMedida(line.readString(4));
					jurisdiccion.setDescripcion(line.readString(5));
					jurisdiccion.setImporteBase(line.readDouble(6));
				} else {
					jurisdiccion.setLine(Arrays.toString(line.getValues()));
					jurisdiccion.setId(99L);
				}
				
				jurisdiccion.setComprobante(comprobante);
				comprobante.getJurisdicciones().add(jurisdiccion);
				
			} else if (prefix.equals("95")) { //Total Jurisdiccion
				
				Comprador comprador = getCompradorActual(vendedor);
				Comprobante comprobante = getComprobanteActual(comprador);

				TotalJurisdiccion total = new TotalJurisdiccion();
				
				if (comprobante.getId() == null && fieldCount == 3) {
					total.setCantidadAProcesar(line.readInt(1));
					total.setTotal(line.readDouble(2));
					total.setComprobante(comprobante);
				} else {
					total.setLine(Arrays.toString(line.getValues()));
					total.setId(99L);
				}
				
				comprobante.setTotalJurisdiccion(total);
				
			} else if (prefix.equals("06")) { //Impuesto
				Comprador comprador = getCompradorActual(vendedor);
				Comprobante comprobante = getComprobanteActual(comprador);

				Impuesto impuesto = new Impuesto();

				if (comprobante.getId() == null && fieldCount == 7) {
					impuesto.setCodigo(line.readString(1));
					impuesto.setDescripcion(line.readString(2));
					impuesto.setAlicuota(line.readDouble(3));
					impuesto.setCodigoAfip(line.readString(4));
					impuesto.setMonto(line.readDouble(5));
					impuesto.setMontoBase(line.readDouble(6));
				} else {
					impuesto.setLine(Arrays.toString(line.getValues()));
					impuesto.setId(99L);
				}
				
				comprobante.getImpuestos().add(impuesto);
				impuesto.setComprobante(comprobante);
				
			} else if (prefix.equals("96")) { //Total Impuesto
				Comprador comprador = getCompradorActual(vendedor);
				Comprobante comprobante = getComprobanteActual(comprador);
				
				TotalImpuesto total = new TotalImpuesto();

				if (comprobante.getId() == null && fieldCount == 3) {
					total.setCantidadAProcesar(line.readInt(1));
					total.setTotal(line.readDouble(2));
				} else {
					total.setLine(Arrays.toString(line.getValues()));
					total.setId(99L);
				}

				total.setComprobante(comprobante);
				comprobante.setTotalImpuesto(total);
				
			} else if (prefix.equals("92")) { //Cierre del Comprador

				Comprador comprador = getCompradorActual(vendedor);

				if (comprador.getId() == null && fieldCount == 4) {
					comprador.setCantidadMailsAProcesar(line.readInt(2));
					comprador.setCantidadComprobantesAProcesar(line.readInt(3));
				} else { //Si hay un error en la linea de totales del comprador no se cancela
					comprador.setCantidadMailsAProcesar(-1);
					comprador.setCantidadComprobantesAProcesar(-1);
				}
				
			} else if (prefix.equals("91")) { //Cierre del vendedor
				
				if (vendedor.getId() == null && fieldCount == 3) {
					vendedor.setCantidadCompradores(line.readInt(2));
				} else {
					vendedor.setCantidadCompradores(-1);
				}
				
				return vendedor;
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

	private Comprador getCompradorActual(Vendedor vendedor) {
		List<Comprador> compradores = vendedor.getCompradores();
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