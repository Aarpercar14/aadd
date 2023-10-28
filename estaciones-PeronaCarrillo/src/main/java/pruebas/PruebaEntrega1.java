package pruebas;

import java.time.LocalDate;
import java.util.LinkedList;

import dominio.Administrador;
import dominio.Estacionamiento;
import dominio.ResumenSitioTuristico;
import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;
import servicio.ServicioEstaciones;

public class PruebaEntrega1 {

	public static void main(String[] args) {
		Administrador admin=new Administrador("admin", "jefe", "admin@gmail.com", "password", "612345678", LocalDate.now());
		String idEstacion = admin.DarDeAltaEstacion("Murcia Centro", 10, "30020", "38", "-1");
		System.out.println("El administrador ha creado la estacion con id: " + idEstacion);
		try {
			admin.EstablecerSitioTuristico(idEstacion,(LinkedList<ResumenSitioTuristico>) admin.getSitiosTuristicos(idEstacion));
		} catch (RepositorioException | EntidadNoEncontrada e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
