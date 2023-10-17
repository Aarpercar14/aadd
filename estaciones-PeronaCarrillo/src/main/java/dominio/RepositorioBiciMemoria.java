package dominio;

public class RepositorioBiciMemoria extends RepositorioMemoria<Bici> {
	private static RepositorioBiciMemoria unicaInstancia;
	private RepositorioBiciMemoria() {
		super();
	}
	
	public RepositorioBiciMemoria getUnicaInstancia() {
		if(unicaInstancia == null)
			unicaInstancia = new RepositorioBiciMemoria();
		return unicaInstancia;
	}	
}
