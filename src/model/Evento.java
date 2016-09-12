package model;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Evento implements Serializable{	
	
	private static final long serialVersionUID = 1L;	
	
	@Id @GeneratedValue
	private int id;
	
	@Column(nullable=false)
	private String nombre;
	
	@Column(nullable=false)
	private Date fecha;
	
	@Column
    private Time horaInicio;
    
    @Column
    private Time horaFin;
	
	@Column
	private String web;
	
	@Column(nullable=false)
	private String ubicacion;	
	
	/*@Column
	private CoordenadasLatLng puntoUbicacion;*/
	
	@Column(length = 1023)
	private String descripcion;
	
	@OneToMany(mappedBy="eventoAsociado")
	private Collection<Viaje> viajesAsociados = new ArrayList<Viaje>();
	
	@Column(nullable=false)
	private boolean activo;
	
	public Evento() {
		super();
	}
	/*public Evento(String nombre, Date fecha, Time horaInicio, Time horaFin,
			String web, String ubicacion, CoordenadasLatLng puntoUbicacion, String descripcion, boolean activo) {*/
	public Evento(String nombre, Date fecha, Time horaInicio, Time horaFin,
			String web, String ubicacion, String descripcion, boolean activo) {
		super();
		this.nombre = nombre;
		this.fecha = fecha;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.web = web;
		this.ubicacion = ubicacion;
		//this.puntoUbicacion = puntoUbicacion;
		this.descripcion = descripcion;
		this.activo = activo;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Time getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(Time horaInicio) {
		this.horaInicio = horaInicio;
	}
	public Time getHoraFin() {
		return horaFin;
	}
	public void setHoraFin(Time horaFin) {
		this.horaFin = horaFin;
	}
	public String getWeb() {
		return web;
	}
	public void setWeb(String web) {
		this.web = web;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}	
	/*public CoordenadasLatLng getPuntoUbicacion() {
		return puntoUbicacion;
	}
	public void setPuntoUbicacion(CoordenadasLatLng puntoUbicacion) {
		this.puntoUbicacion = puntoUbicacion;
	}*/
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Collection<Viaje> getViajesAsociados() {
		return viajesAsociados;
	}
	public void setViajesAsociados(Collection<Viaje> viajesAsociados) {
		this.viajesAsociados = viajesAsociados;
	}
	public boolean getActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
			
}