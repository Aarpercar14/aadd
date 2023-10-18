package dominio;

import java.util.*;

import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;
import repositorio.RepositorioMemoria;


public class RepositorioMemoriaSitioTuristico extends RepositorioMemoria<SitioTuristico>{
	public RepositorioMemoriaSitioTuristico(){
		super();
	}
	
	public ArrayList<SitioTuristico> getSitiosInteres(int x , int y) throws RepositorioException{
		ArrayList<SitioTuristico> lista=(ArrayList<SitioTuristico>) this.getAll();
		ArrayList<SitioTuristico> resultado=new ArrayList<>();
		for(SitioTuristico s:lista) {
			if(Math.pow(s.getX()-x,2)+Math.pow(s.getY()-y, 2)<1){
				resultado.add(s);
			}
		}
		return resultado;
	}
	
	public SitioTuristico getInfoSitio(String id) throws RepositorioException, EntidadNoEncontrada {
		return this.getById(id);
	}
}
