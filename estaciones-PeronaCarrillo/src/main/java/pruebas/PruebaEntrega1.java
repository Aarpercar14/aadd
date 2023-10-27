package pruebas;

import java.time.LocalDate;

import dominio.Administrador;
import dominio.Estacionamiento;

public class PruebaEntrega1 {

	public static void main(String[] args) {
		Administrador admin=new Administrador("admin", "jefe", "admin@gmail.com", "password", "612345678", LocalDate.now());
//		Estacionamiento estacionameinto=new Estacionamiento("Murcia Centro", 10, "30020", "38", "-1");
		String idEstacion = admin.DarDeAltaEstacion("Murcia Centro", 10, "30020", "38", "-1");
		System.out.println("El administrador ha creado la estacion con id: " + idEstacion);
		
	}

}
