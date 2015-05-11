package model;

import javax.persistence.Entity;

@Entity
public class Administrador extends Usuario {
	
	public Administrador(){		
	}
	
	public Administrador(String usuario, String clave){
		super(usuario, clave);
	}
	
	public String getPerfil(){
		return "administrador";
	}
	
	public boolean getActivo(){
		return true;
	}
	
	public float calificacionActual(){
		return 0;
	}
	
	
}
