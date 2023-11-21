package servicio;

import java.util.List;
import dominio.Bicicleta;
import dominio.Estacionamiento;
import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;
import repositorio.RepositorioMongoDBHistorico;

public interface IServicioEstaciones {
	
	String crear(String nombre, int numPuestos, String postal, double cordX, double cordY);
	
	Estacionamiento getEstacion(String id) throws RepositorioException, EntidadNoEncontrada;
	
	void eliminar(String id) throws RepositorioException, EntidadNoEncontrada;
	
	String altaDeUnaBici(String modelo, Estacionamiento estacion,RepositorioMongoDBHistorico historico);
	
	void estacionarUnaBicileta(String idBici, String idEstacion,RepositorioMongoDBHistorico historico);
	
	void retirarUnaBicleta(String idBici,RepositorioMongoDBHistorico repositorio);
	
	void darDeBajaUnaBici(String idBici, String motivo);
	
	List<Bicicleta> recuperarBiciEstacionadaPosicion(double x, double y);
	
	List<Estacionamiento> recuperarEstacionSitiosTuristicos();
	
}
