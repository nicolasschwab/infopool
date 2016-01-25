package model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
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
	@Column(unique=true)
	private String usuario;
	private String clave;		
	@ManyToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy="participantes")
	private Collection<Conversacion> misConversaciones;
	@OneToMany(mappedBy = "receptor", cascade={CascadeType.PERSIST, CascadeType.REMOVE})
	private Collection<Notificacion> misNotificaciones;
	
	public Usuario(){		
	}	
	
	public Usuario(String usuario, String clave) {
		super();
		this.usuario = usuario;
		this.clave = clave;
		this.misConversaciones = new ArrayList<Conversacion>();
		this.misNotificaciones = new ArrayList<Notificacion>();
	}
	
	
	public Collection<Notificacion> getMisNotificaciones() {
		return misNotificaciones;
	}

	public void setMisNotificaciones(Collection<Notificacion> misNotificaciones) {
		this.misNotificaciones = misNotificaciones;
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
	public Collection<Conversacion> getMisMensajesEnviados() {
		return misConversaciones;
	}
	public void setMisMensajesEnviados(Collection<Conversacion> misMensajesEnviados) {
		this.misConversaciones = misMensajesEnviados;
	}	
	
	public abstract String getPerfil();
	
	public abstract boolean getActivo();
	
	public abstract float calificacionActual(); 
}