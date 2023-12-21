package dominio.dto;

import java.io.Serializable;

public class IncidenciaDTO implements Serializable {
	private String id;
	private String idBici;
	private String descripcion;
	private String operario;
	
	public IncidenciaDTO(String id, String idBici, String descripcion, String operario) {
		this.id = id;
		this.idBici = idBici;
		this.descripcion = descripcion;
		this.operario = operario;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdBici() {
		return idBici;
	}

	public void setIdBici(String idBici) {
		this.idBici = idBici;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getOperario() {
		if(operario == null)
			return "No Asignado";
		return operario;
	}

	public void setOperario(String operario) {
		this.operario = operario;
	}
		
}
