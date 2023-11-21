package dominio;

import java.time.LocalDateTime;
import java.util.UUID;

import repositorio.Identificable;

public class EntradaHistorico implements Identificable{
	
	private String id;
	private LocalDateTime fechaEstacionamiento;
	private LocalDateTime fechaRetiro;
	private String idBici;
	private String idEstacion;
	
	public EntradaHistorico(String idBici, String idEstacion) {
		super();
		this.id = UUID.randomUUID().toString();
		this.fechaEstacionamiento = LocalDateTime.now();
		this.fechaRetiro = null;
		this.idBici = idBici;
		this.idEstacion = idEstacion;
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
	
	

}
