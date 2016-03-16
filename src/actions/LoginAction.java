package actions;

import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import interfacesDAO.UsuarioDAO;
import model.Usuario;
import util.Csrf;
import util.SessionUtil;

import com.opensymphony.xwork2.ActionSupport;

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
			if (this.getUsuario() == null){
				return INPUT;
			}
			if ((this.getUsuario().length() > 0) && (this.getClave().length() > 0)){
				user = usuarioDAO.existe(this.getUsuario(),this.getClave());
				if (user != null) {
					if (user.getActivo()){						
						sessionMap.put("usrLogin", user);
						//Esto genera los id unicos y randoms que el ususario va a usar en esta sesion
						Csrf.getTokenId();
						Csrf.getTokenValue();
						return SUCCESS;
					}
					else{
						addFieldError("loginError", "La cuenta a la que desea ingresa se encuentra desactivada temporalmente!");
						return INPUT;
					}
				} else {
					addFieldError("loginError", "Los datos ingresado son incorrectos!");
					return INPUT;
				}
			}
			else{
				addFieldError("loginError", "Debe ingresar el usuario y la clave para acceder!");
				return INPUT;
			}
		} else {
			return "conectado";
		}
	}	
	
	public String cerrarSesion() {		
		if(SessionUtil.checkLogin()){
			sessionMap.invalidate();
			return SUCCESS;
		}else{
			return "sinPermisos";
		}		
	}
	@Override
	public void setSession(Map<String, Object> map) {
		sessionMap = (SessionMap<String, Object>) map;		
	}
	
}