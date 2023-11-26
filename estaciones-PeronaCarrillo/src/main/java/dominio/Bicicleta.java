package dominio;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import repositorio.Identificable;
@Entity
@Table(name = "bicicleta")
public class Bicicleta implements Identificable {
	@Id
	private String id;
	@Column(name = "modelo")
	private String modelo;
	@Column(name = "fechaAlta", columnDefinition = "DATE")
	private LocalDateTime fechaAlta;
	@Column(name = "fechaBaja", columnDefinition = "DATE")
	private LocalDateTime fechaBaja;
	@Lob
	@Column(name = "motivoBaja")
	private String motivoBaja;
	@Column(name = "estado")
	private String estado;
	@Column(name = "idHistorico")
	private String idHistorico;

	public Bicicleta(String id, String modelo, String idHistorico) {
		super();
		this.id = id;
		this.modelo = modelo;
		this.fechaAlta = LocalDateTime.now();
		this.idHistorico = idHistorico;
		this.fechaBaja = null;
		this.motivoBaja = null;
		this.estado="Disponible";
	}

	public Bicicleta() {
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
		this.id = id;
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

	public String getIdHistorico() {
		return idHistorico;
	}

	public void setIdHistorico(String idHistorico) {
		this.idHistorico = idHistorico;
	}

	public String toString() {
		return "Bicicleta{" + "id='" + id + '\'' + ", modelo='" + modelo + '\'' + ", fechaAlta=" + fechaAlta
				+ ", fechaBaja=" + fechaBaja + ", motivoBaja='" + motivoBaja + '\'' + ", estado='" + estado + '\''
				+ ", idHistorico='" + idHistorico + '\'' + '}';
	}
}