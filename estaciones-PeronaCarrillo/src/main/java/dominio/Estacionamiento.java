package dominio;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class Estacionamiento implements Identificable{
	private String nombre;
	private int numPuestos;
	private int postal;
	private double cordX;
	private double cordY;
	private LocalDateTime fechaAlta;
	private final String id;
	
	public Estacionamiento(String nom, int num, int post, double x, double y) {
		this.nombre=nom;
		this.numPuestos=num;
		this.cordX=x;
		this.cordY=y;
		this.fechaAlta=LocalDateTime.now();
		this.id=UUID.randomUUID().toString();
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
	}

}
