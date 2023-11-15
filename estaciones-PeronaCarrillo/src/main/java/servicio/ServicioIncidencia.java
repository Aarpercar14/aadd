package servicio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import dominio.Bicicleta;
import dominio.EstadoIncidencia;
import dominio.Incidencia;
import repositorio.EntidadNoEncontrada;
import repositorio.Repositorio;
import repositorio.RepositorioException;

public class ServicioIncidencia implements IServicioIncidencias{
	private Repositorio<Incidencia,String> repositorio;

	@Override
	public Incidencia crear(Bicicleta bici, String descripcionIncidencia) {
		
		Incidencia i=new Incidencia(bici, descripcionIncidencia);
		try {
			repositorio.add(i);
		} catch (RepositorioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public void gestionDeLasIncidencias(String cierre, String operario, String incidencia,String nuevoEstado) {
		try {
			Incidencia i=repositorio.getById(incidencia);
			switch(nuevoEstado){
				case "cancelada":
					i.setEstado(EstadoIncidencia.CANCELADA);
					i.setFechaCierre(LocalDateTime.now());
					i.setMotivoCierre(cierre);
					Bicicleta bici=i.getBicicleta();
					bici.cambioEstadoBici("disponible");
					break;
				case "asignada":
					i.setEstado(EstadoIncidencia.ASIGNADA);
					i.setOperario(operario);
					//TODOQuitar bici de la estacion
					break;
				case "resuelta":
					i.setMotivoCierre(cierre);
					i.setEstado(EstadoIncidencia.RESUELTA);
					i.setFechaCierre(LocalDateTime.now());
					//TODO hacer un if bici esta rota o si regresa a estacion
					int random_int = (int)Math.floor(Math.random() * 10);
					if(random_int>5) {
						
					}else {
						 
					}
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
