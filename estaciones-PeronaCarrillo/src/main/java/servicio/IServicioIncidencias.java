package servicio;

import java.util.List;

import dominio.Bicicleta;
import dominio.Incidencia;
import dominio.dto.IncidenciaDTO;

public interface IServicioIncidencias {
	
	Incidencia crear(Bicicleta bici,String descripcionIncidencia);
	
	void gestionDeLasIncidencias(String cierre, String operario, String incidencia,String nuevoEstado);
	
	List<Incidencia> recuperarIncidencias();
	
	public List<IncidenciaDTO> recuperarIncidenciasDTO(); 
	
	public IncidenciaDTO getIncidenciaDTO(String id);

}
