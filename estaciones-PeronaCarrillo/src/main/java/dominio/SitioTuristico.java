package dominio;

import java.util.UUID;

import repositorio.Identificable;

public class SitioTuristico implements Identificable{
	private String nombre;
	private String descripcion;
	private DistanciaCoordenadas distancia;
	private String URL;
	private String id;
	
	public SitioTuristico(String nombre, String des, DistanciaCoordenadas distancia, String U) {
		this.nombre=nombre;
		this.descripcion=des;
		this.distancia = distancia;
		this.URL=U;
		id=UUID.randomUUID().toString();
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public DistanciaCoordenadas getDistancia() {
		return distancia;
	}
	
	public double getDistanciaX() {
		return distancia.getX();
	}
	
	public double getDistanciaY() {
		return distancia.getY();
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String URL) {
		this.URL = URL;
	}
	

}
