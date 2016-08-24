package actionsRest;

import implementacionesDAO.FactoryDAO;

import java.util.ArrayList;
import java.util.List;

import model.FrecuenciaViaje;
import model.Viaje;
import model.Viajero;

import org.apache.struts2.convention.annotation.Action;

import util.Dozer;
import util.Generics;
import util.SessionUtil;
import util.Validacion;

import com.opensymphony.xwork2.ModelDriven;

import dto.FrecuenciaViajeDto;
import dto.GenericDto;

public class MobileFrecuenciaAction implements ModelDriven<GenericDto> {

	private GenericDto respuesta;
	
	private String id;
	private String viajeId;
	private String uuid;
	
	
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
	public String getViajeId() {
		return viajeId;
	}
	public void setViajeId(String viajeId) {
		this.viajeId = viajeId;
	}
	
	@Action("/frecuencia/detalle")
	public void detalle() throws Exception{
		if(SessionUtil.checkLoginMobile(this.getUuid())){
			if(Validacion.stringNoVacio(this.getId())){
				FrecuenciaViaje frecuencia = Generics.getGenericFrecuenciaViajeAction().detalleFrecuenciaViaje(Integer.parseInt(this.getId()));
				if(frecuencia!=null){				
					this.success("",Dozer.getMapper().map(frecuencia, FrecuenciaViajeDto.class));
				}else{
					this.fail("El viajero no existe");
				}
			}else{
				this.fail("");
			}
		}
	}
	
	@Action("/frecuencia/frecuenciasViaje")
	public void frecuencias(){
		if(SessionUtil.checkLoginMobile(this.getUuid())){			
			Viaje viaje=Generics.getGenericViajeAction().detalleViaje(this.getViajeId());
			if(viaje!=null){				
				for(FrecuenciaViaje frecuencia:viaje.getFrecuencias()){
					this.success("",Dozer.getMapper().map(frecuencia, FrecuenciaViajeDto.class));
				}				
			}else{
				this.fail("El viaje no existe");
			}
		}
	}

	@Action("/frecuencia/pasajeroFrecuencia")
	public void pasajeroFrecuencia() throws Exception{
		if(SessionUtil.checkLoginMobile(this.getUuid())){			
			FrecuenciaViaje frecuencia = Generics.getGenericFrecuenciaViajeAction().detalleFrecuenciaViaje(Integer.parseInt(this.getId()));
			if(frecuencia!=null){
				String message = "Viajero";				
				if (frecuencia.esPasajero(SessionUtil.getUsuario())){
					message = "Pasajero";
				}
				this.success(message,Dozer.getMapper().map(frecuencia, FrecuenciaViajeDto.class));
			}else{
				this.fail("La frecuencia no existe");
			}
		}
	}
	
	private void success(String mensaje, FrecuenciaViajeDto frecuencia){
		this.getModel().setEstado("1");
		this.getModel().setMensaje(mensaje);
		this.getModel().agregarUnicoResutado(frecuencia);
	}
	
	private void fail(String mensaje){
		this.getModel().setEstado("2");
		this.getModel().setMensaje(mensaje);
		this.getModel().setResultado(null);
	}
	
	@Override
	public GenericDto getModel() {
		if(respuesta==null){
			respuesta=new GenericDto<FrecuenciaViajeDto>();
		}
		return respuesta;
	}
	
}
