package dominio;

import java.time.LocalDateTime;

public class Incidencia {
	
	private LocalDateTime fechaCreacion;
	private String descripcion;
	private String estado;
	
	public Incidencia(LocalDateTime fechaCreacion, String descripcion, String estado) {
		super();
		this.fechaCreacion = fechaCreacion;
		this.descripcion = descripcion;
		this.estado = estado;
	}
	
	

}
