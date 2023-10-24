package repositorio;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class RepositorioJSON<T extends Identificable> implements RepositorioString {
	
	public final static String DIRECTORIO = "repositorio-json/";
	
	static {
		
		File directorio = new File(DIRECTORIO);
		
		if(!directorio.exists())
			directorio.mkdir();
		
	}
	
	public abstract Class<T> getClase();
	
	protected String getDocumento(String id) {
		
		return DIRECTORIO + getClase().getSimpleName() + "-" + id + ".json";
		
	}
	
	protected boolean checkDocumento(String id) {
		
		final String documento = getDocumento(id);
		
		File fichero = new File(documento);
		
		return fichero.exists();
		
	}
	
	protected void save(T entity) throws RepositorioException {
		
		final String documento = getDocumento(entity.getId());
		
		final File fichero = new File(documento);
		
		try {
			// TODO implementacion con JSON-B y JSON-P
		} catch (Exception e) {
			throw new RepositorioException("Error al guardar la entidad con id: " + entity.getId());
		}
		
	}
	
	protected T load(String id) throws RepositorioException, EntidadNoEncontrada {
		
		if(!checkDocumento(id))
			throw new EntidadNoEncontrada("La entidad no existe, id: " + id);
		
		final String documento = getDocumento(id);
		
		try {
			// TODO implementacion con JSON-B y JSON-P
		} catch (Exception e) {
			throw new RepositorioException("Error al cargar la entidad con id: " + id);
		}
	}

	/*** Fin métodos de apoyo ***/
	
	@Override
	public String add(T entidad) throws RepositorioException {
		
		String id = UUID.randomUUID().toString();
		
		entidad.getId();
		save(entidad);
		
		return id;
		
	}
	
	@Override
	public void update(T entidad) throws RepositorioException, EntidadNoEncontrada {
		
		if(!checkDocumento(entidad.getId()))
			throw new EntidadNoEncontrada("La entidad no existe, id: " + entidad.getId());
		
		save(entidad);
				
	}
	
	@Override
	public T getById(String id) throws RepositorioException, EntidadNoEncontrada {
		
		return load(id);
		
	}
	
	@Override
	public List<String> getIds() {
		LinkedList<String> resultado = new LinkedList<>();
		
		File directorio = new File(DIRECTORIO);
		
		File[] entidades = directorio.listFiles(f -> f.isFile() && f.getName().endsWith(".json"));
		
		final String prefijo = getClase().getSimpleName() + "-";
		for(File file : entidades) {
			
			String id = file.getName().substring(prefijo.length(), file.getName().length() - 4);
			
			resultado.add(id);
		}
		
		return resultado;
		
	}
	
	public List<T> getAll() throws RepositorioException {
		
		LinkedList<T> resultado = new LinkedList<>();
		
		for(String id : getIds()) {
			try {
				resultado.add(load(id));
			} catch (EntidadNoEncontrada e) {
				// No debería suceder
				e.printStackTrace();
			}
		}
		
		return resultado;
	}
	
}
