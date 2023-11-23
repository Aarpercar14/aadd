package repositorio;

import dominio.Estacionamiento;

public class RepositorioMemoriaEstacion extends RepositorioMemoria<Estacionamiento>{
	
	public RepositorioMemoriaEstacion() {
		super();
	}
	
	public static String getEstacionLibre(Repositorio<Estacionamiento, String> repositorioEstaciones) throws RepositorioException {
		for(Estacionamiento e : repositorioEstaciones.getAll()) {
			if(e.haySitioLibre())
				return e.getId();
		}
		return null;
	}

}
