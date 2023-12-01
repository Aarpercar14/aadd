package servicio;

import repositorio.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import dominio.Bicicleta;
import dominio.Estacionamiento;
import dominio.Historico;

public class ServicioEstaciones implements IServicioEstaciones {

	private Repositorio<Estacionamiento, String> repositorioEstacion = FactoriaRepositorios.getRepositorio(Estacionamiento.class);
	private Repositorio<Bicicleta, String> repositorioBicicletas = FactoriaRepositorios.getRepositorio(Bicicleta.class);
	private Repositorio<Historico, String> repositorioHistorico = FactoriaRepositorios.getRepositorio(Historico.class);

	@Override
	public String crear(String nombre, int numPuestos, String postal, double cordX, double cordY) {
		if (nombre == null || nombre.isEmpty())
			throw new IllegalArgumentException("nombre: no debe ser nulo ni vacio");
		if (numPuestos < 5)
			throw new IllegalArgumentException("numero de puestos: una estacion tiene 5 puestos como mínimo");
		if (postal == null || postal.isEmpty())
			throw new IllegalArgumentException("posta: no debe ser nulo ni vacio");
		Estacionamiento estacion = new Estacionamiento(nombre, numPuestos, postal, cordX, cordY);
		try {
			String id = repositorioEstacion.add(estacion);
			return id;
		} catch (RepositorioException e) {
			e.printStackTrace();
		}
		return "";
	}

	@Override
	public Estacionamiento getEstacion(String id) throws RepositorioException, EntidadNoEncontrada {
		if (id == null || id.isEmpty())
			throw new IllegalArgumentException("id: no debe ser nulo ni vacío");
		return repositorioEstacion.getById(id);
	}

	@Override
	public void eliminar(String id) throws RepositorioException, EntidadNoEncontrada {
		if (id == null || id.isEmpty())
			throw new IllegalArgumentException("id: no debe ser nulo ni vacio");
		Estacionamiento estacion = repositorioEstacion.getById(id);
		repositorioEstacion.delete(estacion);
	}

	@Override
	public String altaDeUnaBici(String modelo, Estacionamiento estacion) {
		String id = UUID.randomUUID().toString();
		try {
			
			Bicicleta bici = new Bicicleta(id, modelo);
			Historico historico = new Historico(bici.getId(),estacion.getId());
			repositorioHistorico.add(historico);
			repositorioBicicletas.add(bici);
			this.estacionarUnaBicileta(id, estacion.getId());
		} catch (RepositorioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public void estacionarUnaBicileta(String idBici) {
		try {
			String idEstacion = RepositorioMemoriaEstacion.getEstacionLibre(repositorioEstacion);
			Bicicleta bici = repositorioBicicletas.getById(idBici);
			Estacionamiento estacion = repositorioEstacion.getById(idEstacion);
			estacion.estacionarBici(bici);
			Historico historico = RepositorioMongoDBHistorico.getHistoricoNoRetirado(repositorioHistorico, idBici);
			RepositorioMongoDBHistorico.añadirEntrada(repositorioHistorico,new Historico(idBici, idEstacion));
			repositorioHistorico.update(historico);
			repositorioEstacion.update(estacion);
		} catch (RepositorioException | EntidadNoEncontrada e) {
			e.printStackTrace();
		}

	}

	@Override
	public void estacionarUnaBicileta(String idBici, String idEstacion) {
		try {
			Bicicleta bici = repositorioBicicletas.getById(idBici);
			Estacionamiento estacion = repositorioEstacion.getById(idEstacion);
			estacion.estacionarBici(bici);
			repositorioHistorico.add(new Historico(idBici, idEstacion));
			repositorioEstacion.update(estacion);
;		} catch (RepositorioException | EntidadNoEncontrada e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void retirarUnaBicleta(String idBici) {
		try {
			Historico his=repositorioHistorico.getById("656a2bf572664c72dac3a310");
			System.out.println(his);
			Estacionamiento estacion = repositorioEstacion.getById(his.getIdEstacion());
			estacion.sacarBici(idBici);
			repositorioEstacion.update(estacion);
			repositorioHistorico.update(his);
		} catch (RepositorioException | EntidadNoEncontrada e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void darDeBajaUnaBici(String idBici, String motivo) {
		try {
			Bicicleta bici = repositorioBicicletas.getById(idBici);
			this.retirarUnaBicleta(idBici);
			bici.cambioEstadoBici("no disponible");
			bici.setFechaBaja(LocalDateTime.now());
			Historico historico = RepositorioMongoDBHistorico.getHistoricoNoRetirado(repositorioHistorico, idBici);
			repositorioHistorico.delete(historico);
		} catch (RepositorioException | EntidadNoEncontrada e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public List<Bicicleta> recuperarBiciEstacionadaPosicion(double x, double y) {
		ArrayList<Bicicleta> bicis=new ArrayList<>();
		for(Estacionamiento e:RepositorioMongoDBEstaciones.getEstacionesByDistancia(repositorioEstacion,x,y)) {
			bicis.addAll(e.getBicicletas());
		}
		return bicis;
	}

	@Override
	public List<Estacionamiento> recuperarEstacionSitiosTuristicosDeMayorAMenor() {
		LinkedList<Estacionamiento> estacion=new LinkedList<Estacionamiento>();
		try {
			int count=0;
			for(Estacionamiento e: repositorioEstacion.getAll()) {
				if(e.getSitiosTuristicos().size()>count) {
					estacion.addFirst(e);
				} else {
					estacion.addLast(e);
				}
			}
			return estacion;
		} catch (RepositorioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	@Override
	public String encontrarEstacionLibre() throws RepositorioException {
		String idEstacion = RepositorioMemoriaEstacion.getEstacionLibre(repositorioEstacion);
		return idEstacion;
	}
	
	@Override
	public Bicicleta obtenerBici(String idBici) throws RepositorioException, EntidadNoEncontrada {
		return repositorioBicicletas.getById(idBici);
	}

}
