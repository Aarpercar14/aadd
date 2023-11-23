package repositorio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import dominio.Bicicleta;
import utils.EntityManagerHelper;

public class RepositorioJPABicicletas extends RepositorioJPA<Bicicleta>{

	@Override
	public Class<Bicicleta> getClase() {
		return Bicicleta.class;
	}

	@Override
	public String getNombre() {
		return Bicicleta.class.getName().substring(Bicicleta.class.getName().lastIndexOf(".")+1);
	}
	
	public List<Bicicleta> getByModelo(String keyword) throws RepositorioException{
		EntityManager em = EntityManagerHelper.getEntityManager();
		try {
		Query query = em.createNamedQuery("Bicicleta.getByModelo");
		
		query.setParameter("keyword", "%"+keyword+"%");
		
		query.setHint(QueryHints.REFRESH, HintValues.TRUE);
		
		return query.getResultList();
		
		}catch(RuntimeException ru) {
			throw new RepositorioException("Error buscando bicicletas por palabra clave", ru);
		}
	}

	@Override
	public Object getCollection() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
