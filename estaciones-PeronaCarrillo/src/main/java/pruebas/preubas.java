package pruebas;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;
import org.xml.sax.SAXException;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.InsertOneResult;
import utils.PropertiesReader;
import dominio.Administrador;
import dominio.Estacionamiento;
import dominio.ResumenSitioTuristico;
import dominio.SitioTuristico;
import dominio.SitioTuristicoException;

public class preubas {

	public static void main(String[] args) {
		PropertiesReader properties;
		try {
			properties = new PropertiesReader("mongo.properties");

			String connectionString = properties.getProperty("mongouri");
			String databaseString = properties.getProperty("mongodatabase");

			MongoClient mongoClient = MongoClients.create(connectionString);

			MongoDatabase database = mongoClient.getDatabase(databaseString);

			CodecRegistry defaultCodecRegistry = CodecRegistries.fromRegistries(
					MongoClientSettings.getDefaultCodecRegistry(),
					CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));

			MongoCollection<Estacionamiento> coleccion = database.getCollection("Estacionamiento", Estacionamiento.class)
					.withCodecRegistry(defaultCodecRegistry);
			
			Estacionamiento estacion = new Estacionamiento("Saint Louis", 10, "30020", 40,-5);
			Administrador admin = new Administrador("admin", "jefe", "admin@gmail.com", "password", "612345678",
					LocalDate.now());
			
			try {
				List<ResumenSitioTuristico> sitios = new LinkedList<>(
						admin.servSitios.getSitiosInteres(estacion.getCordX(), estacion.getCordY()));
				estacion.setSitiosTuristicos(sitios);
				
				InsertOneResult s = coleccion.insertOne(estacion);
				System.out.println(s.getInsertedId().asObjectId().getValue().toString());
				
				Bson query=Filters.all("_id", s.getInsertedId());
				System.out.println(query);
				
				FindIterable<Estacionamiento> resultados=coleccion.find(query);
				System.out.println(resultados);
				
				MongoCursor<Estacionamiento> it=resultados.iterator();
				System.out.println(it.available());
				
				if(it.hasNext())  System.out.println(it.next().toString());
			} catch (SAXException | ParserConfigurationException | SitioTuristicoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
