package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Notificacion implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id 
	private String id;
	
	@Column(nullable=false)
	private String mensaje;
	
	@ManyToOne
	private Usuario emisor;
	
	@ManyToOne
	private Usuario receptor;
	
	@Column(nullable=false)
	private Date fechaHora;
	
	@Column(nullable=false)
	private String tipo;
	
	@Column(nullable=false)
	private String link;
	
	@Enumerated(EnumType.STRING)
	private EstadoNotificacion estado;
		
	public Notificacion() {
		super();	
	}
	
	public Notificacion(String mensaje, Usuario emisor, Usuario receptor, Date fechaHora, String tipo, String link, EstadoNotificacion estado) {
		super();
		this.mensaje = mensaje;
		this.emisor = emisor;
		this.receptor = receptor;
		this.fechaHora = fechaHora;
		this.tipo = tipo;
		this.link = link;
		this.estado = estado;
	}

	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id = id;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public Usuario getEmisor() {
		return emisor;
	}
	public void setEmisor(Usuario emisor) {
		this.emisor = emisor;
	}
	public Usuario getReceptor() {
		return receptor;
	}
	public void setReceptor(Usuario receptor) {
		this.receptor = receptor;
	}
	public Date getFechaHora() {
		return fechaHora;
	}
	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public EstadoNotificacion getEstado() {
		return estado;
	}
	public void setEstado(EstadoNotificacion estado) {
		this.estado = estado;
	}	
}