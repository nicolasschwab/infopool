package model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Usuario {	
	
	@Id @GeneratedValue(strategy=GenerationType.TABLE)
	private int id;	
	
	@Column(nullable=false, unique=true)
	private String usuario;
	
	@Column(nullable=false)
	private String clave;		
	
	@ManyToMany(mappedBy="participantesConversacion")
	private Collection<Conversacion> misConversaciones = new ArrayList<Conversacion>();
	
	@OneToMany(mappedBy="receptor")
	private Collection<Notificacion> misNotificaciones = new ArrayList<Notificacion>();
	
	public Usuario(){	
		super();
	}	
	
	public Usuario(String usuario, String clave) {
		super();
		this.usuario = usuario;
		this.clave = clave;
	}
	
	public int getId(){
		return id;
	}	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public Collection<Conversacion> getMisConversaciones() {
		return misConversaciones;
	}
	public void setMisConversaciones(Collection<Conversacion> misConversaciones) {
		this.misConversaciones = misConversaciones;
	}
	public Collection<Notificacion> getMisNotificaciones() {
		return misNotificaciones;
	}
	public void setMisNotificaciones(Collection<Notificacion> misNotificaciones) {
		this.misNotificaciones = misNotificaciones;
	}	
	public abstract String getPerfil();	
	public abstract boolean getActivo();
	public abstract boolean soyAdministrador();
}