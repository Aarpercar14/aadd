package web.incidencias;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import dominio.Bicicleta;
import dominio.Incidencia;
import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;
import servicio.FactoriaServicios;
import servicio.IServicioEstaciones;
import servicio.IServicioIncidencias;

@Named
@ViewScoped
public class CrearInicidenciaWeb implements Serializable{
	
	private String idBici;
	private String descripcionIncidencia;
	
	private IServicioIncidencias servicioInicidencias;
	private IServicioEstaciones servicioEstaciones;
	
	@Inject
	protected FacesContext facesContext;
	
	public CrearInicidenciaWeb() {
		servicioInicidencias = FactoriaServicios.getServicio(IServicioIncidencias.class);
		servicioEstaciones = FactoriaServicios.getServicio(IServicioEstaciones.class);
	}
	
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

	public String getIdBici() {
		return idBici;
	}

	public void setIdBici(String idBici) {
		this.idBici = idBici;
	}

	public String getDescripcionIncidencia() {
		return descripcionIncidencia;
	}

	public void setDescripcionIncidencia(String descripcionIncidencia) {
		this.descripcionIncidencia = descripcionIncidencia;
	}

	public IServicioIncidencias getServicioInicidencias() {
		return servicioInicidencias;
	}

	public void setServicioInicidencias(IServicioIncidencias servicioInicidencias) {
		this.servicioInicidencias = servicioInicidencias;
	}

	public IServicioEstaciones getServicioEstaciones() {
		return servicioEstaciones;
	}

	public void setServicioEstaciones(IServicioEstaciones servicioEstaciones) {
		this.servicioEstaciones = servicioEstaciones;
	}

	public FacesContext getFacesContext() {
		return facesContext;
	}

	public void setFacesContext(FacesContext facesContext) {
		this.facesContext = facesContext;
	}
	

}
