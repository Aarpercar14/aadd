package dto;

import java.io.Serializable;

public class EstacionesDTO implements Serializable{
	private String id;
	private String nombre;
	private int numPuestos;
	private String postal;
	private double cordY;
	private double cordX;
	public EstacionesDTO(String id, String nombre, int numPuestos, String postal, double cordY, double cordX) {
		this.id = id;
		this.nombre = nombre;
		this.numPuestos = numPuestos;
		this.postal = postal;
		this.cordY = cordY;
		this.cordX = cordX;
	}
	public String getId() {
		return id;
	}
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
	public double getCordY() {
		return cordY;
	}
	public void setCordY(double cordY) {
		this.cordY = cordY;
	}
	public double getCordX() {
		return cordX;
	}
	public void setCordX(double cordX) {
		this.cordX = cordX;
	}
	
	
	

}
