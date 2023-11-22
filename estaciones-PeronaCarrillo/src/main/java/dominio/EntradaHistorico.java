package dominio;

import java.time.LocalDateTime;


public class EntradaHistorico {
	
	private LocalDateTime fechaEstacionamiento;
	private LocalDateTime fechaRetiro;
	private String idBici;
	private String idEstacion;
	
	public EntradaHistorico(String idBici, String idEstacion) {
		super();
		
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

}
