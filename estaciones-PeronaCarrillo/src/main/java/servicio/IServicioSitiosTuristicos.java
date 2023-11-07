package servicio;

import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import dominio.ResumenSitioTuristico;
import dominio.SitioTuristico;
import dominio.SitioTuristicoException;


public interface IServicioSitiosTuristicos {

	String crear(String uRL);
	
	List<ResumenSitioTuristico> getSitiosInteres(double cordX, double cordY) throws SAXException, ParserConfigurationException, SitioTuristicoException;
	
	SitioTuristico getInfoSitio(String id) throws SitioTuristicoException;
}
 