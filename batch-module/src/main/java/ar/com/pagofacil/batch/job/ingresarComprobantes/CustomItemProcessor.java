package ar.com.pagofacil.batch.job.ingresarComprobantes;

import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import ar.com.pagofacil.batch.model.AbstractTextLine;
import ar.com.pagofacil.batch.model.Comprador;
import ar.com.pagofacil.batch.model.Comprobante;
import ar.com.pagofacil.batch.model.Identificable;
import ar.com.pagofacil.batch.model.RegistroErrores;
import ar.com.pagofacil.batch.model.Vendedor;

@Component(value="itemProcessor")
public class CustomItemProcessor implements ItemProcessor<AbstractTextLine, AbstractTextLine> {

	public AbstractTextLine process(AbstractTextLine item) throws Exception {
		Vendedor vendedor = (Vendedor) item;
		
		if (vendedor.getId() != null) {
			System.out.println("Skiping..." + vendedor);
			RegistroErrores registroError = new RegistroErrores();
			registroError.setIdProceso(1L);
			registroError.setLinea(vendedor.getLine());
			registroError.setCausa("Error formato de linea");
			registroError.setFechaHora(new Timestamp(System.currentTimeMillis()));
			registroError.addEntidadAfectada(vendedor);
			//TODO Recorrer todos los hijos y crear una entidad afectada
			return registroError;
		} else {
			//Validar cada Comprador/Comprobante/(Impuesto|Juridiccion|Totales)

			List<Comprador> compradores = vendedor.getCompradores();
			
			filtrarObjectosInvalidos(compradores);

			//Si el vendedor no tiene ningun comprador asociado hay que anularlo para que no lo persista
			if (compradores.isEmpty()) {
				System.out.println("Skiping..." + vendedor.getNombre());
				//TODO Loguear en una tabla de registros rechazados
				return null;
			} else {
				for(Comprador comprador : compradores) {
					List<Comprobante> comprobantes = comprador.getComprobantes(); 
					
					//Se filtran los comprobantes invalidos procesados en la lectura
					filtrarObjectosInvalidos(comprobantes);
					
					if (comprobantes.isEmpty()) {
						//Si no hay comprobantes para ese comprador entonces eliminar el comprador
						comprador.setId(99L);
					} else {
						for(Comprobante comprobante : comprobantes) {
							
							Predicate predicado = new Predicate() {
								
								@Override
								public boolean evaluate(Object object) {
									return ((Identificable) object).getId() != null;
								}
							};
							
							//Si existe alguna jurisdiccion erronea entonces cancelar el comprobante
							boolean hayJurisdiccionErronea = CollectionUtils.exists(comprobante.getJurisdicciones(), predicado);
							
							if (hayJurisdiccionErronea) {
								comprobante.setId(99L);
							} else {
								//Si existe algun impuesto erroneo entonces cancelar el comprobante
								boolean hayImpuestoErroneo = CollectionUtils.exists(comprobante.getImpuestos(), predicado);
								
								if (hayImpuestoErroneo) {
									comprobante.setId(99L);
								} else { //Si existe total erroneo entonces cancelar el comprobante
									if ((comprobante.getTotalJurisdiccion() != null && comprobante.getTotalJurisdiccion().getId() != null)
											|| (comprobante.getTotalImpuesto() != null && comprobante.getTotalImpuesto().getId() != null)) {
										comprobante.setId(99L);
									}
								}
							}
						}
						
						//Filtra los comprobantes que puedieron ser seteados como invalidos por algun error en los impuestos
						filtrarObjectosInvalidos(comprobantes);
						
						//Si no hay comprobantes para ese comprador entonces eliminar el comprador
						if (comprobantes.isEmpty()) {
							comprador.setId(99L);
						}
					}
				}
				
				//Filtra los compradores que puedieron ser seteados como invalidos por algun error en los comprobantes u otro dato
				filtrarObjectosInvalidos(compradores);
				
				if (compradores.isEmpty()) {
					System.out.println("Skiping..." + vendedor.getNombre());
					return null;
				}	
			}
			
			System.out.println("Processing..." + vendedor.getNombre());
			return vendedor;
		}
	}
	
	/**
	 * Filtra las entidades que tienen asignado un Identificador los cuales
	 * fueron marcados en el proceso de lectura del archivo como invalidos.
	 * 
	 * @param entities
	 */
	private static void filtrarObjectosInvalidos(List<? extends Identificable> entities) {
		CollectionUtils.filter(entities, new Predicate() {
			
			@Override
			public boolean evaluate(Object obj) {
				Identificable entity = (Identificable) obj;
				
				return (entity.getId() == null);
			}
		});
	}

}