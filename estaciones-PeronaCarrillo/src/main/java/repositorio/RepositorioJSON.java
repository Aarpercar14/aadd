package repositorio;

import java.io.File;

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

}
