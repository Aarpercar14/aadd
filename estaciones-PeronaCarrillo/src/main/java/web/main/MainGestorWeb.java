package web.main;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

public class MainGestorWeb implements Serializable{

	
	@Inject
	protected FacesContext facesContext;
	
	public void verInicidencias() {
		try {
			facesContext.getExternalContext().redirect("buscadorIncidencias.xhtml");
		} catch (IOException e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No se ha podido navegar"));
			e.printStackTrace();
		}
	}
	
	public void load() {
		try {
			facesContext.getExternalContext().redirect("mainGestor.xhtml");
		} catch (IOException e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No se ha podido navegar"));
			e.printStackTrace();
		}
	}
}
