package dominio;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;

import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;
import servicio.FactoriaServicios;
import servicio.IServicioEstaciones;
import servicio.IServicioSitiosTuristicos;

public class Administrador extends Usuario {
	
	HashMap<String,LinkedList<SitioTuristico>> sitiosEstacionamiento; 
	private IServicioEstaciones servEstaciones = FactoriaServicios.getServicio(IServicioEstaciones.class);
	private IServicioSitiosTuristicos servSitios = FactoriaServicios.getServicio(IServicioSitiosTuristicos.class);
	
	public Administrador(String nombre, String apellidos, String email, String password, String telefono, LocalDate fechaNacimiento) {
		super(nombre, apellidos, email, password, telefono, fechaNacimiento);
		this.sitiosEstacionamiento=new HashMap<>();

	}
	
	public String DarDeAltaEstacion(String nom, int num, String post, String x, String y) {
			
			String id = servEstaciones.crear(nom, num, post, x, y);
			
			return id;		
		
	}	
	public void EstablecerSitioTuristico(String id,LinkedList<SitioTuristico> sitios) {
		sitiosEstacionamiento.put(id,sitios);
	}
	public LinkedList<SitioTuristico> getSitiosTuristicos(String id){
		return sitiosEstacionamiento.get(id);
	}
	public Estacionamiento getEstacionamiento(String id) throws RepositorioException, EntidadNoEncontrada {
		return servEstaciones.getEstacion(id);
	}
}
