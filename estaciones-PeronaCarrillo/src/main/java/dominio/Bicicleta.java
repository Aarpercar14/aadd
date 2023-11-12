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
	private Optional<LocalDateTime> fechaBaja;
	@Lob
	@Column(name="motivobaja")
	private Optional<String> motivoBaja;	
	
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

	public Optional<LocalDateTime> getFechaBaja() {
		return fechaBaja;
	}

	public Optional<String> getMotivoBaja() {
		return motivoBaja;
	}

	public Repositorio<Estacionamiento, String> getHistorico() {
		return historico;
	}
	
	
}
