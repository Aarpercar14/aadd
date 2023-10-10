package dominio;

import java.time.LocalDate;

public class Administrador extends Usuario {
	public Administrador(String nombre, String apellidos, String email, String password, String telefono, LocalDate fechaNacimiento) {
		super(nombre, apellidos, email, password, telefono, fechaNacimiento);
	}
	
	//TODO Implementar método para dar de alta estaciones
	
	
}
