package estaciones.web.estaciones;

import java.io.Serializable;

import javax.faces.*;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import dto.EstacionesDTO;
import repositorio.RepositorioException;
import servicio.FactoriaServicios;
import servicio.IServicioEstaciones;

@Named
@ViewScoped
public class EstacionDetailWeb implements Serializable{  
    private String idEstacion;   
    private IServicioEstaciones servicioEstaciones;  
    private EstacionesDTO revista; 
    @Inject
    protected FacesContext facesContext;
        
    public EstacionDetailWeb() {     
        servicioEstaciones = FactoriaServicios.getServicio(IServicioEstaciones.class);        
    }
    public void load() {
        try {
            revista = servicioEstaciones.getById(idEstacion);
        } catch (RepositorioException e) {
            facesContext.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "", e.getMessage()));
        }
    }
	public String getIdEstacion() {
		return idEstacion;
	}
	public void setIdEstacion(String idEstacion) {
		this.idEstacion = idEstacion;
	}
	public IServicioEstaciones getServicioEstaciones() {
		return servicioEstaciones;
	}
	public void setServicioEstaciones(IServicioEstaciones servicioEstaciones) {
		this.servicioEstaciones = servicioEstaciones;
	}
	public EstacionesDTO getRevista() {
		return revista;
	}
	public void setRevista(EstacionesDTO revista) {
		this.revista = revista;
	}
	public FacesContext getFacesContext() {
		return facesContext;
	}
	public void setFacesContext(FacesContext facesContext) {
		this.facesContext = facesContext;
	}

    
}