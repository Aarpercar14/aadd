package servicio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import dominio.EstadoIncidencia;
import dominio.Incidencia;
import repositorio.EntidadNoEncontrada;
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
		try {
			Incidencia i=repositorio.getById(incidencia);
			switch(i.getEstado().toString()){
				case "cancelada":
					i.setFechaCierre(LocalDateTime.now());
					i.setMotivoCierre(cierre);
					i.setEstado(EstadoIncidencia.RESUELTA);
					//TODO cambiar bicicleta a estar disponible
					break;
				case "asignada":
					i.setOperario(operario);
					//TODOQuitar bici de la estacion
					break;
				case "resuelta":
					i.setMotivoCierre(cierre);
					i.setEstado(EstadoIncidencia.RESUELTA);
					//TODO hacer un if bici esta rota o si regresa a estacion
					break;
			};
			
		} catch (RepositorioException | EntidadNoEncontrada e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Incidencia> recuperarIncidencia() {
		try {
			ArrayList<Incidencia> listaIncidencias=new ArrayList<Incidencia>();
			for(Incidencia i:(ArrayList<Incidencia>)repositorio.getAll()) {
				if(!i.getEstado().equals(EstadoIncidencia.RESUELTA)) {
					listaIncidencias.add(i);
				}
			}
			return listaIncidencias;
		} catch (RepositorioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
