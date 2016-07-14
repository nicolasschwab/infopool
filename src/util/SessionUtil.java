package util;

import javax.servlet.http.HttpSession;

import model.Usuario;

import org.apache.struts2.ServletActionContext;

import actionsRest.MobileLoginAction;
import implementacionesDAO.FactoryDAO;

public class SessionUtil {

	public static boolean checkLogin(){       
        HttpSession session = ServletActionContext.getRequest().getSession(false);        
        if(session == null || session.getAttribute("usrLogin") == null){
            return false;
        }
        return true;
	}
	
	public static boolean checkLoginMobile(String uuid){
		if(uuid==null){
			return false;
		}		
        HttpSession session = ServletActionContext.getRequest().getSession(false);
        if(session != null && session.getAttribute("usrLogin") != null){
        	return ((Usuario)session.getAttribute("usrLogin")).getUuid().equals(uuid);
        }else{
        	Usuario usr=FactoryDAO.getUsuarioDAO().encontrarPorUUID(uuid);
        	if(usr!=null){
        		session = ServletActionContext.getRequest().getSession(true);
        		session.setAttribute("usrLogin", usr);
        		return true;
        	}else{
        		return false;
        	}
        }
	}
	
	public static Usuario getUsuario(){		
		HttpSession session = ServletActionContext.getRequest().getSession(false);
		return((Usuario)session.getAttribute("usrLogin"));
	}
	
	public static String getTokenId(){
		HttpSession session = ServletActionContext.getRequest().getSession(false);
		if(session.getAttribute("tokenId")==null){
			return null;
		}
		return String.valueOf(session.getAttribute("tokenId"));
	}
	
	public static String getTokenValue(){
		HttpSession session = ServletActionContext.getRequest().getSession(false);
		if(session.getAttribute("tokenValue")==null){
			return null;
		}
		return String.valueOf(session.getAttribute("tokenValue"));
	}
	
	public static HttpSession getSession(){
		return ServletActionContext.getRequest().getSession(false);
	}
	
}