package repositorio;

import com.mongodb.client.MongoCollection;

import dominio.Estacionamiento;

public class RepositorioMongoDBEstaciones extends RepositorioMongoDB<Estacionamiento>{

	@Override
	public MongoCollection<Estacionamiento> getCollection() {
		// TODO Auto-generated method stub
		return null;
	}

}
