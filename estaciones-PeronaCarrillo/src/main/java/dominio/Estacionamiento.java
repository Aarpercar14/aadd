package dominio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.codecs.pojo.annotations.BsonRepresentation;

import repositorio.Identificable;
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
	@BsonProperty(value="cordY")
	private double cordY;
	@BsonProperty(value="cordX")
	private double cordX;
	@BsonProperty(value="fecha_alta")
	private LocalDateTime fechaAlta;
	@BsonProperty(value="bicicletas")
	private ArrayList<Bicicleta> bicicletas;
	@BsonProperty(value="sitiosTurisiticos")
	private ArrayList<ResumenSitioTuristico> sitiosTuristicos;
	
	public Estacionamiento(String nombre, int numPuesto, String postal, double x, double y) {
		this.nombre=nombre;
		this.numPuestos=numPuesto;
		this.cordX=x;
		this.cordY=y;
		this.fechaAlta=LocalDateTime.now();
		this.sitiosTuristicos = new ArrayList<>();
		this.bicicletas=new ArrayList<Bicicleta>();
	}
	
	public Estacionamiento() {
		
	}
	
	public void estacionarBici(Bicicleta bici) throws RepositorioException {
		if(bicicletas==null) {
			bicicletas=new ArrayList<Bicicleta>();
		}
		bicicletas.add(bici);
		numPuestos--;
	}
	
	public void sacarBici(String idBici) {
		for(Bicicleta b:bicicletas) {
			if(b.getId().equals(idBici)) {
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

	public LocalDateTime getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(LocalDateTime fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	
	public ArrayList<ResumenSitioTuristico> getSitiosTuristicos(){
		return new ArrayList<>(this.sitiosTuristicos);
	}
	
	public void setSitiosTuristicos(List<ResumenSitioTuristico> sitiosTuristicos) {
		this.sitiosTuristicos = (ArrayList<ResumenSitioTuristico>) sitiosTuristicos;
		this.sitiosTuristicos.addAll(sitiosTuristicos);
	}
	
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

	public double getCordX() {
		return cordX;
	}

	public void setCordX(double cord) {
		this.cordX = cord;
	}
	
	public double getCordY() {
		return cordY;
	}

	public void setCordY(double cord) {
		this.cordY = cord;
	}

	public void setBicicletas(ArrayList<Bicicleta> bicicletas) {
		this.bicicletas = bicicletas;
	}

	public void setSitiosTuristicos(ArrayList<ResumenSitioTuristico> sitiosTuristicos) {
		this.sitiosTuristicos = sitiosTuristicos;
	}

	@Override
	public String toString() {
		return "Estacionamiento [id=" + id + ", nombre=" + nombre + ", numPuestos=" + numPuestos + ", postal=" + postal
				+ ", cordY=" + cordY + ", cordX=" + cordX + ", fechaAlta=" + fechaAlta + ", bicicletas=" + bicicletas
				+ ", sitiosTuristicos=" + sitiosTuristicos + "]";
	}

	
	
	
}