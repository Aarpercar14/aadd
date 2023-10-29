package servicio;

import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import dominio.ResumenSitioTuristico;


public interface IServicioSitiosTuristicos {

	public String crear(String uRL);
	
	List<ResumenSitioTuristico> getSitiosInteres(String cordX, String cordY) throws SAXException, ParserConfigurationException;
	
	String getInfoSitio(String id);
}
 