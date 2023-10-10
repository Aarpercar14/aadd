package dominio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RepositorioMemoria<T extends Identificable> implements RepositorioString<T> {
	private HashMap<String, T> entidades = new HashMap<>();

	private int id = 1;

	public String add(T entity) {
		String id = String.valueOf(this.id++);
		entity.setId(id);
		this.entidades.put(id, entity);
		return id;
	}

	public void update(T entity) throws EntidadNoEncontrada {

		try {
			if (entidades.containsValue(entity)) {
				entidades.replace(entity.getId(), entity);
			} else {
				throw new EntidadNoEncontrada();
			}
		} catch (EntidadNoEncontrada e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void delete(T entity) throws EntidadNoEncontrada {
		try {
			if (entidades.containsValue(entity)) {
				entidades.remove(entity.getId());
			} else {
				throw new EntidadNoEncontrada();
			}
		} catch (EntidadNoEncontrada e) {
			e.printStackTrace();
		}
	}
	
	public T getById(String id) throws RepositorioException, EntidadNoEncontrada{
		try {
			if(entidades.containsKey(id))
				return entidades.get(id);
			else
				throw new EntidadNoEncontrada();
		} catch (EntidadNoEncontrada e) {
			e.printStackTrace();
		}
		return null;
		
	}

	public List<T> getAll() throws RepositorioException {
		return new ArrayList<T>(entidades.values());
	}

	public List<String> getIds() throws RepositorioException {
		return new ArrayList<String>(entidades.keySet());
	}
}
