package estaciones.web.estaciones;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.bson.codecs.pojo.annotations.BsonProperty;

import dominio.Bicicleta;
import dominio.ResumenSitioTuristico;
import repositorio.RepositorioException;
import servicio.FactoriaServicios;
import servicio.IServicioEstaciones;

@Named
@ViewScoped
public class AltaEstacionesWeb implements Serializable {
	private String id;
	private String nombre;
	private int numPuestos;
	private String postal;
	private double cordY;
	private double cordX;
	private LocalDateTime fechaAlta;
	private ArrayList<Bicicleta> bicicletas;
	private ArrayList<ResumenSitioTuristico> sitiosTuristicos;
	private IServicioEstaciones servicioEstaciones;
	
	@Inject
	protected FacesContext facesContext;
	
	 public AltaEstacionesWeb() {       
		 servicioEstaciones = FactoriaServicios.getServicio(IServicioEstaciones.class);
		 
	}
	 
	 public void altaEstacoin() {
		 try {
	        String resultado = servicioEstaciones.crear(nombre, numPuestos, postal,cordX,cordY);    
	        facesContext.addMessage(null,
	                new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Estacion "+resultado+" creada correctamente"));
	    } catch(IllegalArgumentException e) {
	        facesContext.addMessage(null,
	                new FacesMessage(FacesMessage.SEVERITY_WARN, "", e.getMessage()));
	        e.printStackTrace();
	    }
	 }
	    

	public String getId() {
		return id;
	}

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

	public double getCordY() {
		return cordY;
	}

	public void setCordY(double cordY) {
		this.cordY = cordY;
	}

	public double getCordX() {
		return cordX;
	}

	public void setCordX(double cordX) {
		this.cordX = cordX;
	}

	public LocalDateTime getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(LocalDateTime fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public ArrayList<Bicicleta> getBicicletas() {
		return bicicletas;
	}

	public void setBicicletas(ArrayList<Bicicleta> bicicletas) {
		this.bicicletas = bicicletas;
	}

	public ArrayList<ResumenSitioTuristico> getSitiosTuristicos() {
		return sitiosTuristicos;
	}

	public void setSitiosTuristicos(ArrayList<ResumenSitioTuristico> sitiosTuristicos) {
		this.sitiosTuristicos = sitiosTuristicos;
	}

	public IServicioEstaciones getServicioEstaciones() {
		return servicioEstaciones;
	}

	public void setServicioEstaciones(IServicioEstaciones servicioEstaciones) {
		this.servicioEstaciones = servicioEstaciones;
	}

}
