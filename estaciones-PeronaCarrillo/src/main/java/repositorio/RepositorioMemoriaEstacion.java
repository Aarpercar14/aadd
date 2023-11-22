package repositorio;

import dominio.Estacionamiento;

public class RepositorioMemoriaEstacion extends RepositorioMemoria<Estacionamiento>{
	
	public RepositorioMemoriaEstacion() {
		super();
	}
	
	public static String getEstacionLibre(Repositorio<Estacionamiento, String> repositorioEstciones) throws RepositorioException {
		for(Estacionamiento e : repositorioEstciones.getAll()) {
			if(e.haySitioLibre())
				return e.getId();
		}
		return null;
	}

}
