package servicio;

import java.util.List;

import dominio.ResumenSitioTuristico;
import dominio.SitioTuristico;

public interface IServicioSitiosTuristicos {

	
	List<ResumenSitioTuristico> getSitiosInteres(String cordX, String cordY);
	
	String getInfoSitio(String id);
}
 