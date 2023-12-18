package web.inicio;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class InicioSesionWeb implements Serializable{
	
	protected FacesContext facesContext;

	public void inicioCliente() {
		try {
			facesContext.getExternalContext().redirect("mainCliente.xhtml");
		} catch (IOException e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No se ha podido navegar"));
			e.printStackTrace();
		}
	}
	
	public void inicioGestor() {
		try {
			facesContext.getExternalContext().redirect("mainGestor.xhtml");
		} catch (IOException e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No se ha podido navegar"));
			e.printStackTrace();
		}
	}
	
}
