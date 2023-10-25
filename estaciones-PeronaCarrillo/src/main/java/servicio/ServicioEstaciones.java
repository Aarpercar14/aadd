package servicio;

import repositorio.EntidadNoEncontrada;
import repositorio.FactoriaRepositorios;
import repositorio.Repositorio;
import repositorio.RepositorioException;

import dominio.Estacionamiento;

public class ServicioEstaciones implements IServicioEstaciones {
	
	private Repositorio<Estacionamiento, String> repositorio = FactoriaRepositorios.getRepositorio(Estacionamiento.class);
	
	@Override
	public String crear(String nombre, int numPuestos, String postal, String cordX, String cordY) {
		if(nombre == null || nombre.isEmpty())
			throw new IllegalArgumentException("nombre: no debe ser nulo ni vacio");
		if(numPuestos < 5)
			throw new IllegalArgumentException("numero de puestos: una estacion tiene 5 puestos como mínimo");
		if(postal == null || postal.isEmpty())
			throw new IllegalArgumentException("posta: no debe ser nulo ni vacio");
		if(cordX == null || cordX.isEmpty())
			throw new IllegalArgumentException("cordX: no debe ser nula ni vacia");
		if(cordY == null || cordY.isEmpty())
			throw new IllegalArgumentException("cordY: no debe ser nula ni vacia");
		
		
		Estacionamiento estacion = new Estacionamiento(nombre,numPuestos, postal, cordX, cordY);
		
		try {
			String id = repositorio.add(estacion);
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
		
		return repositorio.getById(id);
		
	}
	
	@Override
	public void eliminar(String id) throws RepositorioException, EntidadNoEncontrada {
		if (id == null || id.isEmpty())
			throw new IllegalArgumentException("id: no debe ser nulo ni vacio");
		
		Estacionamiento estacion = repositorio.getById(id);
		repositorio.delete(estacion);
	}
}
