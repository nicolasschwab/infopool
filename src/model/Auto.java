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
public class Auto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue
	private int id;
	
	@ManyToOne
	private MarcaAuto marca;
	
	@ManyToOne
	private ModeloAuto modelo;
	
	@Enumerated(EnumType.STRING)
	private Combustible combustibleAlternativo;
	
	@Column
	private String caracteristicas;
		
	public Auto() {
		super();
	}
	
	public Auto(MarcaAuto marca, ModeloAuto modelo, Combustible combustibleAlternativo, String caracteristicas) {
		super();
		this.marca = marca;
		this.modelo = modelo;
		this.combustibleAlternativo = combustibleAlternativo;
		this.caracteristicas = caracteristicas;
	}

	public int getId() {
		return id;
	}
	public void setId(int id){
		this.id = id;
	}
	public MarcaAuto getMarca() {
		return marca;
	}
	public void setMarca(MarcaAuto marca) {
		this.marca = marca;
	}
	public ModeloAuto getModelo() {
		return modelo;
	}
	public void setModelo(ModeloAuto modelo) {
		this.modelo = modelo;
	}
	public Combustible getCombustibleAlternativo() {
		return combustibleAlternativo;
	}
	public void setCombustibleAlternativo(Combustible combustibleAlternativo) {
		this.combustibleAlternativo = combustibleAlternativo;
	}
	public String getCaracteristicas() {
		return caracteristicas;
	}
	public void setCaracteristicas(String caracteristicas) {
		this.caracteristicas = caracteristicas;
	}
	public double calcularEmision(float km){
		return km*modelo.getEmisionGases();
	}

}