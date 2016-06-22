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
	
	int id;
	String receptorID;
	int viajeId;
	String detalle;
	String asunto;
	ConversacionDto conversaciondto;

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
			this.mapear(Generics.getGenericConversacionAction().crearConversacion(receptorID, viajeId, (Viajero)SessionUtil.getUsuario(), detalle, asunto));
		}
	}
	@Action("/conversacion/detalle")
	public void detalle(){
		if(SessionUtil.checkLogin()){
			Conversacion conversacion=Generics.getGenericConversacionAction().detalle(this.getId());
			if(conversacion!=null){
				this.mapear(conversacion);
			}			
		}
	}
	@Action("/conversacion/responder")
	public void responderMensaje() throws Exception{
		if(SessionUtil.checkLogin()){
			Generics.getGenericConversacionAction().responderMensaje(this.getId(), this.getDetalle());
		}
	}
	
	private void mapear(Conversacion conversacion){
		this.setModel(Dozer.getMapper().map(conversacion, ConversacionDto.class));
	}
	
	@Override
	public ConversacionDto getModel() {
		return conversaciondto;
	}
	
	public void setModel(ConversacionDto conversaciondto){
		this.conversaciondto=conversaciondto;
	}
	
}
