package dominio;

@SuppressWarnings("serial")
public class SitioTuristicoException extends Exception{
	public SitioTuristicoException(String msg, Throwable causa) {
		super(msg, causa);
	}
	
	
	public SitioTuristicoException(String msg) {
		super(msg);
	}

}
