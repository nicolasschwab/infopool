package actionsRest;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;

import com.opensymphony.xwork2.ModelDriven;

import dto.ConversacionDto;
import dto.GenericDto;
import implementacionesDAO.FactoryDAO;
import model.Conversacion;
import model.Viajero;
import util.Dozer;
import util.Generics;
import util.SessionUtil;
import util.Validacion;

public class MobileConversacionAction implements ModelDriven<GenericDto>{
	
	private int id;
	private String receptorID;
	private int viajeId;
	private String detalle;
	private String asunto;
	private ConversacionDto conversaciondto;
	private String uuid;
	private GenericDto respuesta;

	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
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
		if(SessionUtil.checkLoginMobile(this.getUuid())){
			if(Validacion.stringNoVacio(receptorID) && Validacion.stringNoVacio(asunto) && Validacion.stringNoVacio(detalle) && Validacion.intNoCeroPositivo(viajeId)){
				this.success("Se creo la conversacion!",Generics.getGenericConversacionAction().crearConversacion(receptorID, viajeId, (Viajero)SessionUtil.getUsuario(), detalle, asunto));
			}else{
				this.fail("Debe completar todos los campos!");
			}
			
		}
	}
	@Action("/conversacion/detalle")
	public void detalle(){
		if(SessionUtil.checkLoginMobile(this.getUuid())){
			if(Validacion.intNoCeroPositivo(this.getId())){			
				Conversacion conversacion=Generics.getGenericConversacionAction().detalle(this.getId());
				if(conversacion!=null){
					this.success("",conversacion);
				}else{
					this.fail("No perteneces a esta conversacion!");
				}
			}
		}
	}
	@Action("/conversacion/responder")
	public void responderMensaje() throws Exception{
		if(SessionUtil.checkLoginMobile(this.getUuid())){
			Conversacion conversacion=Generics.getGenericConversacionAction().responderMensaje(this.getId(), this.getDetalle());
			if(conversacion!=null){
				this.success("",conversacion);
			}else{
				this.fail("No se pudo enviar el mensaje!");
			}
		}
	}

	@Action("/conversacion/listar")
	public void listar(){
		if(SessionUtil.checkLoginMobile(this.getUuid())){
			List<Conversacion> conversaciones=FactoryDAO.getConversacionDAO().listar(SessionUtil.getUsuario());
			for(Conversacion conversacion: conversaciones){
				this.success("", conversacion);
			}
			if(conversaciones.isEmpty()){
				this.fail("No tienes conversaciones");
			}
		}
	}
	
	private void success(String mensaje, Conversacion conversacion){
		this.getModel().setEstado("1");
		this.getModel().setMensaje(mensaje);
		this.mapear(conversacion);
	}
	private void fail(String mensaje){
		this.getModel().setEstado("2");
		this.getModel().setMensaje(mensaje);
		this.getModel().setResultado(null);
	}
	private void mapear(Conversacion conversacion){
		this.getModel().agregarUnicoResutado(Dozer.getMapper().map(conversacion, ConversacionDto.class));
	}
	
	@Override
	public GenericDto getModel() {
		if(respuesta==null){
			respuesta=new GenericDto<ConversacionDto>();
		}
		return respuesta;
	}
	
	public void setModel(GenericDto respuesta){
		this.respuesta=respuesta;
	}
	
}
