package dominio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.codecs.pojo.annotations.BsonRepresentation;

import repositorio.Identificable;

public class Historico implements Identificable {
	@BsonId
	@BsonRepresentation(BsonType.OBJECT_ID)
	private String id;
	@BsonProperty(value="id_bici")
	private String idBici;
	@BsonProperty(value = "historico")
	private HashMap<String, EntradaHistorico> historico;
	@BsonProperty(value="id_ultima_estacion")
	private String idUltimaEstacion;
	
	public Historico(String idBici) {
		this.idBici = idBici;
		this.historico = new HashMap<String, EntradaHistorico>();
		this.idUltimaEstacion="";
	}
	
	public Historico() {
		
	}
	
	public void añadirEntrada(EntradaHistorico entrada) {
		if(historico==null) {
			historico= new HashMap<String, EntradaHistorico>();
		}
		this.historico.put(entrada.getIdEstacion(), entrada);
		this.idUltimaEstacion=entrada.getIdEstacion();
	}
	
	public void eliminarEntrada(String idEstacion) {
		this.historico.remove(idEstacion);
	}
	
	public HashMap<String, EntradaHistorico> getHistorico(){
		return new HashMap<>(this.historico);
	}
	
	@Override
	public String getId() {
		return this.id;
	}
	
	@Override
	public void setId(String id) {
		this.id = id;
	}
	
	public void salidaUltimaEstacion() {
		EntradaHistorico entrada = historico.get(idUltimaEstacion);
		entrada.setFechaRetiro(LocalDateTime.now());
		historico.replace(idUltimaEstacion, entrada);
	}
	
	public String getIdBici() {
		return this.idBici;
	}
	
	public String getUltimaEstacion() {
		return this.idUltimaEstacion;
	}

	@Override
	public String toString() {
		return "Historico [id=" + id + ", idBici=" + idBici + ", historico=" + historico + ", idUltimaEstacion="
				+ idUltimaEstacion + "]";
	}
	
}