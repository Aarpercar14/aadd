package web.bicicletas;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class BuscarBicicletaWeb implements Serializable {
	
	private double x;
	private double y;
	
	@Inject
	protected FacesContext facesContext;
	
	public void buscarBicis() {
		try {
			facesContext.getExternalContext().redirect("resultadoBicis.xhtml? x="+x+"&y="+y);
		} catch (IOException e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No se ha podido navegar"));
			e.printStackTrace();
		}
	}

}
