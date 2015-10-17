package ar.com.pagofacil.batch.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @author Bender
 *
 */
@Entity
@Table(name="REGISTRO_AFECTADO")
public class RegistroAfectado implements Serializable {

	private static final long serialVersionUID = 3258303496324116515L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="registro_error_id")
	private RegistroErrores registro;

	@Column(name="LINEA_AFECTADA")
	private String linea;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RegistroErrores getRegistro() {
		return registro;
	}

	public void setRegistro(RegistroErrores registro) {
		this.registro = registro;
	}

	public String getLinea() {
		return linea;
	}

	public void setLinea(String linea) {
		this.linea = linea;
	}
	
}
