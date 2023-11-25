package pruebas;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;

import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import utils.PropertiesReader;

import dominio.Bicicleta;
import dominio.Estacionamiento;
import dominio.Incidencia;

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
			System.out.println(s);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
