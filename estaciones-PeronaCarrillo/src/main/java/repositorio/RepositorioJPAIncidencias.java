package repositorio;

import dominio.Incidencia;

public class RepositorioJPAIncidencias extends RepositorioJPA<Incidencia>{

	@Override
	public Class<Incidencia> getClase() {
		return Incidencia.class;
	}

	@Override
	public String getNombre() {
		return Incidencia.class.getName().substring(Incidencia.class.getName().lastIndexOf(".") + 1);
	}

	@Override
	public Object getCollection() {
		// TODO Auto-generated method stub
		return null;
	}


}
