package actionsRest;

import org.apache.struts2.convention.annotation.Action;

import com.opensymphony.xwork2.ModelDriven;

import dto.ConversacionDto;
import model.Conversacion;
import model.Viajero;
import util.Dozer;
import util.Generics;
import util.SessionUtil;

public class MobileConversacionAction implements ModelDriven<ConversacionDto>{
	
	String receptorID;
	int viajeId;
	String detalle;
	String asunto;
	ConversacionDto conversaciondto;

	public String getReceptorID() {
		return receptorID;
	}
	public void setReceptorID(String receptorID) {
		this.receptorID = receptorID;
	}
	public int getViajeId() {
		return viajeId;
	}
	public void setViajeId(int viajeId) {
		this.viajeId = viajeId;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	@Action("/conversacion/crear")
	public void crearConversacion() throws NumberFormatException, Exception{
		if(SessionUtil.checkLogin()){
			Conversacion conversacion=Generics.getGenericConversacionAction().crearConversacion(receptorID, viajeId, (Viajero)SessionUtil.getUsuario(), detalle, asunto);
			this.setModel(Dozer.getMapper().map(conversacion, ConversacionDto.class));
		}
	}
	@Override
	public ConversacionDto getModel() {
		return conversaciondto;
	}
	
	public void setModel(ConversacionDto conversaciondto){
		this.conversaciondto=conversaciondto;
	}
	
}
