package web.incidencias;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import dominio.dto.IncidenciaDTO;
import servicio.FactoriaServicios;
import servicio.IServicioIncidencias;


@Named
@ViewScoped
public class GestionaIncidenciaWebResultado implements Serializable {
	private String id;
	private IncidenciaDTO incidencia;
	
	private IServicioIncidencias servicioIncidencias;
	
	public GestionaIncidenciaWebResultado() {
		servicioIncidencias = FactoriaServicios.getServicio(IServicioIncidencias.class);
	}
	
	@Inject
	protected FacesContext facesContext;
	
	public void load() {
		incidencia = servicioIncidencias.getIncidenciaDTO(id);
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Incidencia obtenida con exito"));
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

	public void setIncidencia(IncidenciaDTO incidencia) {
		this.incidencia = incidencia;
	}
		
}
