package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class SolicitudViaje implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue
	private int id;
	private Date fechaSolicitud;	
	@Enumerated(EnumType.STRING)	
	private EstadoSolicitud estado;	
	@ManyToOne
	private Viaje viaje;	
	@ManyToOne
	private Viajero viajero;
	
	public SolicitudViaje() {
		super();
	}
	
	public SolicitudViaje(int id, Date fechaSolicitud, EstadoSolicitud estado, Viaje viaje, Viajero viajero) {
		super();
		this.id = id;
		this.fechaSolicitud = fechaSolicitud;
		this.estado = estado;
		this.viaje = viaje;
		this.viajero = viajero;
	}
	
	public SolicitudViaje(Date fechaSolicitud, EstadoSolicitud estado, Viaje viaje, Viajero viajero) {
		super();		
		this.fechaSolicitud = fechaSolicitud;
		this.estado = estado;
		this.viaje = viaje;
		this.viajero = viajero;
	}
	
	public Date getFechaSolicitud() {
		return fechaSolicitud;
	}
	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}
	public EstadoSolicitud getEstado() {
		return estado;
	}
	public void setEstado(EstadoSolicitud estado) {
		this.estado = estado;
	}
	public Viaje getViaje() {
		return viaje;
	}
	public void setViaje(Viaje viaje) {
		this.viaje = viaje;
	}
	public Viajero getViajero() {
		return viajero;
	}
	public void setViajero(Viajero viajero) {
		this.viajero = viajero;
	}
	public int getId() {
		return id;
	}
	
	
}
