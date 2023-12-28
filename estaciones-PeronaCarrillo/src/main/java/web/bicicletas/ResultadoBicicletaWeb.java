package web.bicicletas;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import dominio.dto.BicicletaDTO;
import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;
import servicio.FactoriaServicios;
import servicio.IServicioEstaciones;

@Named
@ViewScoped
public class ResultadoBicicletaWeb implements Serializable{
	
	private double x;
	private double y;
	private IServicioEstaciones servicioEstaciones;
	private List<BicicletaDTO> bicis;
	
	@Inject
	protected FacesContext facesContext;
	
	public ResultadoBicicletaWeb() {
		servicioEstaciones = FactoriaServicios.getServicio(IServicioEstaciones.class);
	}
	
	public void load() {
		try {
			System.out.println(x);
			bicis = servicioEstaciones.obtenerBicisDTO(servicioEstaciones.recuperarBiciEstacionadaPosicion(x, y));
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Bicis encontradas con exito"));
		} catch (RepositorioException e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", e.getMessage()));
			e.printStackTrace();
		} catch (EntidadNoEncontrada e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", e.getLocalizedMessage()));
			e.printStackTrace();
		}
	}
	
	public double getX() {
		return x;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public List<BicicletaDTO> getBicis() {
		return bicis;
	}
}
