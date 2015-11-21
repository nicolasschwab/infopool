package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Auto implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue
	private int id;
	@Enumerated(EnumType.STRING)
	private Marca marca;
	private String modelo;
	@Enumerated(EnumType.STRING)
	private Combustible combustible;
	@Enumerated(EnumType.STRING)
	private TipoVehiculo tipo;
	
	public Auto(){
		
	}
	
	public Auto(Marca marca, String modelo,Combustible combustible, TipoVehiculo tipo) {
		super();
		this.marca = marca;
		this.modelo = modelo;
		this.combustible = combustible;
		this.tipo = tipo;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public Combustible getCombustible() {
		return combustible;
	}

	public void setCombustible(Combustible combustible) {
		this.combustible = combustible;
	}

	public TipoVehiculo getTipo() {
		return tipo;
	}

	public void setTipo(TipoVehiculo tipo) {
		this.tipo = tipo;
	}
	

}
