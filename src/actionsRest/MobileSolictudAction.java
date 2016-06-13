package actionsRest;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Convert;

import org.apache.struts2.convention.annotation.Action;

import com.opensymphony.xwork2.ModelDriven;

import dto.SolicitudViajeDto;
import dto.ViajeDto;
import implementacionesDAO.FactoryDAO;
import model.SolicitudViaje;
import util.Dozer;
import util.Generics;
import util.SessionUtil;

@Action("/solicitud")
public class MobileSolictudAction implements ModelDriven<List<SolicitudViajeDto>>{

	private int id;
	private int idFrecuenciaViaje;	
	private List<SolicitudViajeDto> listaSolicitudesViajeDto;
	
	public int getIdFrecuenciaViaje() {
		return idFrecuenciaViaje;
	}	
	public void setIdFrecuenciaViaje(int idFrecuenciaViaje) {
		this.idFrecuenciaViaje = idFrecuenciaViaje;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	
	@Action("/aceptar")
	public void AceptarSolicitudViaje() throws Exception{
		if(SessionUtil.checkLogin()){
			SolicitudViaje solicitud= FactoryDAO.getSolicitudViajeDAO().encontrar(this.getId());
			String respuesta= Generics.getGenericSolicitudAction().AceptarSolicitudViaje(solicitud);
			if(respuesta=="SUCCESS"){
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
