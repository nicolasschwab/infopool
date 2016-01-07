package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Notificacion implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id 
	private String id;
	private String mensaje;
	@ManyToOne
	private Usuario emisor;
	@ManyToOne
	private Usuario receptor;
	private Date hora;
	private String tipo;
	private String link;
	@ManyToOne
	private EstadoNotificacion estado;
	
	public Notificacion(){
		
	}
	public Notificacion(String elMensaje, Usuario elEmisor,Usuario elReceptor,Date laHora,String elTipo){
		this.mensaje=elMensaje;
		this.emisor=elEmisor;
		this.receptor=elReceptor;
		this.hora=laHora;
		this.tipo=elTipo;
	}
	
	
	public EstadoNotificacion getEstado() {
		return estado;
	}
	public void setEstado(EstadoNotificacion estado) {
		this.estado = estado;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public Date getHora() {
		return hora;
	}
	public void setHora(Date hora) {
		this.hora = hora;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
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
	
}
