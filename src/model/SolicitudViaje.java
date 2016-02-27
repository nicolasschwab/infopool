package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;

@Entity
public class SolicitudViaje implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue
	private int id;
	
	@Column(nullable=false)
	private Date fechaInicioSolicitud;
	
	@Column
	private Date fechaFinSolicitud;
	
	@Enumerated(EnumType.STRING)	
	private EstadoSolicitud estadoSolicitud;	
	
	@ManyToOne(optional=false)
	private Viaje viaje;
	
	@ManyToOne(optional=false)
	private Viajero viajero;
	
	@Enumerated(EnumType.STRING)
	private DiasSemana diaSolicitud;
	
	@Column
	@Type(type="text")
    private String puntoEncuentro;
	
	public SolicitudViaje() {
		super();
	}
	public SolicitudViaje(Date fechaInicioSolicitud, Date fechaFinSolicitud,
			EstadoSolicitud estadoSolicitud, Viaje viaje, Viajero viajero,
			DiasSemana diaSolicitud, String puntoEncuentro) {
		super();
		this.fechaInicioSolicitud = fechaInicioSolicitud;
		this.fechaFinSolicitud = fechaFinSolicitud;
		this.estadoSolicitud = estadoSolicitud;
		this.viaje = viaje;
		this.viajero = viajero;
		this.diaSolicitud = diaSolicitud;
		this.puntoEncuentro = puntoEncuentro;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getFechaInicioSolicitud() {
		return fechaInicioSolicitud;
	}
	public void setFechaInicioSolicitud(Date fechaInicioSolicitud) {
		this.fechaInicioSolicitud = fechaInicioSolicitud;
	}
	public Date getFechaFinSolicitud() {
		return fechaFinSolicitud;
	}
	public void setFechaFinSolicitud(Date fechaFinSolicitud) {
		this.fechaFinSolicitud = fechaFinSolicitud;
	}
	public EstadoSolicitud getEstadoSolicitud() {
		return estadoSolicitud;
	}
	public void setEstadoSolicitud(EstadoSolicitud estadoSolicitud) {
		this.estadoSolicitud = estadoSolicitud;
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
	public DiasSemana getDiaSolicitud() {
		return diaSolicitud;
	}
	public void setDiaSolicitud(DiasSemana diaSolicitud) {
		this.diaSolicitud = diaSolicitud;
	}
	public String getPuntoEncuentro() {
		return puntoEncuentro;
	}
	public void setPuntoEncuentro(String puntoEncuentro) {
		this.puntoEncuentro = puntoEncuentro;
	}
	
}