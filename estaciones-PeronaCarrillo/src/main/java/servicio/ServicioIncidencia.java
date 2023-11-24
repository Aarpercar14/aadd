package servicio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import dominio.Bicicleta;
import dominio.EstadoIncidencia;
import dominio.Incidencia;
import repositorio.EntidadNoEncontrada;
import repositorio.Repositorio;
import repositorio.RepositorioException;

public class ServicioIncidencia implements IServicioIncidencias{
	private Repositorio<Incidencia,String> repositorio;
	private IServicioEstaciones servEstaciones = FactoriaServicios.getServicio(IServicioEstaciones.class);

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
	public void gestionDeLasIncidencias(String cierre, String operario, String incidencia, String nuevoEstado) {
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
					
					Bicicleta biciAsignada = i.getBicicleta();
					servEstaciones.retirarUnaBicleta(biciAsignada.getId());
					
					break;
				case "resuelta":
					i.setEstado(EstadoIncidencia.RESUELTA);
					i.setMotivoCierre(cierre);
					i.setFechaCierre(LocalDateTime.now());
					//TODO hacer un if bici esta rota o si regresa a estacion
					
					if(cierre.equals("rota")) {
						// Caso bici rota, se da de baja la bici
						Bicicleta biciRota = i.getBicicleta();
						servEstaciones.darDeBajaUnaBici(biciRota.getId(), "Bicicleta Rota");
					}
					
					if(cierre.equals("reparada")){
						// Caso bici regresa a estacion
						String idEstacion = servEstaciones.encontrarEstacionLibre();
						if(idEstacion == null)
							throw new NullPointerException();
						servEstaciones.estacionarUnaBicileta(i.getBicicleta().getId(), idEstacion);
					}
					break;
			};
			
		} catch (RepositorioException | EntidadNoEncontrada e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Incidencia> recuperarIncidencias() {
		try {
			ArrayList<Incidencia> listaIncidencias=new ArrayList<Incidencia>();
			for(Incidencia i:(ArrayList<Incidencia>)repositorio.getAll()) {
				if((i.getEstado().equals(EstadoIncidencia.ASIGNADA)) ||(i.getEstado().equals(EstadoIncidencia.PENDIENTE))) {
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
