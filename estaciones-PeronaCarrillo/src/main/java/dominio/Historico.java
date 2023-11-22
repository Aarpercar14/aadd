package dominio;

import java.util.HashMap;
import java.util.UUID;

import repositorio.Identificable;

public class Historico implements Identificable {
	
	private String id;
	private HashMap<String, EntradaHistorico> historico;
	
	public Historico() {
		this.id = UUID.randomUUID().toString();
		this.historico = new HashMap<String, EntradaHistorico>();
	}
	
	public void añadirEntrada(EntradaHistorico entrada) {
		this.historico.put(entrada.getIdEstacion(), entrada);
	}
	
	public void eliminarEntrada(String idEstacion) {
		this.historico.remove(idEstacion);
	}
	
	public HashMap<String, EntradaHistorico> getHistorico(){
		return new HashMap<>(this.historico);
	}
	
	@Override
	public String getId() {
		return this.id;
	}
	
	@Override
	public void setId(String id) {
		this.id = id;
	}
}
