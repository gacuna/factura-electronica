package ar.com.pagofacil.batch.model;

import java.io.Serializable;
import java.sql.Timestamp;
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
@Table(name = "ERROR_IMPORTACION")
public class RegistroErrores extends AbstractTextLine implements Serializable {

	private static final long serialVersionUID = -2224691748216807570L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="registro_error_id")
	private Long id;

	@Column(name="ID_PROCESO")
	private Long idProceso;
	
	@Column(name="LINEA", length=250)
	private String linea;
	
	@Column(name="CAUSA", length=200)
	private String causa;
	
	@Column(name="FECHA_HORA")
	private Timestamp fechaHora;

	@OneToMany(cascade=CascadeType.ALL, mappedBy="registro")
	private List<RegistroAfectado> registrosAfectados = new ArrayList<RegistroAfectado>();
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdProceso() {
		return idProceso;
	}

	public void setIdProceso(Long idProceso) {
		this.idProceso = idProceso;
	}

	public String getLinea() {
		return linea;
	}

	public void setLinea(String linea) {
		this.linea = linea;
	}

	public String getCausa() {
		return causa;
	}

	public void setCausa(String causa) {
		this.causa = causa;
	}

	public Timestamp getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(Timestamp fechaHora) {
		this.fechaHora = fechaHora;
	}

	public List<RegistroAfectado> getRegistrosAfectados() {
		return registrosAfectados;
	}

	public void setRegistrosAfectados(List<RegistroAfectado> registrosAfectados) {
		this.registrosAfectados = registrosAfectados;
	}

	public void addEntidadAfectada(AbstractTextLine entity) {
		RegistroAfectado registro = new RegistroAfectado();
		registro.setRegistro(this);
		registro.setLinea(entity.getLine());
		this.registrosAfectados.add(registro);
	}
}
