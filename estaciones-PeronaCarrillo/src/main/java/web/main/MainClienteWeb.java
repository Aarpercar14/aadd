package web.main;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

public class MainClienteWeb implements Serializable{

	
	@Inject
	protected FacesContext facesContext;
	
	public void crearBicicleta() {
		try {
			facesContext.getExternalContext().redirect("altaBicicleta.xhtml");
		} catch (IOException e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No se ha podido navegar"));
			e.printStackTrace();
		}
	}
	
	public void buscarBicicleta() {
		try {
			facesContext.getExternalContext().redirect("buscadorBicis.xhtml");
		} catch (IOException e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No se ha podido navegar"));
		}
	}
	
	public void crearInicidencia() {
		try {
			facesContext.getExternalContext().redirect("altaIncidencia.xhtml");
		} catch (IOException e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No se ha podido navegar"));
		}
	}
	
	public void load() {
		try {
			facesContext.getExternalContext().redirect("mainCliente.xhtml");
		} catch (IOException e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No se ha podido navegar"));
		}
	}
}
