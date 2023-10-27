package dominio;

import repositorio.RepositorioJSON;

public class RepositorioSitiosTuristicosJSON extends RepositorioJSON<SitioTuristico>{
	
	public RepositorioSitiosTuristicosJSON() {
		super();
	}
	
	@Override
	public Class<SitioTuristico> getClase() {
		return SitioTuristico.class;
	}

}
