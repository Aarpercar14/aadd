package servicio;

import java.util.List;

import dominio.SitioTuristico;

public interface IServicioSitiosTuristicos {

	
	List<SitioTuristico> getSitiosInteres(double cordX, double cordY);
	
	String getInfoSitio(String id);
}
 