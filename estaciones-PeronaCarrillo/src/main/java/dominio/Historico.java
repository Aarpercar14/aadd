package dominio;

import java.time.LocalDateTime;
import java.util.HashMap;

import repositorio.Identificable;

public class Historico implements Identificable {
	
	private String id;
	private String idBici;
	private HashMap<String, EntradaHistorico> historico;
	private String idUltimaEstacion;
	
	public Historico(String idBici) {
		this.idBici = idBici;
		this.historico = new HashMap<String, EntradaHistorico>();
		this.idUltimaEstacion="";
	}
	
	public void añadirEntrada(EntradaHistorico entrada) {
		this.historico.put(entrada.getIdEstacion(), entrada);
		this.idUltimaEstacion=entrada.getIdEstacion();
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
	
	public void salidaUltimaEstacion() {
		EntradaHistorico entrada = historico.get(idUltimaEstacion);
		entrada.setFechaRetiro(LocalDateTime.now());
		historico.replace(idUltimaEstacion, entrada);
	}
	
	public String getIdBici() {
		return this.idBici;
	}
	
	public String getUltimaEstacion() {
		return this.idUltimaEstacion;
	}
}