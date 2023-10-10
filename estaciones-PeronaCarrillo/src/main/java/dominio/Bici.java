package dominio;

import java.time.LocalDate;
import java.util.UUID;

public class Bici implements Identificable {
	private String id;
	private String modelo;
	private LocalDate fechaAlta;
	private LocalDate fechaBaja;
	private String motivoBaja;
	
	public Bici(String modelo, LocalDate fechaAlta) {
		id = UUID.randomUUID().toString();
		this.modelo = modelo;
		this.fechaAlta = fechaAlta;
		this.fechaBaja = null;
		this.motivoBaja = "";		
	}

	public LocalDate getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(LocalDate fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public String getMotivoBaja() {
		return motivoBaja;
	}

	public void setMotivoBaja(String motivoBaja) {
		this.motivoBaja = motivoBaja;
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

	public LocalDate getFechaAlta() {
		return fechaAlta;
	}
}
