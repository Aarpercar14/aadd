package repositorio;

import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.InsertOneResult;

public abstract class RepositorioMongoDB<T extends Identificable> implements RepositorioString<T> {
	
	public abstract MongoCollection<T> getCollection();

	@Override
	public String add(T entity) throws RepositorioException {
		InsertOneResult resultado= getCollection().insertOne(entity);
		return resultado.getInsertedId().asObjectId().getValue().toString();
	}

	@Override
	public void update(T entity) throws RepositorioException, EntidadNoEncontrada {
		// TODO Auto-generated method stub
		getCollection().replaceOne(new Document("_id",new ObjectId(entity.getId())),entity);
	}

	@Override
	public void delete(T entity) throws RepositorioException, EntidadNoEncontrada {
		// TODO Auto-generated method stub
		getCollection().deleteOne(new Document("_id",new ObjectId(entity.getId())));
	}

	@Override
	public T getById(String id) throws RepositorioException, EntidadNoEncontrada {
		ObjectId idB= new ObjectId(id);
		Bson query=Filters.all("_id", idB);
		FindIterable<T> resultados=getCollection().find(query);
		return resultados.first();
		//if(it.hasNext()) return (it.next());
			
		//return null;
	}

	@Override
	public List<T> getAll() throws RepositorioException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getIds() throws RepositorioException {
		// TODO Auto-generated method stub
		return null;
	}

}
