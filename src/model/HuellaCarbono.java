package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class HuellaCarbono implements Serializable{
	
	private final static long serialVersionUID = 1L;
	
	@Id @GeneratedValue	
	private int id;
	
	@Column(nullable=false)
	private Date fecha;
	
	@Column(nullable=false)
	private float kmAcumulados;
	
	@Column(nullable=false)
	private float emisionesAcumuladas;
	
	@Column(nullable=false)
	private int cantidadViajesRealizados;
	
	@ManyToOne(optional=false)
	private Viajero viajeroHuella;
	
	public HuellaCarbono(){
		super();
	}
	
	public HuellaCarbono(Date fecha, float kmAcumulados,
			float emisionesAcumuladas, int cantidadViajesRealizados,
			Viajero viajeroHuella) {
		super();
		this.fecha = fecha;
		this.kmAcumulados = kmAcumulados;
		this.emisionesAcumuladas = emisionesAcumuladas;
		this.cantidadViajesRealizados = cantidadViajesRealizados;
		this.viajeroHuella = viajeroHuella;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public float getKmAcumulados() {
		return kmAcumulados;
	}
	public void setKmAcumulados(float kmAcumulados) {
		this.kmAcumulados = kmAcumulados;
	}
	public float getEmisionesAcumuladas() {
		return emisionesAcumuladas;
	}
	public void setEmisionesAcumuladas(float emisionesAcumuladas) {
		this.emisionesAcumuladas = emisionesAcumuladas;
	}
	public int getCantidadViajesRealizados() {
		return cantidadViajesRealizados;
	}
	public void setCantidadViajesRealizados(int cantidadViajesRealizados) {
		this.cantidadViajesRealizados = cantidadViajesRealizados;
	}
	public Viajero getViajeroHuella() {
		return viajeroHuella;
	}
	public void setViajeroHuella(Viajero viajeroHuella) {
		this.viajeroHuella = viajeroHuella;
	}
	
	
	
}