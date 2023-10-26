package dominio;

import java.util.UUID;

import repositorio.Identificable;

public class SitioTuristico implements Identificable{
	private String nombre;
	private String descripcion;
	private DistanciaCoordenadas distancia;
	private String URL;
	private String id;
	private String categoria;
	private String infoComplementaria;
	private String direccionImg;
	
	public SitioTuristico(String nombre, String descripcion, DistanciaCoordenadas distancia, String uRL, String id,
			String categoria, String infoComplementaria, String direccionImg) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.distancia = distancia;
		this.URL = uRL;
		this.id = UUID.randomUUID().toString();
		this.categoria = categoria;
		this.infoComplementaria = infoComplementaria;
		this.direccionImg = direccionImg;
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

	public String getCategoria() {
		return categoria;
	}

	public String getInfoComplementaria() {
		return infoComplementaria;
	}

	public String getDireccionImg() {
		return direccionImg;
	}
	

}
