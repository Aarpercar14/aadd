package repositorio;

import java.util.List;
import java.util.stream.Collectors;

import especificacion.Especificacion;

public interface Repositorio <T, K> {

	String add(T entity) throws RepositorioException;
	
	void update(T entity) throws RepositorioException, EntidadNoEncontrada;
	
	void delete(T entity) throws RepositorioException, EntidadNoEncontrada;
	
	T getById(K id) throws RepositorioException, EntidadNoEncontrada;
	
	List<T> getAll() throws RepositorioException;
	
	List<K> getIds() throws RepositorioException;
	
	default List<T> getByEspecificacion(Especificacion<T> spec) throws RepositorioException {
		return getAll().stream().filter(obj -> spec.isSatisfiedBy(obj))
				.collect(Collectors.toList());
	}

	Object getCollection();
}
