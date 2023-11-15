package servicio;

import java.util.List;

import dominio.Bicicleta;
import dominio.Incidencia;

public interface IServicioIncidencias {
	
	Incidencia crear(Bicicleta bici,String descripcionIncidencia);
	
	void gestionDeLasIncidencias(String cierre, String operario, String incidencia,String nuevoEstado);
	
	List<Incidencia> recuperarIncidencia();

}
