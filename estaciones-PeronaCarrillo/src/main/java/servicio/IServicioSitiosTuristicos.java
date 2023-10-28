package servicio;

import java.util.List;

import dominio.ResumenSitioTuristico;


public interface IServicioSitiosTuristicos {

	
	List<ResumenSitioTuristico> getSitiosInteres(String cordX, String cordY);
	
	String getInfoSitio(String id);
}
 