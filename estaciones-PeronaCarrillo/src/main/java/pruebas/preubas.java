package pruebas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import dominio.Administrador;
import dominio.Estacionamiento;
import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;

public class preubas {

public static void main(String[] args) {
		
		Administrador admin = new Administrador("admin", "jefe", "admin@gmail.com", "password", "612345678", LocalDate.now());
				
		// Creacion de las tres estaciones de ejemplo
		
		String idEstacion1 = admin.DarDeAltaEstacion("Saint Louis", 10, "30020", 40,-5);
		String idEstacion2 = admin.DarDeAltaEstacion("Saint John", 10, "30800", 22, -8);
		String idEstacion3 = admin.DarDeAltaEstacion("Groove Street", 10, "30750", 70, 10);

		try {
			
			String idBmx = admin.servEstaciones.altaDeUnaBici("BMX", admin.servEstaciones.getEstacion(idEstacion1));
			String idMontaña = admin.servEstaciones.altaDeUnaBici("MONTAÑA", admin.servEstaciones.getEstacion(idEstacion1));
			String idCarretera = admin.servEstaciones.altaDeUnaBici("CARRETERA", admin.servEstaciones.getEstacion(idEstacion2));
			String idTandem = admin.servEstaciones.altaDeUnaBici("TANDEM", admin.servEstaciones.getEstacion(idEstacion2));
			
			System.out.println("Estacion Sanit Louis"+admin.getEstacionamiento(idEstacion1).getBicicletas());
			System.out.println("Estacion Sanit John"+admin.getEstacionamiento(idEstacion2).getBicicletas());
			System.out.println("Estacion Groove Street"+admin.getEstacionamiento(idEstacion3).getBicicletas());
			admin.servEstaciones.retirarUnaBicleta(idCarretera);
			System.out.println("La bici de carretera ya no esta estacionada en la estacion de Saint John");
			System.out.println(admin.servEstaciones.getEstacion(idEstacion2).getBicicletas().toString());
			
			
		} catch (RepositorioException | EntidadNoEncontrada e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
