package dominio;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;

import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;

public class Administrador extends Usuario {
	
	private RepositorioMemoriaEstacion estacionamientos;
	HashMap<String,LinkedList<SitioTuristico>> sitiosEstacionamiento; 
	
	public Administrador(String nombre, String apellidos, String email, String password, String telefono, LocalDate fechaNacimiento) {
		super(nombre, apellidos, email, password, telefono, fechaNacimiento);
		this.sitiosEstacionamiento=new HashMap<>();

	}
	
	public String DarDeAltaEstacion(String nom, int num, int post, double x, double y) {
		Estacionamiento est=new Estacionamiento(nom,num,post,x,y);
		return(estacionamientos.add(est));
	}	
	public void EstablecerSitioTuristico(String id,LinkedList<SitioTuristico> sitios) {
		sitiosEstacionamiento.put(id,sitios);
	}
	public LinkedList<SitioTuristico> getSitiosTuristicos(String id){
		return sitiosEstacionamiento.get(id);
	}
	public Estacionamiento getEstacionamiento(String id) throws RepositorioException, EntidadNoEncontrada {
		return estacionamientos.getById(id);
	}
}
