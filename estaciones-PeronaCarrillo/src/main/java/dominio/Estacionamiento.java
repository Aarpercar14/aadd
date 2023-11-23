package dominio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import repositorio.FactoriaRepositorios;
import repositorio.Identificable;
import repositorio.Repositorio;
import repositorio.RepositorioException;

public class Estacionamiento implements Identificable{
	private String nombre;
	private int numPuestos;
	private String postal;
	private double[] cord;
	private LocalDateTime fechaAlta;
	private String id;
	
	private ArrayList<Bicicleta> bicicletas;
	
	private Repositorio<Bicicleta,String> historico=FactoriaRepositorios.getRepositorio(Bicicleta.class);
	
	private List<ResumenSitioTuristico> sitiosTuristicos;
	
	public Estacionamiento(String nombre, int numPuesto, String postal, double x, double y) {
		this.id=UUID.randomUUID().toString();
		this.nombre=nombre;
		this.numPuestos=numPuesto;
		this.cord[0]=x;
		this.cord[1]=y;
		this.fechaAlta=LocalDateTime.now();
		this.sitiosTuristicos = new LinkedList<>();
		this.bicicletas=new ArrayList<>();
	}
	
	public void estacionarBici(Bicicleta bici) throws RepositorioException {
		bicicletas.add(bici);
		historico.add(bici);
		numPuestos--;
	}
	
	public void sacarBici(String idBici) {
		for(Bicicleta b:bicicletas) {
			if(b.getId()==idBici) {
				bicicletas.remove(b);
				break;
			}
		}
		numPuestos++;
	}
	
	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNumPuestos() {
		return numPuestos;
	}

	public void setNumPuestos(int numPuestos) {
		this.numPuestos = numPuestos;
	}

	public String getPostal() {
		return postal;
	}

	public void setPostal(String postal) {
		this.postal = postal;
	}

	public double getCordX() {
		return cord[0];
	}

	public void setCordX(double cordX) {
		this.cord[0] = cordX;
	}

	public double getCordY() {
		return cord[1];
	}

	public void setCordY(double cordY) {
		this.cord[1] = cordY;
	}

	public LocalDateTime getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(LocalDateTime fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	
	public List<ResumenSitioTuristico> getSitiosTuristicos(){
		return new LinkedList<>(this.sitiosTuristicos);
	}
	
	public void setSitiosTuristicos(List<ResumenSitioTuristico> sitiosTuristicos) {
		this.sitiosTuristicos.addAll(sitiosTuristicos);
	}

	public ArrayList<Bicicleta> getBicicletas() {
		return bicicletas;
	}

	public Repositorio<Bicicleta, String> getHistorico() {
		return historico;
	}
	
	public boolean haySitioLibre() {
		return (this.numPuestos > 0);
	}
}