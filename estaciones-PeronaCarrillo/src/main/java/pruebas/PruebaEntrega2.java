package pruebas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dominio.Administrador;
import dominio.Bicicleta;
import dominio.Estacionamiento;
import dominio.Incidencia;

import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;

public class PruebaEntrega2 {

	public static void main(String[] args) {
		
		Administrador admin = new Administrador("admin", "jefe", "admin@gmail.com", "password", "612345678", LocalDate.now());
				
		// Creacion de las tres estaciones de ejemplo
		String idEstacion1 = admin.DarDeAltaEstacion("Saint Louis", 10, "30020", 40,-5);
		String idEstacion2 = admin.DarDeAltaEstacion("Saint John", 10, "30800", 22, -8);
		String idEstacion3 = admin.DarDeAltaEstacion("Groove Street", 10, "30750", 70, 10);
		List<Estacionamiento> estaciones = new ArrayList<Estacionamiento>();
		try {
			estaciones.add(admin.servEstaciones.getEstacion(idEstacion1));
			estaciones.add(admin.servEstaciones.getEstacion(idEstacion2));
			estaciones.add(admin.servEstaciones.getEstacion(idEstacion3));
			for(Estacionamiento e : estaciones)
				System.out.println(e.toString());
		} catch (RepositorioException | EntidadNoEncontrada e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			// Dar de alta Bicis de Ejemplo
			
			String idBmx = admin.servEstaciones.altaDeUnaBici("BMX", admin.servEstaciones.getEstacion(idEstacion1));
			System.out.println("Las bicicletas que hay estacionadas en la estacion de Saint Louis tras dar de alta la bmx");
			System.out.println(admin.servEstaciones.getEstacion(idEstacion1).getBicicletas().toString());
			
			String idMontaña = admin.servEstaciones.altaDeUnaBici("MONTAÑA", admin.servEstaciones.getEstacion(idEstacion1));
			System.out.println("Las bicicletas que hay estacionadas en la estacion de Saint Louis tras dar de alta la bici de Montaña");
			System.out.println(admin.servEstaciones.getEstacion(idEstacion1).getBicicletas().toString());
			
			String idCarretera = admin.servEstaciones.altaDeUnaBici("CARRETERA", admin.servEstaciones.getEstacion(idEstacion2));
			System.out.println("Las bicicletas que hay estacionadas en la estacion de Saint John tras dar de alta la bici de carretera");
			System.out.println(admin.servEstaciones.getEstacion(idEstacion2).getBicicletas().toString());
			
			String idTandem = admin.servEstaciones.altaDeUnaBici("TANDEM", admin.servEstaciones.getEstacion(idEstacion2));
			System.out.println("Las bicicletas que hay estacionadas en la estacion de Saint John tras dar de alta el tandem");
			System.out.println(admin.servEstaciones.getEstacion(idEstacion2).getBicicletas().toString());
			
			// Retiro varias biciletas y las muevo de estacion
			// Bmx sale de estacion 1 y llega a estacion 2
			admin.servEstaciones.retirarUnaBicleta(idBmx);
			System.out.println("La bmx ya no esta estacionada en la estacion de Saint Louis");
			System.out.println(admin.servEstaciones.getEstacion(idEstacion1).getBicicletas().toString());
			
			admin.servEstaciones.estacionarUnaBicileta(idBmx, idEstacion2);
			System.out.println("Ahora la bmx esta estacionada en la estacion de Saint John");
			System.out.println(admin.servEstaciones.getEstacion(idEstacion2).getBicicletas().toString());
			
			// Bici de Carretera sale de la estacion 2 y llega a estacion 1
			admin.servEstaciones.retirarUnaBicleta(idCarretera);
			System.out.println("La bici de carretera ya no esta estacionada en la estacion de Saint John");
			System.out.println(admin.servEstaciones.getEstacion(idEstacion2).getBicicletas().toString());
			
			admin.servEstaciones.estacionarUnaBicileta(idCarretera, idEstacion1);
			System.out.println("Ahora la bici de carretera esta estacionada en la estacion de Saint Louis");
			System.out.println(admin.servEstaciones.getEstacion(idEstacion1).getBicicletas().toString());
			
			// Bici de Montaña sale de la estacion 1 a la estacion 3
			admin.servEstaciones.retirarUnaBicleta(idMontaña);
			System.out.println("La bici de montaña ya no esta estacionada en la estacion de Saint Louis");
			System.out.println(admin.servEstaciones.getEstacion(idEstacion1).getBicicletas().toString());
			
			admin.servEstaciones.estacionarUnaBicileta(idMontaña, idEstacion3);
			System.out.println("Ahora la bici de carretera esta estacionada en la estacion de Groove Street");
			System.out.println(admin.servEstaciones.getEstacion(idEstacion3).getBicicletas().toString());
			
			// Tandem sale de la estacion 2 y llega a la estacion 1
			admin.servEstaciones.retirarUnaBicleta(idTandem);
			System.out.println("El tandem ya no esta estacionado en la estacion de Saint John");
			System.out.println(admin.servEstaciones.getEstacion(idEstacion2).getBicicletas().toString());
			
			admin.servEstaciones.estacionarUnaBicileta(idTandem, idEstacion1);
			System.out.println("Ahora el tandem esta estacionado en la estacion de Saint Louis");
			System.out.println(admin.servEstaciones.getEstacion(idEstacion1).getBicicletas().toString());
			
			
			// Simulacion de una incidencia
			Bicicleta tandem = admin.servEstaciones.getEstacion(idEstacion1).getBicicleta(idTandem);
			Bicicleta bmx = admin.servEstaciones.getEstacion(idEstacion2).getBicicleta(idBmx);
			Incidencia incidencia1, inicidencia2;
			if (tandem != null && bmx != null) {
				incidencia1 = admin.servIncidencias.crear(tandem, "Tandem con cadena rota");
				inicidencia2 = admin.servIncidencias.crear(bmx, "Bmx no se puede usar");
				
				List<Incidencia> incidenciasAbiertas = admin.servIncidencias.recuperarIncidencias();
				for(Incidencia i : incidenciasAbiertas)
					System.out.println(i.toString());
				
				admin.servIncidencias.gestionDeLasIncidencias("", "Pedro Benitez", incidencia1.getId(), "asignada");
				//TODO combrobar que la bici ya no se encuentra en la estacion
				System.out.println("Podemos apreciar que el tandem ya no se encuentra en la estacion de Saint Louis");
				System.out.println(admin.servEstaciones.getEstacion(idEstacion1).getBicicletas().toString());
				
				admin.servIncidencias.gestionDeLasIncidencias("rota", "Pedro Benitez", incidencia1.getId(), "resuelta");
				//TODO comprobar que la bici ha sido dada de baja
				System.out.println("El motivo de la baja del tandemn ha sido : " + admin.servEstaciones.obtenerBici(idTandem).getMotivoBaja());
				
				admin.servIncidencias.gestionDeLasIncidencias("cancelada", "", inicidencia2.getId(), "cancelada");
				
				
				
			} else
				throw new NullPointerException();
			
			
			
		} catch (RepositorioException | EntidadNoEncontrada e) {
			e.printStackTrace();
		}		
		

	}

}
