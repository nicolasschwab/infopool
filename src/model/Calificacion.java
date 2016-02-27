package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Calificacion implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue
	private int id;	
	
	@ManyToOne(optional=false)
	private Viajero calificador;	
	
	@ManyToOne(optional=false)
	private Viajero calificado;	
	
	@Column(nullable=false)
	private int calificacion;
	
	@Column
	private Date fechaCalificacion;
	
	@ManyToOne(optional=false)
	private Viaje viaje;
	
	public Calificacion() {		
		super();
	}
	
	public Calificacion(Viajero calificador, Viajero calificado, int calificacion, Date fechaCalificacion, Viaje viaje) {
		super();
		this.calificador = calificador;
		this.calificado = calificado;
		this.calificacion = calificacion;		
		this.fechaCalificacion = fechaCalificacion;
		this.viaje = viaje;
	}
		
	public int getId() {
		return id;
	}
	public void setId(int id){
		this.id = id;
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
	public Date getFechaCalificacion() {
		return fechaCalificacion;
	}
	public void setFechaCalificacion(Date fechaCalificacion) {
		this.fechaCalificacion = fechaCalificacion;
	}
	public Viaje getViaje() {
		return viaje;
	}
	public void setViaje(Viaje viaje) {
		this.viaje = viaje;
	}
	
}