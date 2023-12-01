package dominio;

import java.time.LocalDateTime;
import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.codecs.pojo.annotations.BsonRepresentation;
import repositorio.Identificable;

public class Historico implements Identificable{
	@BsonProperty(value="fecha_estacionamiento")
	private LocalDateTime fechaEstacionamiento;
	@BsonProperty(value="fecha_retiro")
	private LocalDateTime fechaRetiro;
	@BsonProperty(value="id_bici")
	private String idBici;
	@BsonId
	@BsonRepresentation(BsonType.OBJECT_ID)
	private String id;
	@BsonProperty(value="id_estacion")
	private String idEstacion;
	
	public Historico(String idBici, String idEstacion) {
		this.fechaEstacionamiento = LocalDateTime.now();
		this.fechaRetiro = null;
		this.idBici = idBici;
		this.idEstacion = idEstacion;
	}
	
	public Historico() {
		
	}
	
	

	public LocalDateTime getFechaEstacionamiento() {
		return fechaEstacionamiento;
	}

	public LocalDateTime getFechaRetiro() {
		return fechaRetiro;
	}

	public void setFechaRetiro(LocalDateTime fechaRetiro) {
		this.fechaRetiro = fechaRetiro;
	}

	public String getIdBici() {
		return idBici;
	}

	public String getIdEstacion() {
		return idEstacion;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id=id;
		
	}

	@Override
	public String toString() {
		return "Historico [fechaEstacionamiento=" + fechaEstacionamiento + ", fechaRetiro=" + fechaRetiro + ", idBici="
				+ idBici + ", id=" + id + ", idEstacion=" + idEstacion + "]";
	}

}
