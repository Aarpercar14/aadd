package dominio.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class BicicletaDTO implements Serializable {

	private String id;
	private String modelo;
	private LocalDateTime fechaAlta;
	
	public BicicletaDTO(String id, String modelo, LocalDateTime fechaAlta) {
		this.id = id;
		this.modelo = modelo;
		this.fechaAlta = fechaAlta;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public LocalDateTime getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(LocalDateTime fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
		
}
