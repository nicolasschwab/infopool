package actionsRest;

import model.Viaje;
import model.Viajero;

import org.apache.struts2.convention.annotation.Action;

import util.Dozer;
import util.Generics;
import util.SessionUtil;
import util.Validacion;

import com.opensymphony.xwork2.ModelDriven;

import dto.GenericDto;
import dto.ViajeDto;
import dto.ViajeroDto;

public class MobileViajeroAction implements ModelDriven<GenericDto>{
	
	private GenericDto respuesta;
	
	private String id;
	private String uuid;
	
	
	public GenericDto getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(GenericDto respuesta) {
		this.respuesta = respuesta;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	@Action("/viajero/detalle")
	public void detalle() throws Exception{
		if(SessionUtil.checkLoginMobile(this.getUuid())){
			if(Validacion.stringNoVacio(this.getId())){
				Viajero viajero = Generics.getGenericViajeroAction().detalleViajero(this.getId());
				if(viajero!=null){				
					this.success("", Dozer.getMapper().map(viajero, ViajeroDto.class));
				}else{
					this.fail("El viajero no existe");
				}
			}else{
				this.fail("");
			}
		}
	}
	
	private void success(String mensaje, ViajeroDto viajero){
		this.getModel().setEstado("1");
		this.getModel().setMensaje(mensaje);
		this.getModel().agregarUnicoResutado(viajero);
	}
	
	private void fail(String mensaje){
		this.getModel().setEstado("2");
		this.getModel().setMensaje(mensaje);
		this.getModel().setResultado(null);
	}
	
	@Override
	public GenericDto getModel() {
		if(respuesta==null){
			respuesta= new GenericDto<ViajeDto>();
		}
		return respuesta;
	}

}
