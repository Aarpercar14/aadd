package dominio;

import java.util.HashMap;
import java.util.UUID;

import repositorio.Identificable;

public class Historico implements Identificable {
	
	private String id;
	private String idBici;
	private HashMap<String, EntradaHistorico> historico;
	private String idUltimoEstacion;
	
	public Historico(String idBici) {
		this.idBici = idBici;
		this.historico = new HashMap<String, EntradaHistorico>();
		this.idUltimoEstacion="";
	}
	
	public void añadirEntrada(EntradaHistorico entrada) {
		this.historico.put(entrada.getIdEstacion(), entrada);
		this.idUltimoEstacion=entrada.getIdEstacion();
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
	public EntradaHistorico getEntradaHistorico() {
		return historico.get(idUltimoEstacion);
	}
	
	public String getIdBici() {
		return this.idBici;
	}
}