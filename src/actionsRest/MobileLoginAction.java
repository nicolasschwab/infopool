package actionsRest;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;


import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import actionsGeneric.GenericLoginAction;
import dto.ViajeroDto;
import implementacionesDAO.FactoryDAO;
import model.Usuario;

import util.Dozer;
import util.Generics;
import util.SessionUtil;
import util.Validacion;
@Action("/sesion")
public class MobileLoginAction implements SessionAware,ModelDriven<ViajeroDto>{
	
	private String usr;
	private String clave;
	private ViajeroDto model;
	private SessionMap<String, Object> sessionMap;

	public SessionMap<String, Object> getSession() {
		return sessionMap;
	}
	public void setSession(SessionMap<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}	
	public String getUsr() {
		return usr;
	}
	public void setUsr(String usr) {
		this.usr = usr;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}

	@Action("/login")
	public void index(){
		if(Validacion.stringNoVacio(this.getClave()) && Validacion.stringNoVacio(this.getUsr()) ){
			String login=Generics.getGenericLoginAction().iniciarSesionGeneric(this.getUsr(), this.getClave(), FactoryDAO.getUsuarioDAO(), sessionMap);
			if (login=="success"){
				this.setModel((Dozer.getMapper().map(FactoryDAO.getUsuarioDAO().existe(this.getUsr(),this.getClave()),ViajeroDto.class)));
			}			
		}
	}
	
	@Action("/logout")
	public void cerrarSesion() {
		Generics.getGenericLoginAction().cerrarSesionGeneric(sessionMap);
	}
	
	@Override
	public void setSession(Map<String, Object> map) {
		sessionMap = (SessionMap<String, Object>) map;		
	}

	
	public ViajeroDto getModel() {
		if(model==null){
			model=new ViajeroDto();
		}
		return model;
	}
	
	public void setModel(ViajeroDto model){
		this.model=model;
	}
}
