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

import util.SessionUtil;

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
	private FrecuenciaViaje frecuenciaViaje;
	
	@ManyToOne(optional=false)
	private Viajero viajero;
	
	@Column
	@Type(type="text")
    private String puntoEncuentro;
	
	/*@Column
	private CoordenadasLatLng puntoEncuentro;*/
	
	public SolicitudViaje() {
		super();
	}
	/*public SolicitudViaje(Date fechaInicioSolicitud, Date fechaFinSolicitud,
			EstadoSolicitud estadoSolicitud, Viajero viajero,
			FrecuenciaViaje frecuenciaViaje, CoordenadasLatLng puntoEncuentro) {*/
	public SolicitudViaje(Date fechaInicioSolicitud, Date fechaFinSolicitud,
			EstadoSolicitud estadoSolicitud, Viajero viajero,
			FrecuenciaViaje frecuenciaViaje, String puntoEncuentro) {
		super();
		this.fechaInicioSolicitud = fechaInicioSolicitud;
		this.fechaFinSolicitud = fechaFinSolicitud;
		this.estadoSolicitud = estadoSolicitud;
		this.frecuenciaViaje = frecuenciaViaje;
		this.viajero = viajero;		
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
	public Viajero getViajero() {
		return viajero;
	}
	public void setViajero(Viajero viajero) {
		this.viajero = viajero;
	}	
	/*public CoordenadasLatLng getPuntoEncuentro() {
		return puntoEncuentro;
	}
	public void setPuntoEncuentro(CoordenadasLatLng puntoEncuentro) {
		this.puntoEncuentro = puntoEncuentro;
	}*/
	public String getPuntoEncuentro() {
		return puntoEncuentro;
	}
	public void setPuntoEncuentro(String puntoEncuentro) {
		this.puntoEncuentro = puntoEncuentro;
	}
	public FrecuenciaViaje getFrecuenciaViaje() {
		return frecuenciaViaje;
	}
	public void setFrecuenciaViaje(FrecuenciaViaje frecuenciaViaje) {
		this.frecuenciaViaje = frecuenciaViaje;
	}
	public String validarPertenece(Usuario usuario) {
		if(usuario.getId()==SessionUtil.getUsuario().getId()){
			return this.getEstadoSolicitud().toString();
		}
		return null;
	}
	
}