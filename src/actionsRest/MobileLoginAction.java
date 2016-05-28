package actionsRest;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;


import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import actionsGeneric.LoginActionGeneric;
import dto.ViajeroDto;
import implementacionesDAO.FactoryDAO;
import model.Usuario;

import util.Dozer;

public class MobileLoginAction extends ActionSupport  implements SessionAware,ModelDriven<ViajeroDto>{
	
	private String usuario;
	private String clave;
	private ViajeroDto model;
	private SessionMap<String, Object> sessionMap;

	public SessionMap<String, Object> getSession() {
		return sessionMap;
	}
	public void setSession(SessionMap<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
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

	@Action("/mobileLogin")
	public void index(){
		//FIXME falta agregar las validaciones, cuando se tenga la app android andando
		LoginActionGeneric.iniciarSesionGeneric(usuario, clave,FactoryDAO.getUsuarioDAO(),sessionMap);
		model=(new Dozer().getMapper().map(FactoryDAO.getUsuarioDAO().existe(usuario,clave),ViajeroDto.class));
	}
	
	@Action("/mobileLogout")
	public String cerrarSesion() {		
		return LoginActionGeneric.cerrarSesionGeneric(sessionMap);	
	}
	
	@Override
	public void setSession(Map<String, Object> map) {
		sessionMap = (SessionMap<String, Object>) map;		
	}

	
	public ViajeroDto getModel() {
		return model;
	}
}
