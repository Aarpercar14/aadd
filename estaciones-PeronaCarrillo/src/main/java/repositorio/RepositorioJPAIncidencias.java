package repositorio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import dominio.Incidencia;
import utils.EntityManagerHelper;

public class RepositorioJPAIncidencias extends RepositorioJPA<Incidencia>{

	@Override
	public Class<Incidencia> getClase() {
		return Incidencia.class;
	}

	@Override
	public String getNombre() {
		return Incidencia.class.getName().substring(Incidencia.class.getName().lastIndexOf(".") + 1);
	}


}
