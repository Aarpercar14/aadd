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
		int i=URL.length();
		char[] c=URL.toCharArray();
		String id="";
		while(i>0 && c[i]!='/') {
			id=c[i]+id;
		}
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
	
}
