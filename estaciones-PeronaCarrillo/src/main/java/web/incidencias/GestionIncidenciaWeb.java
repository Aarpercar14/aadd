package web.incidencias;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class GestionIncidenciaWeb implements Serializable {
	
	private String idIncidencia;
		
	public GestionIncidenciaWeb() {}
	
	@Inject
	protected FacesContext facesContext;
	
	public void gestionaIncidencia() {
		try {
			facesContext.getExternalContext().redirect("gestionIncidencia.xhtml? id="+ idIncidencia);
		} catch (IOException e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", e.getMessage()));
			e.printStackTrace();
		}
	}
	
	public void volver() {
		try {
			facesContext.getExternalContext().redirect("mainGestor.xhtml");
		} catch (IOException e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", e.getMessage()));
			e.printStackTrace();
		}
	}
	
	
	
	public String getIdIncidenacia() {
		return idIncidencia;
	}
	
	public void setIdIcidencia(String id) {
		this.idIncidencia = id;
	}

}
