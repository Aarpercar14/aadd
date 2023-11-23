package repositorio;

import java.util.ArrayList;
import java.util.Arrays;
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
import com.mongodb.client.result.InsertOneResult;

import dominio.Bicicleta;
import dominio.Estacionamiento;
import utils.PropertiesReader;

public class RepositorioMongoDBEstaciones extends RepositorioMongoDB<Estacionamiento>{

	protected MongoClient mongoClient;
	protected MongoDatabase database;
	protected MongoCollection<Estacionamiento> coleccion;
	protected MongoCollection<Document> coleccionSinCodificar;

	public RepositorioMongoDBEstaciones() {
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
			coleccion.createIndex(Indexes.geo2dsphere("cord"));
			coleccion = database.getCollection("estacionamiento", Estacionamiento.class)
					.withCodecRegistry(defaultCodecRegistry);
			coleccionSinCodificar = database.getCollection("Estacionamiento");

		} catch (Exception e) {

		}
	}

	@Override
	public MongoCollection<Estacionamiento> getCollection() {
		return coleccion;
	}

	@Override
	public String add(Estacionamiento entity) throws RepositorioException {
		Document d = new Document();
		d.append("_id", entity.getId()).append("nombre", entity.getNombre()).append("numPuesto", entity.getNumPuestos())
				.append("fechaAlta", entity.getFechaAlta()).append("sitioTuristico", entity.getSitiosTuristicos())
				.append("bicicletas", entity.getBicicletas());
		double[] cord={entity.getCordX(),entity.getCordY()};
		d.append("cord", cord);
		
		InsertOneResult resultado = coleccionSinCodificar.insertOne(d);
		if (resultado.getInsertedId() == null) {
			throw new RepositorioException("Error insertado");
		}
		return resultado.getInsertedId().asObjectId().getValue().toString();
	}

	public List<Estacionamiento> getEstacionesByDistancia(double x, double y) {
		ArrayList<Estacionamiento> estaciones = new ArrayList<Estacionamiento>();
		double[] coordenada = { x, y };
		Document consulta= new Document("cord",new Document("$near",new Document("$geometry",coordenada)));
		Bson query = Filters.all("cord", consulta);
		FindIterable<Estacionamiento> resultado = getCollection().find(query);
		MongoCursor<Estacionamiento> it = resultado.iterator();
		while (it.hasNext()) {
			estaciones.add(it.next());
		}
		return estaciones;
	}

}
