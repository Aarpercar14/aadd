package dominio;


public class ResumenSitioTuristico {
	private String nombre;
	private String descripcion;
	private DistanciaCoordenadas distancia;
	private String URL;
	private String id;
	
	public ResumenSitioTuristico(String nombre, String descripcion, DistanciaCoordenadas distancia, String URL) {
		this.nombre=nombre;
		this.descripcion=descripcion;
		this.distancia = distancia;
		this.URL=URL;
		String[] segmentos = URL.split("/");
		this.id = segmentos[segmentos.length - 1];
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public DistanciaCoordenadas getDistancia() {
		return distancia;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String URL) {
		this.URL = URL;
	}
	
	@Override
	public String toString() {
		return "ResumenSitioTuristico [id = "+ id +", nombre= " + nombre +" descripcion= " + descripcion + ", distancia = " + distancia.toString() + ", URL= " + URL +" ]";	
	}
	
}
