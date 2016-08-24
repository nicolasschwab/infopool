package actionsRest;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Convert;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

import dto.GenericDto;
import dto.SolicitudViajeDto;
import dto.ViajeDto;
import implementacionesDAO.FactoryDAO;
import model.FrecuenciaViaje;
import model.SolicitudViaje;
import model.Viaje;
import util.Dozer;
import util.Generics;
import util.SessionUtil;

public class MobileSolictudAction implements ModelDriven<GenericDto>{

	private int id;
	private int idFrecuenciaViaje;	
	private List<SolicitudViajeDto> listaSolicitudesViajeDto;
	private String uuid;
	private GenericDto respuesta;
	private String puntoEncuentro;
	
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
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
	public String getPuntoEncuentro(){
		return puntoEncuentro;
	}
	public void setPuntoEncuentro(String puntoEncuentro){
		this.puntoEncuentro = puntoEncuentro;
	}

	@Action("/solicitud/nueva")
	public void RegistroSolicitudViaje() throws Exception{
		if(SessionUtil.checkLoginMobile(this.getUuid())){
			String laRespuesta=Generics.getGenericSolicitudAction().RegistroSolicitudViaje(String.valueOf(this.getIdFrecuenciaViaje()),this.getPuntoEncuentro());
			if(laRespuesta.equals("INPUT")){
				this.fail("Ya solicitaste viajar en esta frecuencia!");
			}else if (laRespuesta.equals("sinPermisos")){
				this.fail("Ya perteneces a esta frecuencia!");
			}else {
				this.success("Solicitud enviada!", null);
			}
		}
	}
	
	@Action("/solicitud/listar/conductor")
	public void SolicitudesConductor() throws Exception{
		if(SessionUtil.checkLoginMobile(this.getUuid())){
			List<Viaje> viajes= FactoryDAO.getViajeDAO().listarViajesConductor(SessionUtil.getUsuario());
			//Agrego todas las solicitudes de las frecuencias de los viajes de los cuales sos conductor
			for(Viaje viaje: viajes){
				for(FrecuenciaViaje frecuencia: viaje.getFrecuencias()){
					for(SolicitudViaje solicitud: frecuencia.getSolicitudesViaje()){
						this.success("", Dozer.getMapper().map(solicitud, SolicitudViajeDto.class));
					}
				}
			}
			//Si no se agrego nada, significa que no habia solicitudes
			if(this.getModel().getResultado().isEmpty()){
				this.fail("No hay solicitudes en tus viajes como conductor");
			}
		}
	}
	
	@Action("/solicitud/listar/pasajero")
	public void SolicitudesPasajero(){
		if(SessionUtil.checkLoginMobile(this.getUuid())){
			List<SolicitudViaje> solicitudes=FactoryDAO.getSolicitudViajeDAO().encontrarPorSolicitante((model.Viajero)SessionUtil.getUsuario());
			for(SolicitudViaje solicitud: solicitudes){
				this.success("", Dozer.getMapper().map(solicitud, SolicitudViajeDto.class));
			}
			if(this.getModel().getResultado().isEmpty()){
				this.fail("No solicitaste unirte a ningun viaje");
			}
			
		}
	}
	
	@Action("/solicitud/aceptar")
	public void AceptarSolicitudViaje() throws Exception{
		if(SessionUtil.checkLoginMobile(this.getUuid())){
			SolicitudViaje solicitud= FactoryDAO.getSolicitudViajeDAO().encontrar(this.getId());
			String respuesta= Generics.getGenericSolicitudAction().AceptarSolicitudViaje(solicitud);
			if(respuesta=="SUCCESS"){
				this.success("Se acepto la solicitud!",Dozer.getMapper().map(solicitud, SolicitudViajeDto.class));
			}else if(respuesta=="sinPermisos"){
				this.fail("No puede para aceptar la solicitud!");
			}else{
				this.fail("No hay mas cupos en el viaje!");
			}
		}
	}
	
	@Action("/solicitud/rechazar")
	public void RechazarSolicitudViaje() throws NumberFormatException, Exception{
		if(SessionUtil.checkLoginMobile(this.getUuid())){			
			SolicitudViaje solicitudViaje = FactoryDAO.getSolicitudViajeDAO().encontrar(this.getId());
			String respuesta=Generics.getGenericSolicitudAction().RechazarSolicitudViaje(solicitudViaje);
			if(respuesta=="SUCCESS"){
				this.success("Se rechazo la solicitud!",Dozer.getMapper().map(solicitudViaje, SolicitudViajeDto.class));
			}else{
				this.fail("No puede para rechazar la solicitud!");
			}
		}
	}
	
	@Action("/solicitud/cancelar")
	public void CancelarSolicitudViaje() throws Exception{
		if(SessionUtil.checkLoginMobile(this.getUuid())){
			SolicitudViaje solicitudViaje = FactoryDAO.getSolicitudViajeDAO().encontrar(this.getId());
			String respuesta=Generics.getGenericSolicitudAction().CancelarSolicitudViaje(solicitudViaje);
			if(respuesta=="SUCCESS"){
				this.success("Se cancelo la solicitud!",Dozer.getMapper().map(solicitudViaje, SolicitudViajeDto.class));
			}else{
				this.fail("No puede cancelar la solicitud!");
			}
		}		
	}

	@Action("/solicitud/cancelarFrecuencia")
	public void CancelarSolicitudFrecuenciaViaje() throws Exception{
		if(SessionUtil.checkLoginMobile(this.getUuid())){
			String respuesta = null;
			FrecuenciaViaje frecuenciaViaje = FactoryDAO.getFrecuenciaViajeDAO().encontrar(idFrecuenciaViaje);
			for(SolicitudViaje solicitud: frecuenciaViaje.getSolicitudesViaje()){			
				if ((solicitud.getViajero().equals(SessionUtil.getUsuario())) && (solicitud.getEstadoSolicitud().equals("ACEPTADA") || (solicitud.getEstadoSolicitud().equals("PENDIENTE")))){
					respuesta=Generics.getGenericSolicitudAction().CancelarSolicitudViaje(solicitud);
					if(respuesta=="SUCCESS"){
						this.success("Se cancelo la solicitud!",Dozer.getMapper().map(solicitud, SolicitudViajeDto.class));
					}
				}
			}
			if(respuesta==null){
				this.fail("No puede cancelar la solicitud!");
			}
		}		
	}
	
	private void success(String mensaje, SolicitudViajeDto solicitud){
		this.getModel().setEstado("1");
		this.getModel().setMensaje(mensaje);
		this.getModel().agregarUnicoResutado(solicitud);
	}
	private void fail(String mensaje){
		this.getModel().setEstado("2");
		this.getModel().setMensaje(mensaje);
		this.getModel().setResultado(null);
	}
	
	@Override
	public GenericDto getModel() {
		if(respuesta==null){
			respuesta= new GenericDto<SolicitudViajeDto>();
		}
		return respuesta;
	}
}
