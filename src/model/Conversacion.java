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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
public class Conversacion implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue 
	@Column(name="conversacion_id")
	private int id;
	
	@OneToMany(cascade = {CascadeType.MERGE})
	private Collection<Mensaje> mensajes = new ArrayList<Mensaje>();
	
	@ManyToMany
	@JoinTable( name="Conversacion_Usuario", 
				joinColumns={@JoinColumn(name="conversacion_id", nullable=false)}, 
				inverseJoinColumns={@JoinColumn(name="usuario_id", nullable=false)})  
	private Collection<Viajero> participantesConversacion = new ArrayList<Viajero>();
	
	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
	private Viaje viaje;
	
	@Column(nullable=false)
	private String asunto;
	
	@Column(nullable=false)
	private Date fechaUltimaModificacion;
	
	@Enumerated(EnumType.STRING)
	private TipoConversacion tipoConversacion;
			
	public Conversacion(){
		super();
		this.fechaUltimaModificacion=new Date();
	}
			
	public Conversacion(Collection<Mensaje> mensajes,
			Collection<Viajero> participantesConversacion, Viaje viaje, String asunto,
			Date fechaUltimaModificacion, TipoConversacion tipoConversacion) {
		super();
		this.mensajes = mensajes;
		this.participantesConversacion = participantesConversacion;
		this.viaje = viaje;
		this.asunto = asunto;
		this.fechaUltimaModificacion = fechaUltimaModificacion;
		this.tipoConversacion = tipoConversacion;
	}
	
	public int getId() {
		return id;
	}	
	public void setId(int id){
		this.id = id;
	}
	public Collection<Mensaje> getMensajes() {
		if(this.mensajes==null){
			mensajes=new ArrayList();
		}
		return mensajes;
	}
	public void setMensajes(Collection<Mensaje> mensajes) {
		this.mensajes = mensajes;
	}
	public Collection<Viajero> getParticipantesConversacion() {
		if(this.participantesConversacion==null){
			this.participantesConversacion=new ArrayList();
		}		
		return participantesConversacion;
	}
	public void setParticipantesConversacion(Collection<Viajero> participantesConversacion) {
		this.participantesConversacion = participantesConversacion;
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
	public TipoConversacion getTipoConversacion() {
		return tipoConversacion;
	}
	public void setTipoConversacion(TipoConversacion tipoConversacion) {
		this.tipoConversacion = tipoConversacion;
	}	
	public void agregarParticipantes(Viajero... viajeros){
		for(Viajero viajero: viajeros){
			this.getParticipantesConversacion().add(viajero);
		}
	}
	public void agregarMensajes(Mensaje... mensajes){
		for(Mensaje mensaje: mensajes){
			this.getMensajes().add(mensaje);
		}
	}
}