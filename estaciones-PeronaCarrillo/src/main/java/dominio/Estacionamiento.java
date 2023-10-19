package dominio;

import java.time.LocalDateTime;
import java.util.UUID;

import repositorio.Identificable;

public class Estacionamiento implements Identificable{
	private String nombre;
	private int numPuestos;
	private String postal;
	private String cordX;
	private String cordY;
	private LocalDateTime fechaAlta;
	private String id;
	
	public Estacionamiento(String nom, int num, String post, String x, String y) {
		this.nombre=nom;
		this.numPuestos=num;
		this.cordX=x;
		this.cordY=y;
		this.fechaAlta=LocalDateTime.now();
	}
	
	

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public int getNumPuestos() {
		return numPuestos;
	}



	public void setNumPuestos(int numPuestos) {
		this.numPuestos = numPuestos;
	}



	public String getPostal() {
		return postal;
	}



	public void setPostal(String postal) {
		this.postal = postal;
	}



	public String getCordX() {
		return cordX;
	}



	public void setCordX(String cordX) {
		this.cordX = cordX;
	}



	public String getCordY() {
		return cordY;
	}



	public void setCordY(String cordY) {
		this.cordY = cordY;
	}



	public LocalDateTime getFechaAlta() {
		return fechaAlta;
	}



	public void setFechaAlta(LocalDateTime fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

}
