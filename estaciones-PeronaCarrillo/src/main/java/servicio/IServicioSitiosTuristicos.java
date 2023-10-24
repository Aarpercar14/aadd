package servicio;

import java.util.List;
import dominio.SitioTuristico;

public interface IServicioSitiosTuristicos {

	
	List<SitioTuristico> getSitiosInteres(String cordX, String cordY);
	
	String getInfoSitio(String id);
}
 