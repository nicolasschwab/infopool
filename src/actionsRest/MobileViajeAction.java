package actionsRest;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;

import com.opensymphony.xwork2.ModelDriven;

import actionsGeneric.GenericViajeAction;
import dto.ViajeDto;
import dto.ViajeroDto;
import model.Viaje;
import util.Dozer;
import util.SessionUtil;
@Action("/viaje")
public class MobileViajeAction implements ModelDriven<List<ViajeDto>> {

	private List<Viaje> listaBusquedaViajes;
	private List<ViajeDto> listaBusquedaViajesDto;
	
	
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
	
	@Action("/buscar")
	public void busquedaViaje(){
		if(SessionUtil.checkLogin()){		
			this.setListaBusquedaViajes( new GenericViajeAction().busquedaViaje());
			if(this.getListaBusquedaViajes() !=null){
				for(Viaje v: this.getListaBusquedaViajes()){
					ViajeDto viajedto= new ViajeDto();
					Dozer.getMapper().map(v, viajedto);
					this.getModel().add(viajedto);
				}
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
