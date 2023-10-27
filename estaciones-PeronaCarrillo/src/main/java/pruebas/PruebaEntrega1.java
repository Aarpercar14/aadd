package pruebas;

import java.time.LocalDate;

import dominio.Administrador;
import dominio.Estacionamiento;
import servicio.ServicioEstaciones;

public class PruebaEntrega1 {

	public static void main(String[] args) {
		Administrador admin=new Administrador("admin", "jefe", "admin@gmail.com", "password", "612345678", LocalDate.now());
		ServicioEstaciones estacionamientos=new ServicioEstaciones();
		System.out.println(estacionamientos.crear("Murcia Centro", 10, "30020", "38", "-1"));
		
	}

}
