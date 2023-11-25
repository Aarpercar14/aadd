package repositorio;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;

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
	public String add(Historico entity) throws RepositorioException {
		Document d = new Document();
		d.append("_id", entity.getId()).append("idBici", entity.getIdBici()).append("historico", entity.getHistorico())
				.append("idUltimaEstacion", entity.getUltimaEstacion());
		InsertOneResult resultado = coleccionSinCodificar.insertOne(d);
		if (resultado.getInsertedId() == null) {
			throw new RepositorioException("Error insertado");
		}
		return resultado.getInsertedId().asObjectId().getValue().toString();
	}

	@Override
	public MongoCollection<Historico> getCollection() {
		CodecRegistry defaultCodecRegistry = CodecRegistries.fromRegistries(
				MongoClientSettings.getDefaultCodecRegistry(),
				CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
		return database.getCollection("historico", Historico.class).withCodecRegistry(defaultCodecRegistry);
	}
}
