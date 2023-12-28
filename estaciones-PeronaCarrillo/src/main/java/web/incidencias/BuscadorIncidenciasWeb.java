package web.incidencias;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

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
public class BuscadorIncidenciasWeb implements Serializable{
	
	private IServicioIncidencias servicioIncidencias;
	private List<IncidenciaDTO> incidencias;
	
	public BuscadorIncidenciasWeb() {
		servicioIncidencias = FactoriaServicios.getServicio(IServicioIncidencias.class);
	}
	
	@Inject
	protected FacesContext facesContext;
	
	public void load() {
		incidencias = servicioIncidencias.recuperarIncidenciasDTO();
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Incidencias abiertas encontradas con exito"));
	}
	
	public void gestionarIncidencia(String id) {
		try {
			facesContext.getExternalContext().redirect("gestionIncidencia.xhtml? id="+id);
		} catch (IOException e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No se ha podido navegar"));
			e.printStackTrace();
		}
	}
	
	public List<IncidenciaDTO> getIncidencias() {
		return incidencias;
	}
}
