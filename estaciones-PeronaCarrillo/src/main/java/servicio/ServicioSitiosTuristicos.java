package servicio;

import repositorio.EntidadNoEncontrada;
import repositorio.FactoriaRepositorios;
import repositorio.Repositorio;
import repositorio.RepositorioException;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import dominio.SitioTuristico;


public class ServicioSitiosTuristicos implements IServicioSitiosTuristicos{
	
	private Repositorio<SitioTuristico, String> repositorio = FactoriaRepositorios.getRepositorio(SitioTuristico.class);


	@Override
	public List<SitioTuristico> getSitiosInteres(String cordX, String cordY){
		String sitios="http://api.geonames.org/findNearbyWikipedia?lat="+cordX+"&lng="+cordY+"&username=plasnake";//cambiar usuario
		DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
		Document documento = null;
		try {
			DocumentBuilder analizador = factoria.newDocumentBuilder();
			 documento = analizador.parse(sitios);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		NodeList nodos=documento.getElementsByTagName("entry");
		LinkedList<SitioTuristico> lista=new LinkedList<SitioTuristico>();
		for(int i=0;i<nodos.getLength();i++)
		{
			Element elemento= (Element) nodos.item(i);
			SitioTuristico s=new SitioTuristico(elemento.getElementsByTagName("title").item(0).getLocalName(),elemento.getElementsByTagName("summary").item(0).getLocalName(),Integer.parseInt(elemento.getElementsByTagName("lat").item(0).getLocalName()),Integer.parseInt(elemento.getElementsByTagName("lng").item(0).getLocalName()),elemento.getElementsByTagName("wikipediaUrl").item(0).getLocalName());
			lista.add(s);
		}
		return  lista;
	}

	@Override
	public String getInfoSitio(String id) {
		try {
			return repositorio.getById(id).toString();
		} catch (RepositorioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EntidadNoEncontrada e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
