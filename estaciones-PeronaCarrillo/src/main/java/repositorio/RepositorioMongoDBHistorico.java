package repositorio;

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
import dominio.Historico;
import utils.PropertiesReader;

public class RepositorioMongoDBHistorico extends RepositorioMongoDB<Historico> {

	protected MongoClient mongoClient;
	protected MongoDatabase database;
	protected MongoCollection<Historico> coleccion;
	protected MongoCollection<Document> coleccionSinCodificar;

	public RepositorioMongoDBHistorico() {
		PropertiesReader properties;
		try {
			properties = new PropertiesReader("mongo.properties");
			String connectionString = properties.getProperty("mongouri");
			MongoClient mongoClient = MongoClients.create(connectionString);
			String mongoDatabase = properties.getProperty("mongodatabase");
			database = mongoClient.getDatabase(mongoDatabase);
			CodecRegistry defaultCodecRegistry = CodecRegistries.fromRegistries(
					MongoClientSettings.getDefaultCodecRegistry(),
					CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
			coleccion = database.getCollection("Historico", Historico.class).withCodecRegistry(defaultCodecRegistry);
			coleccionSinCodificar = database.getCollection("Historico");

		} catch (Exception e) {

		}
	}

	@Override
	public MongoCollection<Historico> getCollection() {
		CodecRegistry defaultCodecRegistry = CodecRegistries.fromRegistries(
				MongoClientSettings.getDefaultCodecRegistry(),
				CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
		return database.getCollection("historico", Historico.class).withCodecRegistry(defaultCodecRegistry);
	}
	
	@SuppressWarnings("unchecked")
	public static Historico getHistoricoNoRetirado(Repositorio<Historico, String> repos, String id) {
		Bson filter=Filters.and(Filters.eq("_id",id),Filters.eq("fecha_retiro",null));
		FindIterable<Historico> query=((MongoCollection<Historico>) repos.getCollection()).find(filter);
		MongoCursor<Historico> it = query.iterator();
		while (it.hasNext()) {
			return it.next();
		}
		return null;
	}
	
	public static void añadirEntrada(Repositorio<Historico, String> repos, Historico hid) {
		try {
			repos.update(hid);
		} catch (RepositorioException | EntidadNoEncontrada e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void borrarEntrada(Repositorio<Historico, String> repos, Historico hid) {
		try {
			repos.delete(hid);
		} catch (RepositorioException | EntidadNoEncontrada e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
