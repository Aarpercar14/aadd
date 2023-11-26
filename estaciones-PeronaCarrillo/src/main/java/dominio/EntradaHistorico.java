package dominio;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Id;

import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.codecs.pojo.annotations.BsonRepresentation;

public class EntradaHistorico {
	@BsonProperty(value="fecha_estacionamiento")
	private LocalDateTime fechaEstacionamiento;
	@BsonProperty(value="fecha_retiro")
	private LocalDateTime fechaRetiro;
	@BsonProperty(value="id_bici")
	private String idBici;
	@BsonId
	@BsonRepresentation(BsonType.OBJECT_ID)
	private String idEstacion;
	
	public EntradaHistorico(String idBici, String idEstacion) {
		this.fechaEstacionamiento = LocalDateTime.now();
		this.fechaRetiro = null;
		this.idBici = idBici;
		this.idEstacion = idEstacion;
	}
	
	public EntradaHistorico() {
		
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

}
