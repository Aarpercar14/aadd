package servicio;

import repositorio.EntidadNoEncontrada;
import repositorio.FactoriaRepositorios;
import repositorio.Repositorio;
import repositorio.RepositorioException;
import repositorio.RepositorioJPABicicletas;
import repositorio.RepositorioMongoDBHistorico;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import dominio.Bicicleta;
import dominio.Estacionamiento;
import dominio.Historico;

public class ServicioEstaciones implements IServicioEstaciones {

	private Repositorio<Estacionamiento, String> repositorioEstacion = FactoriaRepositorios
			.getRepositorio(Estacionamiento.class);
	private Repositorio<Bicicleta, String> repositorioBicicletas = FactoriaRepositorios.getRepositorio(Bicicleta.class);

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
	public String altaDeUnaBici(String modelo, Estacionamiento estacion, RepositorioMongoDBHistorico historico) {
		String id = UUID.randomUUID().toString();
		Bicicleta bici = new Bicicleta(id, modelo);
		try {
			repositorioBicicletas.add(bici);
		} catch (RepositorioException e) {
			e.printStackTrace();
		}
		this.estacionarUnaBicileta(id, estacion.getId(), historico);
		return id;
	}

	@Override
	public void estacionarUnaBicileta(String idBici, String idEstacion, RepositorioMongoDBHistorico historico) {
		try {
			if (idEstacion.isEmpty()) {
				for (Estacionamiento e : repositorioEstacion.getAll()) {
					if (e.getNumPuestos() > 0) {
						Bicicleta bici = repositorioBicicletas.getById(idBici);
						bici.estacionar(e);
						e.getBicicletas().add(bici);
						historico.add(new Historico(idBici, e.getId()));
					}
				}
			} else {
				Bicicleta bici = repositorioBicicletas.getById(idBici);
				Estacionamiento e = repositorioEstacion.getById(idEstacion);
				bici.estacionar(e);
				e.getBicicletas().add(bici);
				historico.add(new Historico(idBici, idEstacion));
			}
		} catch (RepositorioException | EntidadNoEncontrada e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void retirarUnaBicleta(String idBici, RepositorioMongoDBHistorico repositorio) {
		try {
			for (Estacionamiento e : repositorioEstacion.getAll()) {
				for (Bicicleta b : e.getBicicletas()) {
					if (b.getId().equals(idBici)) {
						e.sacarBici(b);
						repositorioEstacion.update(e);
						for (Historico h : repositorio.getAll()) {
							if ((h.getIdBici() == b.getId()) && (h.getIdEstacion() == e.getNombre())) {
								h.setFechaRetiro(LocalDateTime.now());
							}
						}
					}
				}
			}
		} catch (RepositorioException | EntidadNoEncontrada e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void darDeBajaUnaBici(String idBici, String motivo) {
		try {
			Bicicleta bici = repositorioBicicletas.getById(idBici);
			bici.cambioEstadoBici("no disponible");
			bici.setFechaBaja(LocalDateTime.now());
			for (Estacionamiento e : repositorioEstacion.getAll()) {
				if (e.getBicicletas().contains(bici)) {
					e.sacarBici(bici);
				}
			}
		} catch (RepositorioException | EntidadNoEncontrada e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public List<Bicicleta> recuperarBiciEstacionadaPosicion(double x, double y) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Estacionamiento> recuperarEstacionSitiosTuristicos() {
		// TODO Auto-generated method stub
		return null;
	}

}
