package web.incidencias;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class BuscadorIncidenciasWeb implements Serializable{
	
	@Inject
	protected FacesContext facesContext;
	
	public void gestionarIncidencia() {
		
	}
}
