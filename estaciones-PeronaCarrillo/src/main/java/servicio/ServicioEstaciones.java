package servicio;

import repositorio.EntidadNoEncontrada;
import repositorio.FactoriaRepositorios;
import repositorio.Repositorio;
import repositorio.RepositorioException;
import dominio.Bicicleta;
import dominio.Estacionamiento;

public class ServicioEstaciones implements IServicioEstaciones {
	
	private Repositorio<Estacionamiento, String> repositorioEstacion = FactoriaRepositorios.getRepositorio(Estacionamiento.class);
	
	@Override
	public String crear(String nombre, int numPuestos, String postal, double cordX, double cordY) {
		if(nombre == null || nombre.isEmpty())
			throw new IllegalArgumentException("nombre: no debe ser nulo ni vacio");
		if(numPuestos < 5)
			throw new IllegalArgumentException("numero de puestos: una estacion tiene 5 puestos como mínimo");
		if(postal == null || postal.isEmpty())
			throw new IllegalArgumentException("posta: no debe ser nulo ni vacio");
		
		
		Estacionamiento estacion = new Estacionamiento(nombre,numPuestos, postal, cordX, cordY);
		
		try {
			String id = repositorioEstacion.add(estacion);
			return id;
		} catch(RepositorioException e) {
			e.printStackTrace();
		}
		return "";
		
	}
	
	@Override
	public Estacionamiento getEstacion(String id) throws RepositorioException, EntidadNoEncontrada {
		
		if(id == null || id.isEmpty())
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
	
}
