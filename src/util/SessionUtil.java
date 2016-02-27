package util;

import javax.servlet.http.HttpSession;

import model.Usuario;

import org.apache.struts2.ServletActionContext;

public class SessionUtil {

	public static boolean checkLogin(){        
        HttpSession session = ServletActionContext.getRequest().getSession(false);        
        if(session == null || session.getAttribute("usrLogin") == null){
            return false;
        }
        return true;
	}	
	
	public static Usuario getUsuario(){		
		HttpSession session = ServletActionContext.getRequest().getSession(false);
		return((Usuario)session.getAttribute("usrLogin"));
	}
	
}