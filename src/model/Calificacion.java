package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Calificacion implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue
	private int id;	
	@ManyToOne
	private Viajero calificador;	
	@ManyToOne
	private Viajero calificado;	
	private int calificacion;	
	@ManyToOne
	private Viaje viaje;
	
	public Calificacion() {		
	}
	
	public Calificacion(Viajero calificador, Viajero calificado, int calificacion, Viaje viaje) {
		super();
		this.calificador = calificador;
		this.calificado = calificado;
		this.calificacion = calificacion;
		this.viaje = viaje;
	}
	
	public Viajero getCalificador() {
		return calificador;
	}
	public void setCalificador(Viajero calificador) {
		this.calificador = calificador;
	}
	public Viajero getCalificado() {
		return calificado;
	}
	public void setCalificado(Viajero calificado) {
		this.calificado = calificado;
	}
	public int getCalificacion() {
		return calificacion;
	}
	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}
	public Viaje getViaje() {
		return viaje;
	}
	public void setViaje(Viaje viaje) {
		this.viaje = viaje;
	}
	public int getId() {
		return id;
	}
	
	
}
