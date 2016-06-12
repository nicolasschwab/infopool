package actionsRest;

import org.apache.struts2.convention.annotation.Action;

import util.Generics;
import util.SessionUtil;

@Action("/solicitud")
public class MobileSolictudAction{

	private String idFrecuenciaViaje;	
	
	public String getIdFrecuenciaViaje() {
		return idFrecuenciaViaje;
	}
	
	public void setIdFrecuenciaViaje(String idFrecuenciaViaje) {
		this.idFrecuenciaViaje = idFrecuenciaViaje;
	}

	@Action("/nueva")
	public void RegistroSolicitudViaje() throws Exception{
		if(SessionUtil.checkLogin()){
			Generics.getGenericSolicitudAction().RegistroSolicitudViaje(this.getIdFrecuenciaViaje());
		}
	}
	
}
