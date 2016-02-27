package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ModeloAuto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue
	private int id;
	
	@Column(nullable=false)
	private String nombre;
	
	@ManyToOne(optional=false)
	private MarcaAuto marcaAuto;
	
	//datos utiles para huella de carbono
	@Enumerated(EnumType.STRING)
	private Combustible combustible;
	
	@Column
	private float eficienciaCombustible;
	
	@Column
	private float emisionGases;	
	
	public ModeloAuto(){
		super();
	}
	
	public ModeloAuto(String nombre, MarcaAuto marcaAuto, Combustible combustible, float eficienciaCombustible, float emisionGases){
		super();
		this.nombre = nombre;
		this.marcaAuto = marcaAuto;
		this.combustible = combustible;
		this.eficienciaCombustible = eficienciaCombustible;
		this.emisionGases = emisionGases;
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
	public MarcaAuto getMarcaAuto(){
		return marcaAuto;
	}
	public void setMarcaAuto(MarcaAuto marcaAuto){
		this.marcaAuto = marcaAuto;
	}	
	public Combustible getCombustible() {
		return combustible;
	}
	public void setCombustible(Combustible combustible) {
		this.combustible = combustible;
	}
	public double getEficienciaCombustible(){
		return eficienciaCombustible;
	}
	public void setEficienciaCombustible(float eficienciaCombustible){
		this.eficienciaCombustible = eficienciaCombustible;
	}
	public double getEmisionGases(){
		return this.emisionGases;
	}
	public void setEmisionGases(float emisionGases){
		this.emisionGases = emisionGases;
	}
}