package dominio;

import java.time.LocalDateTime;
import java.util.Optional;

import repositorio.Identificable;

public class Bicicleta implements Identificable{
	private String id;
	private String modelo;
	private LocalDateTime fechaAlta;
	private Optional<LocalDateTime> fechaBaja;
	private Optional<String> motivoBaja;

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		
	}

}
