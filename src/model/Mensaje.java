package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Mensaje implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue
	private int id;
	
	@Column(nullable=false)
	private Date fechaPublicacion;
	
	@Column(nullable=false)
	private String detalle;
	
	@Column(nullable=false)
	private String estado;	
	
	@ManyToOne(optional=false)
	private Usuario emisor;
	
	@ManyToOne(optional=false)
	private Conversacion conversacion;
	
	public Mensaje() {
		super();
	}
	
	public Mensaje( Date fechaPublicacion, String detalle, String estado, Usuario emisor, Conversacion conversacion) {
		super();
		this.fechaPublicacion = fechaPublicacion;
		this.detalle = detalle;
		this.estado = estado;
		this.emisor = emisor;
		this.conversacion = conversacion;
	}

	public int getId() {
		return id;
	}
	public void setId(int id){
		this.id = id;
	}
	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}
	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Usuario getEmisor() {
		return emisor;
	}
	public void setEmisor(Usuario emisor) {
		this.emisor = emisor;
	}
	public Conversacion getConversacion() {
		return conversacion;
	}
	public void setConversacion(Conversacion conversacion) {
		this.conversacion = conversacion;
	}	
}