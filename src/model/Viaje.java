package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Viaje implements Serializable{	
	private static final long serialVersionUID = 1L;	
	@Id @GeneratedValue
	private int id;
	private Date fechaInicio;
	private Date fechaFin;
	@ElementCollection(targetClass=DiasSemana.class)
	@Enumerated(EnumType.STRING)
	private List<DiasSemana> diasSemana;	
	private Date horaPartida;
	private Date horaRegreso;
	private int asientos;
	private String direccionOrigen;
	private String direccionDestino;
	private boolean activo;	
	@OneToMany(mappedBy="viaje",cascade={CascadeType.PERSIST, CascadeType.REMOVE})
	private Collection<SolicitudViaje> solicitudes;	
	@ManyToOne
	private Evento evento;	
	@OneToMany(mappedBy="viaje", cascade={CascadeType.PERSIST, CascadeType.REMOVE})
	private Collection<ForoMensajes> mensajes;	
	@OneToMany(mappedBy="viaje", cascade={CascadeType.PERSIST, CascadeType.REMOVE})
	private Collection<Calificacion> calificaciones;	
	@ManyToOne
	private Viajero conductor;	
	@ManyToMany(mappedBy="misViajesPasajero")
	private Collection<Viajero> pasajeros;
	
	public Viaje() {		
	}
	
	public Viaje(Date fechaInicio, Date fechaFin, List<DiasSemana> diasSemana,Date horaPartida, Date horaRegreso, int asientos, String direccionOrigen, String direccionDestino, Evento evento,Viajero conductor) {
		super();
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.diasSemana = diasSemana;
		this.horaPartida = horaPartida;
		this.horaRegreso = horaRegreso;
		this.asientos = asientos;
		this.direccionOrigen = direccionOrigen;
		this.direccionDestino = direccionDestino;
		this.activo = true;
		this.solicitudes = new ArrayList<SolicitudViaje>();
		this.evento = evento;
		this.mensajes = new ArrayList<ForoMensajes>();
		this.calificaciones = new ArrayList<Calificacion>();
		this.conductor = conductor;
		this.pasajeros = new ArrayList<Viajero>();
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	public List<DiasSemana> getDiasSemana() {
		return diasSemana;
	}
	public void setDiasSemana(List<DiasSemana> diasSemana) {
		this.diasSemana = diasSemana;
	}
	public Date getHoraPartida() {
		return horaPartida;
	}
	public void setHoraPartida(Date horaPartida) {
		this.horaPartida = horaPartida;
	}
	public Date getHoraRegreso() {
		return horaRegreso;
	}
	public void setHoraRegreso(Date horaRegreso) {
		this.horaRegreso = horaRegreso;
	}
	public int getAsientos() {
		return asientos;
	}
	public void setAsientos(int asientos) {
		this.asientos = asientos;
	}
	public String getDireccionOrigen() {
		return direccionOrigen;
	}
	public void setDireccionOrigen(String direccionOrigen) {
		this.direccionOrigen = direccionOrigen;
	}
	public String getDireccionDestino() {
		return direccionDestino;
	}
	public void setDireccionDestino(String direccionDestino) {
		this.direccionDestino = direccionDestino;
	}
	public boolean getActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	public Collection<SolicitudViaje> getSolicitudes() {
		return solicitudes;
	}
	public void setSolicitudes(Collection<SolicitudViaje> solicitudes) {
		this.solicitudes = solicitudes;
	}
	public Evento getEvento() {
		return evento;
	}
	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	public Collection<ForoMensajes> getMensajes() {
		return mensajes;
	}
	public void setMensajes(Collection<ForoMensajes> mensajes) {
		this.mensajes = mensajes;
	}
	public Collection<Calificacion> getCalificaciones() {
		return calificaciones;
	}
	public void setCalificaciones(Collection<Calificacion> calificaciones) {
		this.calificaciones = calificaciones;
	}
	public int getId() {
		return id;
	}
	public Viajero getConductor() {
		return conductor;
	}
	public void setConductor(Viajero conductor) {
		this.conductor = conductor;
	}
	public Collection<Viajero> getPasajeros() {
		return pasajeros;
	}
	public void setPasajeros(Collection<Viajero> pasajeros) {
		this.pasajeros = pasajeros;
	}

	public String misDias() {
		String dias = "";
		for (DiasSemana dia : diasSemana) {
			dias += dia.toString() + ", ";
		}
		return dias.substring(0,(dias.length()-2));
	}	
	
	public boolean esPasajero(Usuario usr){
		boolean esP = false;
		for (Viajero v : pasajeros){
			if (v.getUsuario().equals(usr.getUsuario())){
				esP = true;
			}			
		}
		return esP;
	}
	
	public boolean diaSeleccionado(String dia){
		for (DiasSemana diaSem : diasSemana) {
			if (diaSem.name().equals(dia)){
				return true;
			}
		}
		return false;
	}
}
