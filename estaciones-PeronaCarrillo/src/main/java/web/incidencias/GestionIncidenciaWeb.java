package web.incidencias;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import dominio.dto.IncidenciaDTO;
import servicio.FactoriaServicios;
import servicio.IServicioIncidencias;

@Named
@ViewScoped
public class GestionIncidenciaWeb implements Serializable {
	
	private String id;
	
	private IServicioIncidencias servicioIncidencia;
	
	private IncidenciaDTO incidencia;
	
	public GestionIncidenciaWeb() {
		servicioIncidencia = FactoriaServicios.getServicio(IServicioIncidencias.class);
	}
	
	@Inject
	protected FacesContext facesContext;
	
	
	public void load() {
		incidencia = servicioIncidencia.getIncidenciaDTO(id);
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Incidencia a gestionar obtenida con exito"));
	}
	
	
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public IncidenciaDTO getIncidencia() {
		return incidencia;
	}

}
