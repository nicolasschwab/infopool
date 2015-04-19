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
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Usuario {	
	@Id @GeneratedValue(strategy=GenerationType.TABLE)
	private int id;	
	@Column(unique=true)
	private String usuario;
	private String clave;	
	@OneToMany(mappedBy = "receptor", cascade={CascadeType.PERSIST, CascadeType.REMOVE})
	private Collection<Mensaje> misMensajesRecibidos;	
	@OneToMany(mappedBy = "emisor", cascade={CascadeType.PERSIST, CascadeType.REMOVE})
	private Collection<Mensaje> misMensajesEnviados;
	
	public Usuario(){		
	}	
	
	public Usuario(String usuario, String clave) {
		super();
		this.usuario = usuario;
		this.clave = clave;
		this.misMensajesRecibidos = new ArrayList<Mensaje>();
		this.misMensajesEnviados = new ArrayList<Mensaje>();
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
	public Collection<Mensaje> getMisMensajesRecibidos() {
		return misMensajesRecibidos;
	}
	public void setMisMensajesRecibidos(Collection<Mensaje> misMensajesRecibidos) {
		this.misMensajesRecibidos = misMensajesRecibidos;
	}
	public Collection<Mensaje> getMisMensajesEnviados() {
		return misMensajesEnviados;
	}
	public void setMisMensajesEnviados(Collection<Mensaje> misMensajesEnviados) {
		this.misMensajesEnviados = misMensajesEnviados;
	}	
	
	public abstract String getPerfil();
	
	public abstract boolean getActivo();
}