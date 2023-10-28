package servicio;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import dominio.ResumenSitioTuristico;
import dominio.SitioTuristico;

public interface IServicioSitiosTuristicos {

	
	List<ResumenSitioTuristico> getSitiosInteres(String cordX, String cordY);
	
	String getInfoSitio(String id);
}
 