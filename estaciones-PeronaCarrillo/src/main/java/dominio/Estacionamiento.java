package dominio;

import java.time.LocalDateTime;
import java.util.UUID;

import repositorio.Identificable;

public class Estacionamiento implements Identificable{
	private String nombre;
	private int numPuestos;
	private int postal;
	private double cordX;
	private double cordY;
	private LocalDateTime fechaAlta;
	private final String id;
	
	public Estacionamiento(String nom, int num, int post, double x, double y) {
		this.nombre=nom;
		this.numPuestos=num;
		this.cordX=x;
		this.cordY=y;
		this.fechaAlta=LocalDateTime.now();
		this.id=UUID.randomUUID().toString();
	}
	
	

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
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



	public int getPostal() {
		return postal;
	}



	public void setPostal(int postal) {
		this.postal = postal;
	}



	public double getCordX() {
		return cordX;
	}



	public void setCordX(double cordX) {
		this.cordX = cordX;
	}



	public double getCordY() {
		return cordY;
	}



	public void setCordY(double cordY) {
		this.cordY = cordY;
	}



	public LocalDateTime getFechaAlta() {
		return fechaAlta;
	}



	public void setFechaAlta(LocalDateTime fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

}
