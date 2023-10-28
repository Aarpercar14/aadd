package servicio;

import repositorio.EntidadNoEncontrada;
import repositorio.FactoriaRepositorios;
import repositorio.Repositorio;
import repositorio.RepositorioException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import dominio.DistanciaCoordenadas;
import dominio.RepositorioSitiosTuristicosJSON;
import dominio.ResumenSitioTuristico;
import dominio.SitioTuristico;

public class ServicioSitiosTuristicos implements IServicioSitiosTuristicos {

	private RepositorioSitiosTuristicosJSON repositorioJSON = FactoriaRepositorios
			.getRepositorio(SitioTuristico.class);

	public String crear(String nombre, String descripcion, String URL, String categorias, String infoComplementaria,
			String direccionImg) {
		if(nombre == null || nombre.isEmpty())
			throw new IllegalArgumentException("nombre: no debe ser nulo ni vacio");
		if(descripcion == null || descripcion .isEmpty())
			throw new IllegalArgumentException("descripcion: no debe ser nulo ni vacio");
		if(URL == null || URL .isEmpty())
			throw new IllegalArgumentException("URL: no debe ser nulo ni vacio");
		if(categorias == null || categorias .isEmpty())
			throw new IllegalArgumentException("descripcion: no debe ser nulo ni vacio");
		if(infoComplementaria == null || infoComplementaria .isEmpty())
			throw new IllegalArgumentException("infoComplementaria: no debe ser nulo ni vacio");
		if(direccionImg == null || direccionImg.isEmpty())
			throw new IllegalArgumentException("direccionImg: no debe ser nula ni vacia");
		
		SitioTuristico sitio = new SitioTuristico(nombre,descripcion,URL,categorias,infoComplementaria,direccionImg);
		
		try {
			String id = repositorioJSON.add(sitio);
			return id;
		} catch(RepositorioException e) {
			e.printStackTrace();
		}
		return "";
	}

	@Override
	public List<ResumenSitioTuristico> getSitiosInteres(String cordX1, String cordY1) {
		String sitios = "http://api.geonames.org/findNearbyWikipedia?lat=" + cordX1 + "&lng=" + cordY1
				+ "&username=aadd";
		DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
		Document documento = null;
		try {
			DocumentBuilder analizador = factoria.newDocumentBuilder();
			documento = analizador.parse(sitios);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		NodeList nodos = documento.getElementsByTagName("entry");
		LinkedList<ResumenSitioTuristico> listaResumen = new LinkedList<ResumenSitioTuristico>();
		for (int i = 0; i < nodos.getLength(); i++) {
			Element elemento = (Element) nodos.item(i);
			double cordX2 = Double.parseDouble(elemento.getElementsByTagName("lat").item(0).getLocalName());
			double cordY2 = Double.parseDouble(elemento.getElementsByTagName("lng").item(0).getLocalName());
			DistanciaCoordenadas distancia = DistanciaCoordenadas.obtenerDistancia(Double.parseDouble(cordX1),
					Double.parseDouble(cordY1), cordX2, cordY2);
			ResumenSitioTuristico s = new ResumenSitioTuristico(
					elemento.getElementsByTagName("title").item(0).getLocalName(),
					elemento.getElementsByTagName("summary").item(0).getLocalName(), distancia,
					elemento.getElementsByTagName("wikipediaUrl").item(0).getLocalName());

			listaResumen.add(s);
		}

		return listaResumen;
	}

	@Override
	public String getInfoSitio(String id) {
		try {
			return repositorioJSON.getById(id).toString();
		} catch (RepositorioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EntidadNoEncontrada e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void getSitioTuristicoInfo(URL url) throws IOException {
		InputStreamReader objeto = new InputStreamReader(url.openStream());
		JsonReader jsonReader = Json.createReader(objeto);
		JsonObject obj = jsonReader.readObject();

		JsonObject nombreObj = obj.getJsonObject("http://www.w3.org/2000/01/rdf-schema#label");
		JsonObject resumenObj = obj.getJsonObject("http://dbpedia.org/ontology/abstract");
		JsonObject categoriasObj = obj.getJsonObject("http://www.w3.org/1999/02/22-rdf-syntax-ns#type");
		JsonObject enlaceExternoObj = obj.getJsonObject("http://dbpedia.org/ontology/wikiPageExternalLink");
		JsonObject imagenObj = obj.getJsonObject("http://es.dbpedia.org/property/imagen");

		String nombre = nombreObj.getString("value");
		String resumen = resumenObj.getString("value");
		String categorias = categoriasObj.getString("value");
		String enlaceExterno = enlaceExternoObj.getString("value");
		String imagen = imagenObj.getString("value");
		SitioTuristico sitio = new SitioTuristico(nombre, resumen, categorias, enlaceExterno, imagen, url.toString());
		try {
			repositorioJSON.add(sitio);
		} catch (RepositorioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}