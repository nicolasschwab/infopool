package model;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Type;

@Entity
public class FrecuenciaViaje implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue
	private int id;	
	
	@Enumerated(EnumType.STRING)
	private DiasSemana diaFrecuencia;
	
	@Enumerated(EnumType.STRING)
	private EstadoFrecuencia estadoFrecuencia;
	
	@Column(nullable = false)
	private Time horaPartida;
	
	@Column
	private Time horaRegreso;
	
	@Column(nullable = false)
	private int asientosDisponibles;
	
	@ManyToMany(mappedBy="misFrecuenciasPasajero",cascade = {CascadeType.ALL})
	private Collection<Viajero> pasajeros = new ArrayList<Viajero>();
	
	@ManyToMany(mappedBy="miHistorialFrecuencias",cascade = {CascadeType.ALL})
    private Collection<Viajero> pasajerosHistorial = new ArrayList<Viajero>();
	
	@ManyToOne(optional=false)
	private Viaje viaje;
	
	@OneToMany(mappedBy="frecuenciaViaje")
	private Collection<SolicitudViaje> solicitudesViaje = new ArrayList<SolicitudViaje>();
	
	@Enumerated(EnumType.STRING)
	private TramoViaje tramoViaje;
	
	@Column
	@Type(type="text")
    private String puntosTrayecto;
	
	@Column(nullable=false)
	private float kilometros;
	
	public FrecuenciaViaje(){
		super();
	}	
	public FrecuenciaViaje(DiasSemana diaFrecuencia, EstadoFrecuencia estadoFrecuencia, Time horaPartida,
			Time horaRegreso, int asientosDisponibles, Viaje viaje, String puntosTrayecto, float kilometros, TramoViaje tramoViaje) {
		super();
		this.diaFrecuencia = diaFrecuencia;
		this.estadoFrecuencia = estadoFrecuencia;
		this.horaPartida = horaPartida;
		this.horaRegreso = horaRegreso;
		this.asientosDisponibles = asientosDisponibles;
		this.viaje = viaje;
		this.tramoViaje = tramoViaje;
		this.puntosTrayecto = puntosTrayecto;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public DiasSemana getDiaFrecuencia() {
		return diaFrecuencia;
	}
	public void setDiaFrecuencia(DiasSemana diaFrecuencia) {
		this.diaFrecuencia = diaFrecuencia;
	}
	public EstadoFrecuencia getEstadoFrecuencia() {
		return estadoFrecuencia;
	}
	public void setEstadoFrecuencia(EstadoFrecuencia estadoFrecuencia) {
		this.estadoFrecuencia = estadoFrecuencia;
	}
	public Time getHoraPartida() {
		return horaPartida;
	}
	public void setHoraPartida(Time horaPartida) {
		this.horaPartida = horaPartida;
	}
	public Time getHoraRegreso() {
		return horaRegreso;
	}
	public void setHoraRegreso(Time horaRegreso) {
		this.horaRegreso = horaRegreso;
	}
	public int getAsientosDisponibles() {
		return asientosDisponibles;
	}
	public void setAsientosDisponibles(int asientosDisponibles) {
		this.asientosDisponibles = asientosDisponibles;
	}
	public Collection<Viajero> getPasajeros() {
		return pasajeros;
	}
	public void setPasajeros(Collection<Viajero> pasajeros) {
		this.pasajeros = pasajeros;
	}
	public Collection<Viajero> getPasajerosHistorial() {
		return pasajerosHistorial;
	}
	public void setPasajerosHistorial(Collection<Viajero> pasajerosHistorial) {
		this.pasajerosHistorial = pasajerosHistorial;
	}
	public Viaje getViaje() {
		return viaje;
	}
	public void setViaje(Viaje viaje) {
		this.viaje = viaje;
	}
	public Collection<SolicitudViaje> getSolicitudesViaje() {
		return solicitudesViaje;
	}
	public void setSolicitudesViaje(Collection<SolicitudViaje> solicitudesViaje) {
		this.solicitudesViaje = solicitudesViaje;
	}	
	public TramoViaje getTramoViaje() {
		return tramoViaje;
	}
	public void setTramoViaje(TramoViaje tramoViaje) {
		this.tramoViaje = tramoViaje;
	}	
	public String getPuntosTrayecto() {
		return puntosTrayecto;
	}
	public void setPuntosTrayecto(String puntosTrayecto) {
		this.puntosTrayecto = puntosTrayecto;
	}	
	public float getKilometros() {
		return kilometros;
	}
	public void setKilometros(float kilometros) {
		this.kilometros = kilometros;
	}
	
	public boolean esPasajero(Usuario usuario){
		boolean esPasajero = false;
		for (Viajero v : this.pasajeros){
			if (v.getUsuario().equals(usuario.getUsuario())){
				esPasajero = true;
			}			
		}
		return esPasajero;
	}
	public void agregarViajeroFrecuencia(Viajero viajero){
		this.pasajeros.add(viajero);
	}
	public void agregarPasajeroHistorial(Viajero viajero){		
		for (Viajero v : this.pasajeros) {			
			if(v.getId()==viajero.getId()){				
				this.pasajeros.remove(v);
				break;
			}					
		}
	}	
}