package repositorio;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
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
import com.mongodb.client.model.Indexes;
import dominio.Estacionamiento;
import utils.PropertiesReader;

public class RepositorioMongoDBEstaciones extends RepositorioMongoDB<Estacionamiento> {

	protected MongoClient mongoClient;
	protected MongoDatabase database;
	protected MongoCollection<Estacionamiento> coleccion;
	protected MongoCollection<Document> coleccionSinCodificar;

	public RepositorioMongoDBEstaciones() {
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
			
			coleccion = database.getCollection("estacionamiento", Estacionamiento.class).withCodecRegistry(defaultCodecRegistry);
			coleccionSinCodificar = database.getCollection("Estacionamiento");
			coleccion.createIndex(Indexes.geo2dsphere("cord"));

		} catch (Exception e) {

		}
	}

	@Override
	public MongoCollection<Estacionamiento> getCollection() {
		return coleccion;
	}

	

	public static List<Estacionamiento> getEstacionesByDistancia(Repositorio<Estacionamiento, String> repos, double x,
			double y) {
		ArrayList<Estacionamiento> estaciones = new ArrayList<Estacionamiento>();
		double[] coordenada = { x, y };
		Document consulta = new Document("cord", new Document("$near", new Document("$geometry", coordenada)));
		Bson query = Filters.all("cord", consulta);
		FindIterable<Estacionamiento> resultado;
		resultado = ((MongoCollection<Estacionamiento>) repos.getCollection()).find(query);
		MongoCursor<Estacionamiento> it = resultado.iterator();
		while (it.hasNext()) {
			estaciones.add(it.next());
		}
		return estaciones;
	}

}
