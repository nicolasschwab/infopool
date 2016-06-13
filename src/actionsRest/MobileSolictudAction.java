package actionsRest;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Convert;

import org.apache.struts2.convention.annotation.Action;

import com.opensymphony.xwork2.ModelDriven;

import dto.SolicitudViajeDto;
import dto.ViajeDto;
import model.SolicitudViaje;
import util.Dozer;
import util.Generics;
import util.SessionUtil;

@Action("/solicitud")
public class MobileSolictudAction implements ModelDriven<List<SolicitudViajeDto>>{

	private int idFrecuenciaViaje;	
	private List<SolicitudViajeDto> listaSolicitudesViajeDto;
	
	public int getIdFrecuenciaViaje() {
		return idFrecuenciaViaje;
	}
	
	public void setIdFrecuenciaViaje(int idFrecuenciaViaje) {
		this.idFrecuenciaViaje = idFrecuenciaViaje;
	}

	@Action("/nueva")
	public void RegistroSolicitudViaje() throws Exception{
		if(SessionUtil.checkLogin()){
			Generics.getGenericSolicitudAction().RegistroSolicitudViaje(String.valueOf(this.getIdFrecuenciaViaje()));
		}
	}
	
	@Action("/listar")
	public void SolicitudesFrecuenciaViaje() throws Exception{
		if(SessionUtil.checkLogin()){
			List<SolicitudViaje> solicitudes= Generics.getGenericSolicitudAction().SolicitudesFrecuenciaViaje(idFrecuenciaViaje);
			for(SolicitudViaje solicitud: solicitudes){
				this.getModel().add(Dozer.getMapper().map(solicitud, SolicitudViajeDto.class));
			}
		}
	}
	
	
	@Override
	public List<SolicitudViajeDto> getModel() {
		if(listaSolicitudesViajeDto==null){
			listaSolicitudesViajeDto= new ArrayList<SolicitudViajeDto>();
		}
		return listaSolicitudesViajeDto;
	}
}
