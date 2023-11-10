package dominio;

import java.time.LocalDateTime;

import repositorio.Identificable;

public class Incidencia implements Identificable{
	
	private LocalDateTime fechaCreacion;
	private String descripcion;
	private String estado;
	
	public Incidencia(LocalDateTime fechaCreacion, String descripcion, String estado) {
		super();
		this.fechaCreacion = fechaCreacion;
		this.descripcion = descripcion;
		this.estado = estado;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		
	}
	
	

}
