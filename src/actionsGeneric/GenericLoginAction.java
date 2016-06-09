package actionsGeneric;

import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import interfacesDAO.UsuarioDAO;
import model.Usuario;
import util.Csrf;
import util.SessionUtil;

public class GenericLoginAction{
	

	public static String iniciarSesionGeneric(String usuario,String clave,UsuarioDAO usuarioDAO, SessionMap<String, Object>  sessionMap){
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
	public static String cerrarSesionGeneric( SessionMap<String, Object>  sessionMap){
		if(SessionUtil.checkLogin()){
			sessionMap.invalidate();
			return "success";
		}else{
			return "sinPermisos";
		}	
	}	
}
