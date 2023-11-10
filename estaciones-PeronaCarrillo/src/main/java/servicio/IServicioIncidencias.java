package servicio;

import dominio.Bicicleta;
import dominio.Incidencia;

public interface IServicioIncidencias {
	
	Incidencia crear(String idBici,String descripcionIncidencia);
	
	void gestionDeLasIncidencias(String cierre, String operario, String incidencia);
	
	Incidencia recuperarIncidencia();

}
