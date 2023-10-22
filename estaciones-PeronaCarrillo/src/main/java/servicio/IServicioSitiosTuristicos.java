package servicio;

import java.util.List;

import org.w3c.dom.NodeList;

import dominio.SitioTuristico;

public interface IServicioSitiosTuristicos {

	
	List<SitioTuristico> getSitiosInteres(int cordX, int cordY);
	
	String getInfoSitio(String id);
}
 