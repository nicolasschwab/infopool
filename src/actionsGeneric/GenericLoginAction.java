package actionsGeneric;

import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import implementacionesDAO.FactoryDAO;
import interfacesDAO.UsuarioDAO;
import model.Usuario;
import util.Csrf;
import util.SessionUtil;

public class GenericLoginAction{
	

	public String iniciarSesionGeneric(String usuario,String clave,UsuarioDAO usuarioDAO, SessionMap<String, Object>  sessionMap){
		Usuario user= usuarioDAO.existe(usuario,clave);
		if (user != null) {
			if (user.getActivo()){
				sessionMap.put("usrLogin", user);
				//Esto genera los id unicos y randoms que el ususario va a usar en esta sesion
				Csrf.getTokenId();
				Csrf.getTokenValue();
				return "success";
			}
			else{
				return "desactivada";
			}
		} else {
			return "datosIncorrectos";
		}
	}	
	public String cerrarSesionGeneric( SessionMap<String, Object>  sessionMap){		
		sessionMap.invalidate();
		return "success";			
	}
	public String validarSesionGeneric(String uuid){
		Usuario user = FactoryDAO.getUsuarioDAO().encontrarPorUUID(uuid);
		if (user != null) {			
			return "success";
		} else {
			return "datosIncorrectos";
		}
	}	
}
