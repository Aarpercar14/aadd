package dominio;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Lob;

import repositorio.Identificable;

public class Incidencia implements Identificable{
	@Id
	private String id;
	@Column(name="idBicicleta")
	private String idBicicleta;
	@Column(name="fechaCreacion",columnDefinition="DATE")
	private LocalDateTime fechaCreacion;
	@Lob
	@Column(name="descripcion")
	private String descripcion;
	@Column(name="estado")
	private EstadoIncidencia estado;
	
	public Incidencia(String idBici, LocalDateTime fechaCreacion, String descripcion) {
		super();
		this.id=UUID.randomUUID().toString();
		this.idBicicleta=idBici;
		this.fechaCreacion = fechaCreacion;
		this.descripcion = descripcion;
		this.estado = EstadoIncidencia.PENDIENTE;
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
