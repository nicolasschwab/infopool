package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class MarcaAuto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue
	private int id;
	
	@Column(nullable=false)
	private String nombre;
	
	@OneToMany(mappedBy="marcaAuto",fetch=FetchType.EAGER)
	private Collection<ModeloAuto> modelosAutos = new ArrayList<ModeloAuto>();
	
	public MarcaAuto(){
		super();
	}
	
	public MarcaAuto(String nombre){
		super();
		this.nombre = nombre;		
	}
	
	public int getId(){
		return id;
	}	
	public void setId(int id){
		this.id = id;
	}
	public String getNombre(){
		return nombre;
	}	
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
	public Collection<ModeloAuto> getModelosAutos(){
		return modelosAutos;
	}
	public void setModelosAutos(Collection<ModeloAuto> modelosAutos){
		this.modelosAutos = modelosAutos;
	}
}