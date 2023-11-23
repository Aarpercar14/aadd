package repositorio;

import dominio.SitioTuristico;

public class RepositorioSitiosTuristicosJSON extends RepositorioJSON<SitioTuristico>{
	
	public RepositorioSitiosTuristicosJSON() {
		super();
	}
	
	@Override
	public Class<SitioTuristico> getClase() {
		return SitioTuristico.class;
	}

	@Override
	public Object getCollection() {
		// TODO Auto-generated method stub
		return null;
	}

}
