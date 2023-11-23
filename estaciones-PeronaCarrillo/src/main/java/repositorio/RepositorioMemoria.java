package repositorio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RepositorioMemoria<T extends Identificable> implements RepositorioString<T> {
	private HashMap<String, T> entidades = new HashMap<>();

	private int id = 1;
	
	@Override
	public String add(T entity) {
		String id = String.valueOf(this.id++);
		entity.setId(id);
		this.entidades.put(id, entity);
		
		return id;
	}

	@Override
	public void update(T entity) throws EntidadNoEncontrada {
		
		if(!this.entidades.containsKey(entity.getId()))
			throw new EntidadNoEncontrada(entity.getId() + " no existe en el repositorio");
		
		this.entidades.put(entity.getId(), entity);
	}
	
	
	@Override
	public void delete(T entity) throws EntidadNoEncontrada {
		if(!this.entidades.containsKey(entity.getId()))
			throw new EntidadNoEncontrada(entity.getId() + " no existe en el repositorio");
		
		this.entidades.remove(entity.getId());
			
	}
	
	@Override
	public T getById(String id) throws EntidadNoEncontrada{
		if(!this.entidades.containsKey(id))
			throw new EntidadNoEncontrada(id + " no existe en el repositorio");
		
		return this.entidades.get(id);
		
		
	}

	public List<T> getAll() throws RepositorioException {
		return new ArrayList<>(this.entidades.values());
	}

	public List<String> getIds() throws RepositorioException {
		return new ArrayList<>(this.entidades.keySet());
	}

	@Override
	public Object getCollection() {
		// TODO Auto-generated method stub
		return null;
	}
}
