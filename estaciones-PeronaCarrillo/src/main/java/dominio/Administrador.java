package dominio;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;
import servicio.FactoriaServicios;
import servicio.IServicioEstaciones;
import servicio.IServicioSitiosTuristicos;


public class Administrador extends Usuario {
	
	public IServicioSitiosTuristicos servSitios = FactoriaServicios.getServicio(IServicioSitiosTuristicos.class);
	public IServicioEstaciones servEstaciones = FactoriaServicios.getServicio(IServicioEstaciones.class);

	public Administrador(String nombre, String apellidos, String email, String password, String telefono,
			LocalDate fechaNacimiento) {

		super(nombre, apellidos, email, password, telefono, fechaNacimiento);

	}

	public String DarDeAltaEstacion(String nom, int num, String post, String x, String y) {

		String id = servEstaciones.crear(nom, num, post, x, y);

		return id;

	}

	public void EstablecerSitioTuristico(String id, List<ResumenSitioTuristico> sitios)
			throws RepositorioException, EntidadNoEncontrada {

		Estacionamiento estacion = servEstaciones.getEstacion(id);
		estacion.setSitiosTuristicos(sitios);

	}

	public List<ResumenSitioTuristico> getSitiosTuristicos(String id) throws RepositorioException, EntidadNoEncontrada, SAXException, ParserConfigurationException, SitioTuristicoException {

		Estacionamiento estacion = servEstaciones.getEstacion(id);
		List<ResumenSitioTuristico> sitios = new LinkedList<>(
				servSitios.getSitiosInteres(estacion.getCordX(), estacion.getCordY()));
		return sitios;

	}

	public Estacionamiento getEstacionamiento(String id) throws RepositorioException, EntidadNoEncontrada {

		return servEstaciones.getEstacion(id);

	}
	
	public String guardarSitioTuristicoJson(ResumenSitioTuristico resumen) {
		return servSitios.crear(resumen.getURL());
	}
	
}
