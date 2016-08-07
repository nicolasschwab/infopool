package actionsRest;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.opensymphony.xwork2.ModelDriven;

import actionsGeneric.GenericViajeAction;
import dto.GenericDto;
import dto.ViajeDto;
import dto.ViajeroDto;
import model.Evento;
import model.Viaje;
import model.Viajero;
import util.Dozer;
import util.Generics;
import util.SessionUtil;
import util.Validacion;

public class MobileViajeAction implements ModelDriven<GenericDto> {

	private List<Viaje> listaBusquedaViajes;
	private List<ViajeDto> listaBusquedaViajesDto;
	
	private String id;
	private String direccionOrigen;
	private String direccionDestino;
	private String fechaViaje;
	private String uuid;
	
	private GenericDto respuesta;
	
	
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public List<Viaje> getListaBusquedaViajes() {
		return listaBusquedaViajes;
	}

	public void setListaBusquedaViajes(List<Viaje> listaBusquedaViajes) {
		this.listaBusquedaViajes = listaBusquedaViajes;
	}

	public List<ViajeDto> getListaBusquedaViajesDto() {
		if(listaBusquedaViajesDto==null){
			listaBusquedaViajesDto= new ArrayList<ViajeDto>();
		}
		return listaBusquedaViajesDto;
	}

	public void setListaBusquedaViajesDto(List<ViajeDto> listaBusquedaViajesDto) {
		this.listaBusquedaViajesDto = listaBusquedaViajesDto;
	}
	
	public String getDireccionOrigen() {
		if(this.direccionOrigen==null){
			this.direccionOrigen="";
		}
		return direccionOrigen;
	}

	public void setDireccionOrigen(String direccionOrigen) {
		this.direccionOrigen = direccionOrigen;
	}

	public String getDireccionDestino() {
		if(this.direccionDestino==null){
			this.direccionDestino="";
		}
		return direccionDestino;
	}

	public void setDireccionDestino(String direccionDestino) {
		this.direccionDestino = direccionDestino;
	}

	public String getFechaViaje() {
		return fechaViaje;
	}

	public void setFechaViaje(String fechaViaje) {
		this.fechaViaje = fechaViaje;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Action("/viaje/buscar")
	public void busquedaViaje() throws ParseException{
		if(SessionUtil.checkLoginMobile(this.getUuid())){		
			this.setListaBusquedaViajes( Generics.getGenericViajeAction().busquedaViaje(this.getDireccionOrigen(),this.getDireccionDestino(),this.getFechaViaje()));
			if(this.getListaBusquedaViajes()==null || this.getListaBusquedaViajes().isEmpty()){
				this.fail("No hay viajes con esas caracteristicas");				
			}else{
				for(Viaje v: this.getListaBusquedaViajes()){
					ViajeDto viajedto= new ViajeDto();
					Dozer.getMapper().map(v, viajedto);
					this.success("",viajedto);
				}
			}
		}
	}
	
	@Action("/viaje/detalle")
	public void detalle(){
		if(SessionUtil.checkLoginMobile(this.getUuid())){
			if(Validacion.stringNoVacio(this.getId())){
				Viaje viaje=Generics.getGenericViajeAction().detalleViaje(this.getId());
				if(viaje!=null){				
					this.success("",Dozer.getMapper().map(viaje, ViajeDto.class));
				}else{
					this.fail("El viaje no existe");
				}
			}else{
				this.fail("");
			}
		}
	}
	
	@Action("/viaje/listar/conductor")
	public void conductor(){
		if(SessionUtil.checkLoginMobile(this.getUuid())){
			List<Viaje> viajes=Generics.getGenericViajeAction().viajesUsuarioConductor();
			if(viajes!=null && !viajes.isEmpty()){		
				for(Viaje viaje: viajes){
					this.success("",Dozer.getMapper().map(viaje, ViajeDto.class));
				}
			}else{
				this.fail("No sos conductor de ningun viaje");
			}
		}
	}
	
	@Action("/viaje/listar/pasajero")
	public void pasajero(){
		if(SessionUtil.checkLoginMobile(this.getUuid())){
			List<Viaje> viajes=Generics.getGenericViajeAction().viajesUsuarioViajero();
			if( viajes!=null && !viajes.isEmpty()){
				for(Viaje viaje: viajes){
					this.success("",Dozer.getMapper().map(viaje, ViajeDto.class));
				}
			}else{
				this.fail("No sos pasajero en ningun viaje");
			}
		}
	}	
	
	private void success(String mensaje, ViajeDto viaje){
		this.getModel().setEstado("1");
		this.getModel().setMensaje(mensaje);
		this.getModel().agregarUnicoResutado(viaje);
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
