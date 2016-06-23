package actions;

import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import interfacesDAO.UsuarioDAO;
import model.Usuario;
import util.Csrf;
import util.Generics;
import util.SessionUtil;

import com.opensymphony.xwork2.ActionSupport;

import actionsGeneric.GenericLoginAction;

public class LoginAction extends ActionSupport implements SessionAware{
	
	private static final long serialVersionUID = 1L;
	
	//DATOS FORMULARIO
	private String usuario;
	private String clave;
		
	private SessionMap<String, Object> sessionMap;
	private UsuarioDAO usuarioDAO;
	private Usuario user;
		
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
	public SessionMap<String, Object> getSession() {
		return sessionMap;
	}
	public void setSession(SessionMap<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}
	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}
	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}
	public Usuario getUser() {
		return user;
	}
	public void setUser(Usuario user) {
		this.user = user;
	}

	public String iniciarSesion() {
		if (!SessionUtil.checkLogin()) {
			if ((this.getUsuario() != null ) && (this.getClave() != null )){
				if(this.getUsuario().length() > 0  && this.getClave().length() > 0 ){				
					String respuesta=Generics.getGenericLoginAction().iniciarSesionGeneric(this.getUsuario(),this.getClave(),this.getUsuarioDAO(),sessionMap);
					switch (respuesta){
						case "success":
							return this.finSuccessMetodo();
						default:
							return this.finErrorMetodo(respuesta);
					}
				}
				else{
					return this.finErrorMetodo("faltanDatos");
				}
			}
			else{
				return this.finErrorMetodo("faltanDatos");				
			}
		} else {
			return this.finLogueadoMetodo();
		}
	}	
	
	public String cerrarSesion() {
		return Generics.getGenericLoginAction().cerrarSesionGeneric(sessionMap);		
	}
	@Override
	public void setSession(Map<String, Object> map) {
		sessionMap = (SessionMap<String, Object>) map;		
	}
	
	private String finSuccessMetodo() {
		return SUCCESS;
	}	
	private String finLogueadoMetodo() {
		return "conectado";
	}
	private String finErrorMetodo(String tipo) {
		addFieldError("loginError", getText("error.login."+tipo));
		return INPUT;
	}
	
}