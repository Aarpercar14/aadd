package dominio;

import java.util.UUID;

public class SitioTuristico implements Identificable{
	private String nombre;
	private String descripcion;
	private double coordX;
	private double coordY;
	private String URL;
	private String id;
	
	public SitioTuristico(String nombre, String des,double x, double y, String U) {
		this.nombre=nombre;
		this.descripcion=des;
		this.coordX=x;
		this.coordY=y;
		this.URL=U;
		id=UUID.randomUUID().toString();
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getX() {
		return coordX;
	}
	public double getY() {
		return coordY;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}
	

}
