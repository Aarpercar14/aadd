package dominio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.codecs.pojo.annotations.BsonRepresentation;

import repositorio.FactoriaRepositorios;
import repositorio.Identificable;
import repositorio.Repositorio;
import repositorio.RepositorioException;

public class Estacionamiento implements Identificable{
	@BsonId
	@BsonRepresentation(BsonType.OBJECT_ID)
	private String id;
	@BsonProperty(value="nombre")
	private String nombre;
	@BsonProperty(value = "num_puestos")
	private int numPuestos;
	@BsonProperty(value="postal")
	private String postal;
	@BsonProperty(value="cord")
	private double[] cord=new double[2];
	@BsonProperty(value="fecha_alta")
	private LocalDateTime fechaAlta;
	@BsonProperty(value="bicicletas")
	private ArrayList<Bicicleta> bicicletas;
//	@BsonProperty(value="sitiosTurisiticos")
//	private ArrayList<ResumenSitioTuristico> sitiosTuristicos;
	
	public Estacionamiento(String nombre, int numPuesto, String postal, double x, double y) {
		this.nombre=nombre;
		this.numPuestos=numPuesto;
		this.cord= new double[2];
		this.cord[0]=x;
		this.cord[1]=y;
		this.fechaAlta=LocalDateTime.now();
//		this.sitiosTuristicos = new ArrayList<>();
		this.bicicletas=new ArrayList<Bicicleta>();
	}
	
	public Estacionamiento() {
		
	}
	
	public void estacionarBici(Bicicleta bici) throws RepositorioException {
		if(bicicletas==null) {
			bicicletas=new ArrayList<Bicicleta>();
		}
		System.out.println(bicicletas);
		bicicletas.add(bici);
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
	/*
	public ArrayList<ResumenSitioTuristico> getSitiosTuristicos(){
		return new ArrayList<>(this.sitiosTuristicos);
	}
	
	
	public void setSitiosTuristicos(List<ResumenSitioTuristico> sitiosTuristicos) {
		this.sitiosTuristicos.addAll(sitiosTuristicos);
	}
	*/
	public ArrayList<Bicicleta> getBicicletas() {
		return bicicletas;
	}
	
	public boolean haySitioLibre() {
		return (this.numPuestos > 0);
	}
	
	public Bicicleta getBicicleta(String idBici) {
		for(Bicicleta b : bicicletas) {
			if(b.getId().equals(idBici))
				return b;
		}
		return null;			
	}

	@Override
	public String toString() {
		return "Estacionamiento [id=" + id + ", nombre=" + nombre + ", numPuestos=" + numPuestos + ", postal=" + postal
				+ ", cord=" + Arrays.toString(cord) + ", fechaAlta=" + fechaAlta + ", bicicletas=" + bicicletas + "]";
	}
	
	
}