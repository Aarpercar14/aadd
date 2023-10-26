package dominio;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import repositorio.Identificable;

public class Estacionamiento implements Identificable{
	private String nombre;
	private int numPuestos;
	private String postal;
	private String cordX;
	private String cordY;
	private LocalDateTime fechaAlta;
	private String id;
	
	private List<ResumenSitioTuristico> sitiosTuristicos;
	
	public Estacionamiento(String nombre, int numero, String postal, String x, String y) {
		this.nombre=nombre;
		this.numPuestos=numero;
		this.cordX=x;
		this.cordY=y;
		this.fechaAlta=LocalDateTime.now();
		this.sitiosTuristicos = new LinkedList<>();
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
	
	public List<ResumenSitioTuristico> getSitiosTuristicos(){
		return new LinkedList<>(this.sitiosTuristicos);
	}
	
	public void setSitiosTuristicos(List<ResumenSitioTuristico> sitiosTuristicos) {
		this.sitiosTuristicos.addAll(sitiosTuristicos);
	}

}
