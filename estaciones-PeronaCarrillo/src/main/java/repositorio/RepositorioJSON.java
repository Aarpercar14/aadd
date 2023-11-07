package repositorio;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbConfig;
import javax.json.bind.config.PropertyNamingStrategy;
import javax.json.bind.config.PropertyOrderStrategy;
import javax.json.bind.spi.JsonbProvider;

public abstract class RepositorioJSON<T extends Identificable> implements RepositorioString<T> {

	public final static String DIRECTORIO = "repositorio-json/";

	static {
		File directorio = new File(DIRECTORIO);
		if (!directorio.exists())
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

	@SuppressWarnings("resource")
	protected void save(T entity) throws RepositorioException, IOException {

		final String documento = getDocumento(entity.getId());

		final FileWriter fichero = new FileWriter(documento);

		try {
			// TODO implementacion con JSON-B y JSON-P
			JsonbConfig config = new JsonbConfig().withNullValues(true).withFormatting(true)
					.withPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CASE_WITH_UNDERSCORES)
					.withPropertyOrderStrategy(PropertyOrderStrategy.LEXICOGRAPHICAL);
			Jsonb contexto = JsonbProvider.provider().create().withConfig(config).build();

			String cadenaJSON = contexto.toJson(entity);
			contexto.toJson(entity, new PrintStream("repositorio-json/sitioTuristico-"+entity.getId()+".json"));
		} catch (Exception e) {
			throw new RepositorioException("Error al guardar la entidad con id: " + entity.getId());
		}

	}

	protected T load(String id) throws RepositorioException, EntidadNoEncontrada {

		if (!checkDocumento(id))
			throw new EntidadNoEncontrada("La entidad no existe, id: " + id);

		final String documento = getDocumento(id);
		try {
			// TODO implementacion con JSON-B y JSON-P
			JsonbConfig config = new JsonbConfig().withNullValues(true).withFormatting(true)
					.withPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CASE_WITH_UNDERSCORES)
					.withPropertyOrderStrategy(PropertyOrderStrategy.LEXICOGRAPHICAL);
			Jsonb contexto = JsonbProvider.provider().create().withConfig(config).build();
			Reader entrada = new FileReader(documento);
			T entity = contexto.fromJson(entrada, getClase());
			return entity;
		} catch (Exception e) {
			throw new RepositorioException("Error al cargar la entidad con id: " + id);
		}
	}

	/*** Fin métodos de apoyo ***/

	@Override
	public String add(T entity) throws RepositorioException {

		String id=entity.getId();

		try {
			save(entity);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public void update(T entidad) throws RepositorioException, EntidadNoEncontrada {

		if (!checkDocumento(entidad.getId()))
			throw new EntidadNoEncontrada("La entidad no existe, id: " + entidad.getId());
		try {
			save(entidad);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(T entity) throws EntidadNoEncontrada {

		if (!checkDocumento(entity.getId()))
			throw new EntidadNoEncontrada("La entidad no existe, id: " + entity.getId());

		final String documento = getDocumento(entity.getId());

		File fichero = new File(documento);

		fichero.delete();

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
		for (File file : entidades) {

			String id = file.getName().substring(prefijo.length(), file.getName().length() - 4);

			resultado.add(id);
		}

		return resultado;

	}

	public List<T> getAll() throws RepositorioException {

		LinkedList<T> resultado = new LinkedList<>();

		for (String id : getIds()) {
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
