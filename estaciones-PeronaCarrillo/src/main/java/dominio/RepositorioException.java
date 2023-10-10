package dominio;

public class RepositorioException extends Exception {
	public RepositorioException() {
		super("No ha sido posible acceder al repositorio");
	}
}
