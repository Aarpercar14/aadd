package repositorio;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.InsertOneResult;

public abstract class RepositorioMongoDB<T extends Identificable> implements RepositorioString<T> {
	
	public abstract MongoCollection<T> getCollection();

	@Override
	public String add(T entity) throws RepositorioException {
		InsertOneResult resultado= getCollection().insertOne(entity);
		if(resultado.getInsertedId()==null) {
			throw new RepositorioException("Error insertado");
		}
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
		 List<T> coll =getCollection().find().into(new ArrayList<>());
		 for(T t:coll) {
			 if(t.getId()==id) return t;
		 }
		 return null;
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
