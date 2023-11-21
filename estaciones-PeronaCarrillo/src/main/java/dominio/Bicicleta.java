package dominio;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.eclipse.persistence.jaxb.compiler.facets.FacetVisitor;

import repositorio.FactoriaRepositorios;
import repositorio.Identificable;
import repositorio.Repositorio;
import repositorio.RepositorioException;
import repositorio.RepositorioMongoDBEstaciones;

@Entity
@Table(name="revista")
@NamedQueries({
	@NamedQuery(name="Bicicleta.getByModelo",query="SELECT r FROM Bicicleta r WHERE r.modelo LIKE :keyword")
	
})
public class Bicicleta implements Identificable{
	@Id
	private String id;
	@Column(name="modelo")
	private String modelo;
	@Column(name="fechaAlta",columnDefinition = "DATE")
	private LocalDateTime fechaAlta;
	@Column(name="fechabaja",columnDefinition="DATE")
	private LocalDateTime fechaBaja;
	@Lob
	@Column(name="motivobaja")
	private String motivoBaja;
	@Column(name="estado")
	private String estado;
	
	private Repositorio<Estacionamiento,String> historico=FactoriaRepositorios.getRepositorio(Estacionamiento.class);
	

	public Bicicleta(String id, String modelo) {
		super();
		this.id = id;
		this.modelo = modelo;
		this.fechaAlta = LocalDateTime.now();
		this.fechaBaja = null;
		this.motivoBaja = null;
	}
	
	public void estacionar(Estacionamiento estacion) {
		try {
			historico.add(estacion);
			
		} catch (RepositorioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void cambioEstadoBici(String estado) {
		this.setEstado(estado);
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub	
	}

	public String getModelo() {
		return modelo;
	}

	public LocalDateTime getFechaAlta() {
		return fechaAlta;
	}

	public LocalDateTime getFechaBaja() {
		return fechaBaja;
	}

	public String getMotivoBaja() {
		return motivoBaja;
	}

	public Repositorio<Estacionamiento, String> getHistorico() {
		return historico;
	}

	public String getEstado() {
		return estado;
	}

	private void setEstado(String estado) {
		this.estado = estado;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public void setFechaAlta(LocalDateTime fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public void setFechaBaja(LocalDateTime localDateTime) {
		this.fechaBaja = localDateTime;
	}

	public void setMotivoBaja(String motivoBaja) {
		this.motivoBaja = motivoBaja;
	}

	public void setHistorico(Repositorio<Estacionamiento, String> historico) {
		this.historico = historico;
	}
	
	
}
