package pruebas;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;

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

import dominio.Estacionamiento;
import dominio.SitioTuristico;

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
			
			Estacionamiento editorial = new Estacionamiento("Saint Louis", 10, "30020", 40,-5);
			
			InsertOneResult s = coleccion.insertOne(editorial);
			System.out.println(s.getInsertedId().asObjectId().getValue().toString());
			
			Bson query=Filters.all("_id", s.getInsertedId());
			System.out.println(s.getInsertedId().getClass()
					);
			
			FindIterable<Estacionamiento> resultados=coleccion.find(query);
			System.out.println(resultados);
			
			System.out.println(resultados.first().toString());
			MongoCursor<Estacionamiento> it=resultados.iterator();
			System.out.println(it.available());
			
			if(it.hasNext())  System.out.println(it.next().toString());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
