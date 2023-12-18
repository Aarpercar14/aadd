package web.incidencias;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import dominio.Bicicleta;
import dominio.Incidencia;
import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;
import servicio.FactoriaServicios;
import servicio.IServicioEstaciones;
import servicio.IServicioIncidencias;

public class CrearInicidenciaWeb implements Serializable{
	
	private String idBici;
	private String descripcionIncidencia;
	
	private IServicioIncidencias servicioInicidencias;
	private IServicioEstaciones servicioEstaciones;
	
	public CrearInicidenciaWeb() {
		servicioInicidencias = FactoriaServicios.getServicio(IServicioIncidencias.class);
		servicioEstaciones = FactoriaServicios.getServicio(IServicioEstaciones.class);
	}
	
	@Inject
	protected FacesContext facesContext;
	
	public void altaIncidencia() {
		try {
			Bicicleta bici = servicioEstaciones.obtenerBici(idBici);
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Bicicleta "+bici.getId()+" obtenida correctamente"));
			
			Incidencia resultado = servicioInicidencias.crear(bici, descripcionIncidencia);
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Inicidencia "+resultado.getId()+" creado con existo"));
			
			try {
				facesContext.getExternalContext().redirect("mainCliente.xhtml");
			} catch (IOException e) {
				facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No se ha podido navegar"));
				e.printStackTrace();
			}
		} catch (RepositorioException e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", e.getMessage()));
			e.printStackTrace();
		} catch (EntidadNoEncontrada e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", e.getMessage()));
			e.printStackTrace();
		}
		
	}

}
