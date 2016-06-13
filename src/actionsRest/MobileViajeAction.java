package actionsRest;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;

import com.opensymphony.xwork2.ModelDriven;

import actionsGeneric.GenericViajeAction;
import dto.ViajeDto;
import dto.ViajeroDto;
import model.Evento;
import model.Viaje;
import util.Dozer;
import util.Generics;
import util.SessionUtil;
import util.Validacion;
@Action("/viaje")
public class MobileViajeAction implements ModelDriven<List<ViajeDto>> {

	private List<Viaje> listaBusquedaViajes;
	private List<ViajeDto> listaBusquedaViajesDto;
	
	private String id;
	private String direccionOrigen;
	private String direccionDestino;
	private String fechaViaje;
	
	
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

	@Action("/buscar")
	public void busquedaViaje() throws ParseException{
		if(SessionUtil.checkLogin()){		
			this.setListaBusquedaViajes( Generics.getGenericViajeAction().busquedaViaje(this.getDireccionOrigen(),this.getDireccionDestino(),this.getFechaViaje()));
			if(!this.getListaBusquedaViajes().isEmpty()){
				for(Viaje v: this.getListaBusquedaViajes()){
					ViajeDto viajedto= new ViajeDto();
					Dozer.getMapper().map(v, viajedto);
					this.getModel().add(viajedto);
				}
			}
		}
	}
	
	@Action("/detalle")
	public void detalle(){
		if(SessionUtil.checkLogin()){
			if(Validacion.stringNoVacio(this.getId())){			
				Viaje viaje=Generics.getGenericViajeAction().detalleViaje(this.getId());
				if(viaje!=null){				
					this.getModel().add(Dozer.getMapper().map(viaje, ViajeDto.class));
				}
			}
		}
	}
	
	@Action("/listar/misViajes")
	public void misViajes(){
		if(SessionUtil.checkLogin()){			
			for(Viaje viaje: Generics.getGenericViajeAction().viajesUsuarioConductor()){
				this.getModel().add(Dozer.getMapper().map(viaje, ViajeDto.class));
			}
			//Agrego un null para poder diferenciar entre viajes como conductor y viajes como pasajero
			this.getModel().add(null);
			for(Viaje viaje: Generics.getGenericViajeAction().viajesUsuarioViajero()){
				this.getModel().add(Dozer.getMapper().map(viaje, ViajeDto.class));
			}
		}
	}
	

	@Override
	public List<ViajeDto> getModel() {
		if(listaBusquedaViajesDto==null){
			listaBusquedaViajesDto= new ArrayList<ViajeDto>();
		}
		return listaBusquedaViajesDto;
	}
	
	
}
