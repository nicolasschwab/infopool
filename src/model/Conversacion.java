package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Conversacion implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue@Column(name="conversacion_Id")
	private int id;
	@OneToMany
	private Collection<Mensaje> mensajes;
	@ManyToMany
	@JoinTable(name="conversacion_usuario", joinColumns=@JoinColumn(name="conversacion_Id"), inverseJoinColumns=@JoinColumn(name="id"))  
	private Collection<Viajero> participantes;
	@ManyToOne
	private Viaje viaje;
	private String asunto;
	private Date fechaUltimaModificacion;
	
	public Conversacion(){
		this.setFechaUltimaModificacion(new Date());
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Collection<Mensaje> getMensajes() {
		return mensajes;
	}
	public void setMensajes(Collection<Mensaje> mensajes) {
		this.mensajes = mensajes;
	}
	public Collection<Viajero> getParticipantes() {
		return participantes;
	}
	public void setParticipantes(Collection<Viajero> participantes) {
		this.participantes = participantes;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public Viaje getViaje() {
		return viaje;
	}
	public void setViaje(Viaje viaje) {
		this.viaje = viaje;
	}
	public Date getFechaUltimaModificacion() {
		return fechaUltimaModificacion;
	}
	public void setFechaUltimaModificacion(Date fechaUltimaModificacion) {
		this.fechaUltimaModificacion = fechaUltimaModificacion;
	}
	
	
	
}
