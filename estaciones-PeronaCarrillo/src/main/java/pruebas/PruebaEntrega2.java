package pruebas;

import java.util.List;

import dominio.Bicicleta;
import dominio.Incidencia;

import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;
import servicio.FactoriaServicios;
import servicio.IServicioEstaciones;
import servicio.IServicioIncidencias;

public class PruebaEntrega2 {

	public static void main(String[] args) {
		IServicioEstaciones servEstaciones = FactoriaServicios.getServicio(IServicioEstaciones.class);
		IServicioIncidencias servIncidencias = FactoriaServicios.getServicio(IServicioIncidencias.class);
		
		
		// Creacion de las tres estaciones de ejemplo
		String idEstacion1 = servEstaciones.crear("Saint Louis", 10, "30020", 40,-5);
		String idEstacion2 = servEstaciones.crear("Saint John", 10, "30800", 22, -8);
		String idEstacion3 = servEstaciones.crear("Groove Street", 10, "30750", 70, 10);
		
		
		try {
			// TODO sacar por terminal el estado despues de cada movimiento
			// Dar de alta Bicis de Ejemplo
			String idBmx = servEstaciones.altaDeUnaBici("BMX", servEstaciones.getEstacion(idEstacion1));
			String idMontaña = servEstaciones.altaDeUnaBici("MONTAÑA", servEstaciones.getEstacion(idEstacion1));
			String idCarretera = servEstaciones.altaDeUnaBici("CARRETERA", servEstaciones.getEstacion(idEstacion2));
			String idTandem = servEstaciones.altaDeUnaBici("TANDEM", servEstaciones.getEstacion(idEstacion2));
			
			// Retiro varias biciletas y las muevo de estacion
			// Bmx sale de estacion 1 y llega a estacion 2
			servEstaciones.retirarUnaBicleta(idBmx);
			servEstaciones.estacionarUnaBicileta(idBmx, idEstacion2);
			
			// Bici de Carretera sale de la estacion 2 y llega a estacion 1
			servEstaciones.retirarUnaBicleta(idCarretera);
			servEstaciones.estacionarUnaBicileta(idCarretera, idEstacion1);
			
			// Bici de Montaña sale de la estacion 1 a la estacion 3
			servEstaciones.retirarUnaBicleta(idMontaña);
			servEstaciones.estacionarUnaBicileta(idMontaña, idEstacion3);
			
			// Tandem sale de la estacion 2 y llega a la estacion 1
			servEstaciones.retirarUnaBicleta(idTandem);
			servEstaciones.estacionarUnaBicileta(idTandem, idEstacion1);
			
			
			// Simulacion de una incidencia
			Bicicleta tandem = servEstaciones.getEstacion(idEstacion1).getBicicleta(idTandem);
			Bicicleta bmx = servEstaciones.getEstacion(idEstacion2).getBicicleta(idBmx);
			Incidencia incidencia1, inicidencia2;
			if (tandem != null && bmx != null) {
				incidencia1 = servIncidencias.crear(tandem, "Tandem con cadena rota");
				inicidencia2 = servIncidencias.crear(bmx, "Bmx no se puede usar");
				
				List<Incidencia> incidenciasAbiertas = servIncidencias.recuperarIncidencias();
				for(Incidencia i : incidenciasAbiertas)
					System.out.println(i.toString());
				
				servIncidencias.gestionDeLasIncidencias("", "Pedro Benitez", incidencia1.getId(), "asignada");
				//TODO combrobar que la bici ya no se encuentra en la estacion
				
				servIncidencias.gestionDeLasIncidencias("rota", "Pedro Benitez", incidencia1.getId(), "resuelta");
				//TODO comprobar que la bici ha sido dada de baja
				
				servIncidencias.gestionDeLasIncidencias("cancelada", "", inicidencia2.getId(), "cancelada");
				
				
				
			} else
				throw new NullPointerException();
			
			
			
		} catch (RepositorioException | EntidadNoEncontrada e) {
			e.printStackTrace();
		}		
		

	}

}
