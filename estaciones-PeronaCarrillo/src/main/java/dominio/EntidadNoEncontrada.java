package dominio;

public class EntidadNoEncontrada extends Exception {
	public EntidadNoEncontrada() {
		super("La entidad no ha sido encontrada en el repositorio");
	}
}
