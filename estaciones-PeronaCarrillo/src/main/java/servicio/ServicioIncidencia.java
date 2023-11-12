package servicio;

import java.time.LocalDateTime;

import dominio.Incidencia;
import repositorio.Repositorio;
import repositorio.RepositorioException;

public class ServicioIncidencia implements IServicioIncidencias{
	private Repositorio<Incidencia,String> repositorio;

	@Override
	public Incidencia crear(String idBici, String descripcionIncidencia) {
		Incidencia i=new Incidencia(idBici, LocalDateTime.now(), descripcionIncidencia);
		try {
			repositorio.add(i);
		} catch (RepositorioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public void gestionDeLasIncidencias(String cierre, String operario, String incidencia) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Incidencia recuperarIncidencia() {
		// TODO Auto-generated method stub
		return null;
	}

}
