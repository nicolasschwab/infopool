package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class ForoMensajes implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue
	private int id;
	private Date fechaPublicacion;	
	@OneToOne
	private Viajero viajero;	
	private String mensaje;	
	@ManyToOne
	private Viaje viaje;
	
	public ForoMensajes() {
		super();
	}
	
	public ForoMensajes( Date fechaPublicacion, Viajero viajero, String mensaje, Viaje viaje) {
		super();
		this.fechaPublicacion = fechaPublicacion;
		this.viajero = viajero;
		this.mensaje = mensaje;
		this.viaje=viaje;
	}
	
	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}
	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}
	public Viajero getViajero() {
		return viajero;
	}
	public void setViajero(Viajero viajero) {
		this.viajero = viajero;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public int getId() {
		return id;
	}
	
}
