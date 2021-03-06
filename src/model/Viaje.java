package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Type;

@Entity
public class Viaje implements Serializable{
	
	private static final long serialVersionUID = 1L;	
	
	@Id @GeneratedValue
	private int id;
	
	@Column(nullable = false)
	private String direccionOrigen;
	
	/*@Column(nullable = false)
	private CoordenadasLatLng puntoOrigen;*/
	
	@Column(nullable = false)
	private String direccionDestino;
	
	/*@Column(nullable = false)
	private CoordenadasLatLng puntoDestino;*/
	
	@Column
	@Type(type="text")
    private String puntosTrayecto;
	
	@Column(nullable = false)
	private Date fechaPublicacion;
	
	@Column(nullable = false)
	private Date fechaInicio;
	
	@Column
	private Date fechaFin;		
		
	@ManyToOne(optional=true)
	private Evento eventoAsociado;
	
	@OneToOne(cascade = {CascadeType.ALL})
	private Conversacion foroViaje;
	
	@OneToMany(mappedBy="viaje",cascade = {CascadeType.ALL})
	private Collection<Calificacion> calificaciones = new ArrayList<Calificacion>();
	
	@OneToMany(mappedBy="viaje",cascade = {CascadeType.ALL})
	private Collection<FrecuenciaViaje> frecuencias = new ArrayList<FrecuenciaViaje>();
	
	@ManyToOne(optional=false)
	private Viajero conductor;
	
	@ManyToMany(mappedBy="misViajesPasajero",fetch=FetchType.EAGER)
	private Collection<Viajero> pasajeros;
	
	@Enumerated(EnumType.STRING)
	private TipoViaje tipoViaje;
	
	@Column
	private String descripcion;
	
	@Column(nullable=false)
	private float kilometros;
	
	@Column(nullable=false)
	private boolean activo;
	
	public Viaje() {
		super();
	}	
	/*public Viaje(String direccionOrigen, CoordenadasLatLng puntoOrigen, String direccionDestino, CoordenadasLatLng puntoDestino,
			String puntosTrayecto, Date fechaPublicacion, Date fechaInicio, Date fechaFin,
			Evento eventoAsociado, Conversacion foroViaje, Collection<FrecuenciaViaje> frecuencias,
			Viajero conductor, TipoViaje tipoViaje, String descripcion, float kilometros,
			boolean activo) {*/
	public Viaje(String direccionOrigen, String direccionDestino,
			String puntosTrayecto, Date fechaPublicacion, Date fechaInicio, Date fechaFin,
			Evento eventoAsociado, Conversacion foroViaje, Collection<FrecuenciaViaje> frecuencias,
			Viajero conductor, TipoViaje tipoViaje, String descripcion, float kilometros,
			boolean activo) {
		super();
		this.direccionOrigen = direccionOrigen;
		/*this.puntoOrigen = puntoOrigen;*/
		this.direccionDestino = direccionDestino;
		/*this.puntoDestino = puntoDestino;*/
		this.puntosTrayecto = puntosTrayecto;
		this.fechaPublicacion = fechaPublicacion;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.eventoAsociado = eventoAsociado;
		this.foroViaje = foroViaje;
		this.frecuencias = frecuencias;
		this.conductor = conductor;		
		this.tipoViaje = tipoViaje;
		this.descripcion = descripcion;
		this.kilometros = kilometros;
		this.activo = activo;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDireccionOrigen() {
		return direccionOrigen;
	}
	public void setDireccionOrigen(String direccionOrigen) {
		this.direccionOrigen = direccionOrigen;
	}	
	/*public CoordenadasLatLng getPuntoOrigen() {
		return puntoOrigen;
	}
	public void setPuntoOrigen(CoordenadasLatLng puntoOrigen) {
		this.puntoOrigen = puntoOrigen;
	}*/
	public String getDireccionDestino() {
		return direccionDestino;
	}
	public void setDireccionDestino(String direccionDestino) {
		this.direccionDestino = direccionDestino;
	}	
	/*public CoordenadasLatLng getPuntoDestino() {
		return puntoDestino;
	}
	public void setPuntoDestino(CoordenadasLatLng puntoDestino) {
		this.puntoDestino = puntoDestino;
	}*/
	public String getPuntosTrayecto() {
		return puntosTrayecto;
	}
	public void setPuntosTrayecto(String puntosTrayecto) {
		this.puntosTrayecto = puntosTrayecto;
	}
	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}
	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
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
	public Evento getEventoAsociado() {
		return eventoAsociado;
	}
	public void setEventoAsociado(Evento eventoAsociado) {
		this.eventoAsociado = eventoAsociado;
	}	
	public Conversacion getForoViaje() {
		return foroViaje;
	}
	public void setForoViaje(Conversacion foroViaje) {
		this.foroViaje = foroViaje;
	}
	public Collection<Calificacion> getCalificaciones() {
		return calificaciones;
	}
	public void setCalificaciones(Collection<Calificacion> calificaciones) {
		this.calificaciones = calificaciones;
	}
	public Collection<FrecuenciaViaje> getFrecuencias() {
		return frecuencias;
	}
	public void setFrecuencias(Collection<FrecuenciaViaje> frecuencias) {
		this.frecuencias = frecuencias;
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
	public TipoViaje getTipoViaje() {
		return tipoViaje;
	}
	public void setTipoViaje(TipoViaje tipoViaje) {
		this.tipoViaje = tipoViaje;
	}	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public float getKilometros() {
		return kilometros;
	}
	public void setKilometros(float kilometros) {
		this.kilometros = kilometros;
	}
	public boolean getActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	public Collection<Viajero> obtenerPasajeros(){		
		return this.pasajeros;
	}	
	public boolean esConductor(Usuario usuario){
		boolean esConductor = false;
		if (this.conductor.getUsuario().equals(usuario.getUsuario())){
			esConductor = true;
		}
		return esConductor;
	}
	public boolean esPasajero(Usuario usuario){		
		return this.pasajeros.contains(usuario.getUsuario());
	}
	public boolean tieneDisponibleFrecuencia(DiasSemana diaSolicitud) {
		boolean disponible = false;
		for(FrecuenciaViaje f : this.getFrecuencias()){
			if (f.getDiaFrecuencia().equals(diaSolicitud)){
				disponible = (f.getAsientosDisponibles() > 0);
			}
		}
		return disponible;
	}	
	public void quitarPasajero(Viajero viajero){
		for(Viajero v : pasajeros){
			if(v.getId()==viajero.getId()){
				this.pasajeros.remove(v);
				this.getForoViaje().getParticipantesConversacion().remove(v);
				break;
			}
		}
	}
}