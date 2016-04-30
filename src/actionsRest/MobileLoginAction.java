package actionsRest;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import actionsGeneric.LoginActionGeneric;
import implementacionesDAO.FactoryDAO;

public class MobileLoginAction extends ActionSupport  implements SessionAware{
	
	private String usuario;
	private String clave;
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
	}
	
	@Action("/mobileLogout")
	public String cerrarSesion() {		
		return LoginActionGeneric.cerrarSesionGeneric(sessionMap);	
	}
	
	@Override
	public void setSession(Map<String, Object> map) {
		sessionMap = (SessionMap<String, Object>) map;		
	}
}
