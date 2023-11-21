package dominio;

import java.util.HashMap;

public class Historico {
	
	private HashMap<String, EntradaHistorico> historico;
	
	public Historico() {
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
}
