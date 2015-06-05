package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Evento implements Serializable{	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue
	private int id;
	private String nombre;
	private Date fechaHora;
	private String web;
	private String ubicacion;	
	@OneToMany(mappedBy="evento", cascade={CascadeType.PERSIST, CascadeType.REMOVE})
	private Collection<Viaje> viajes;
	private boolean activo;
	
	public Evento() {
		super();
	}
	
	public Evento(String nombre, Date fechaHora, String web, String ubicacion) {
		super();		
		this.nombre = nombre;
		this.fechaHora = fechaHora;
		this.web = web;
		this.ubicacion = ubicacion;
		this.viajes = new ArrayList<Viaje>();
		this.activo = true;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Date getFechaHora() {
		return fechaHora;
	}
	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
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
	public Collection<Viaje> getViajes() {
		return viajes;
	}
	public void setViajes(Collection<Viaje> viajes) {
		this.viajes = viajes;
	}
	public boolean getActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	public int getId() {
		return id;
	}		
}
