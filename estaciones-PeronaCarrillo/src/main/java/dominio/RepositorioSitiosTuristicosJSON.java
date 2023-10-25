package dominio;

import repositorio.RepositorioJSON;

public class RepositorioSitiosTuristicosJSON extends RepositorioJSON<SitioTuristico>{
	
	@Override
	public Class<SitioTuristico> getClase() {
		return SitioTuristico.class;
	}

}
