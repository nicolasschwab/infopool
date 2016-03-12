package actions;

import implementacionesDAO.FrecuenciaViajeDAOjpa;
import implementacionesDAO.SolicitudViajeDAOjpa;
import implementacionesDAO.ViajeDAOjpa;
import implementacionesDAO.ViajeroDAOjpa;
import interfacesDAO.FrecuenciaViajeDAO;
import interfacesDAO.SolicitudViajeDAO;
import interfacesDAO.ViajeDAO;
import interfacesDAO.ViajeroDAO;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.EstadoSolicitud;
import model.FrecuenciaViaje;
import model.SolicitudViaje;
import model.Viaje;
import model.Viajero;

import org.apache.struts2.ServletActionContext;

import util.SessionUtil;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class SolicitudViajeAction extends ActionSupport{

	private static final long serialVersionUID = 1L;	
	private int id;
	
	/* DATOS FORMULARIO */	
	private int idViaje;
	private int idFrecuenciaViaje;
	
	/* */
	private Viaje viaje;
	private Viajero viajero;
	private FrecuenciaViaje frecuenciaViaje;
	private SolicitudViaje solicitudViaje;
	private ViajeDAO viajeDAO;
	private ViajeroDAO viajeroDAO;
	private FrecuenciaViajeDAO frecuenciaViajeDAO;
	private SolicitudViajeDAO solicitudViajeDAO;
	
	/* DATOS VISTAS */
	private List<SolicitudViaje> listaSolicitudes = null;	
	private boolean tieneSolicitudPendiente;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdViaje() {
		return idViaje;
	}
	public void setIdViaje(int idViaje) {
		this.idViaje = idViaje;
	}
	public int getIdFrecuenciaViaje() {
		return idFrecuenciaViaje;
	}
	public void setIdFrecuenciaViaje(int idFrecuenciaViaje) {
		this.idFrecuenciaViaje = idFrecuenciaViaje;
	}
	
	public Viaje getViaje() {
		return viaje;
	}
	public void setViaje(Viaje viaje) {
		this.viaje = viaje;
	}
	public Viajero getViajero() {
		return viajero;
	}
	public void setViajero(Viajero viajero) {
		this.viajero = viajero;
	}
	public FrecuenciaViaje getFrecuenciaViaje() {
		return frecuenciaViaje;
	}
	public void setFrecuenciaViaje(FrecuenciaViaje frecuenciaViaje) {
		this.frecuenciaViaje = frecuenciaViaje;
	}
	public SolicitudViaje getSolicitudViaje() {
		return solicitudViaje;
	}
	public void setSolicitudViaje(SolicitudViaje solicitudViaje) {
		this.solicitudViaje = solicitudViaje;
	}
	public ViajeDAO getViajeDAO() {
		return viajeDAO;
	}
	public void setViajeDAO(ViajeDAO viajeDAO) {
		this.viajeDAO = viajeDAO;
	}
	public ViajeroDAO getViajeroDAO() {
		return viajeroDAO;
	}
	public void setViajeroDAO(ViajeroDAO viajeroDAO) {
		this.viajeroDAO = viajeroDAO;
	}
	public FrecuenciaViajeDAO getFrecuenciaViajeDAO() {
		return frecuenciaViajeDAO;
	}
	public void setFrecuenciaViajeDAO(FrecuenciaViajeDAO frecuenciaViajeDAO) {
		this.frecuenciaViajeDAO = frecuenciaViajeDAO;
	}
	public SolicitudViajeDAO getSolicitudViajeDAO() {
		return solicitudViajeDAO;
	}
	public void setSolicitudViajeDAO(SolicitudViajeDAO solicitudViajeDAO) {
		this.solicitudViajeDAO = solicitudViajeDAO;
	}
	
	public List<SolicitudViaje> getListaSolicitudes() {
		return listaSolicitudes;
	}
	public void setListaSolicitudes(List<SolicitudViaje> listaSolicitudes) {
		this.listaSolicitudes = listaSolicitudes;
	}
	public boolean getTieneSolicitudPendiente() {
		return tieneSolicitudPendiente;
	}
	public void setTieneSolicitudPendiente(boolean tieneSolicitudPendiente) {
		this.tieneSolicitudPendiente = tieneSolicitudPendiente;
	}
		
	public String RegistroSolicitudViaje() throws Exception{		
		if (SessionUtil.checkLogin()) {
			viajero = (Viajero)SessionUtil.getUsuario();
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			idFrecuenciaViaje = Integer.parseInt(request.getParameter("idFrecuenciaViaje"));
			frecuenciaViaje = ((FrecuenciaViajeDAOjpa)frecuenciaViajeDAO).encontrar(idFrecuenciaViaje);
			viaje = frecuenciaViaje.getViaje();			
			if (!viaje.esConductor(viajero)){
				idViaje = viaje.getId();				
				tieneSolicitudPendiente = solicitudViajeDAO.tieneSolicitudEstado(viajero,frecuenciaViaje,EstadoSolicitud.PENDIENTE);
				if(!tieneSolicitudPendiente){
					Date fechaInicioSolicitud = new Date();					
					solicitudViaje = new SolicitudViaje(fechaInicioSolicitud,null,EstadoSolicitud.PENDIENTE,viajero,frecuenciaViaje,null);					
					solicitudViajeDAO.registrar(solicitudViaje);					
					return SUCCESS;				
				}else{					
					addFieldError("loginError", "Usted ya solicito participar en este viaje");
					return INPUT;
				}
			}
			else{
				return "sinPermisos";
			}
		}
		else{
			return "login";
		}	
	}
	
	public String AceptarSolicitudViaje() throws Exception{		
		if(SessionUtil.checkLogin()){
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);			
			solicitudViaje = solicitudViajeDAO.encontrar(Integer.parseInt(request.getParameter("id")));			
			frecuenciaViaje = ((FrecuenciaViajeDAOjpa)frecuenciaViajeDAO).encontrar(solicitudViaje.getFrecuenciaViaje().getId());
			viaje = frecuenciaViaje.getViaje();
			idViaje = viaje.getId();
			 
			if (frecuenciaViaje.getAsientosDisponibles() > 0){				
				solicitudViaje.setEstadoSolicitud(EstadoSolicitud.ACEPTADA);
				solicitudViaje.setFechaFinSolicitud(new Date());
				solicitudViajeDAO.modificar(solicitudViaje);
				
				viajero = ((ViajeroDAOjpa)viajeroDAO).encontrar(solicitudViaje.getViajero().getId());
				if (((FrecuenciaViajeDAOjpa)frecuenciaViajeDAO).cantidadFrecuenciasEnViaje(viajero,viaje) == 0){
					ViajeAction.agregarUsuarioAForo(idViaje,viajero.getId());
					viajero.agregarViajePasajero(viaje);
				}
				viajero.agregarFrecuenciaPasajero(frecuenciaViaje);
				viajeroDAO.modificar(viajero);
				
				frecuenciaViaje.agregarViajeroFrecuencia(viajero);				
				frecuenciaViaje.setAsientosDisponibles(frecuenciaViaje.getAsientosDisponibles()-1);				
				frecuenciaViajeDAO.modificar(frecuenciaViaje);

				return SUCCESS;
			}
			else{
				//solicitudesviaje = solicitudViajeDAO.listarSolicitudesViaje(viaje);
				addFieldError("loginError", "Se completaron todos los asientos!");
				return INPUT;
			}
		}
		return "sinPermisos";
	}
	
	public String RechazarSolicitudViaje() throws Exception{
		if (SessionUtil.checkLogin()){
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);			
			solicitudViaje = solicitudViajeDAO.encontrar(Integer.parseInt(request.getParameter("id")));
			
			solicitudViaje.setEstadoSolicitud(EstadoSolicitud.RECHAZADA);
			solicitudViaje.setFechaFinSolicitud(new Date());
			solicitudViajeDAO.modificar(solicitudViaje);	
			
			idFrecuenciaViaje = solicitudViaje.getFrecuenciaViaje().getId();			
			/*NotificacionAction notificacion=new NotificacionAction();
			notificacion.crearNotificacionRechazoSolicitud(viajeroSolicitud, viaje);*/			
			return SUCCESS;
		} else{
			return "sinPermisos";
		}
	}
	
	public String CancelarSolicitudViaje() throws Exception{
		if(SessionUtil.checkLogin()){
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);			
			solicitudViaje = solicitudViajeDAO.encontrar(Integer.parseInt(request.getParameter("id")));
			frecuenciaViaje = ((FrecuenciaViajeDAOjpa)frecuenciaViajeDAO).encontrar(solicitudViaje.getFrecuenciaViaje().getId());
			viaje = ((ViajeDAOjpa)viajeDAO).encontrarPorId(frecuenciaViaje.getViaje().getId());
							
			solicitudViaje.setEstadoSolicitud(EstadoSolicitud.CANCELADA);
			solicitudViaje.setFechaFinSolicitud(new Date());
			solicitudViajeDAO.modificar(solicitudViaje);
			
			viajero = ((ViajeroDAOjpa)viajeroDAO).encontrar(solicitudViaje.getViajero().getId());
			if (((FrecuenciaViajeDAOjpa)frecuenciaViajeDAO).cantidadFrecuenciasEnViaje(viajero,viaje) == 1){				
				viajero.quitarViajePasajero(viaje);				
				viaje.quitarPasajero(viajero);
			}			
			viajero.quitarFrecuenciaPasajero(frecuenciaViaje);
			viajeroDAO.modificar(viajero);			
			viajeDAO.modificar(viaje);
			
			frecuenciaViaje.agregarPasajeroHistorial(viajero);
			frecuenciaViaje.setAsientosDisponibles(frecuenciaViaje.getAsientosDisponibles()+1);			
			frecuenciaViajeDAO.modificar(frecuenciaViaje);			
			
			idFrecuenciaViaje = frecuenciaViaje.getId();
			//solicitudesviaje = solicitudViajeDAO.listarSolicitudesViaje(viaje);
			return SUCCESS;
			
		}
		return "sinPermisos";
	}
	
	public String SolicitudesFrecuenciaViaje() throws Exception{
		if(SessionUtil.checkLogin()){
			frecuenciaViaje = frecuenciaViajeDAO.encontrar(this.idFrecuenciaViaje);			
			listaSolicitudes = ((SolicitudViajeDAOjpa)solicitudViajeDAO).buscarSolicitudesFrecuencia(frecuenciaViaje);			
			return SUCCESS;
		}
		return "sinPermisos";
	}
	/*	
	public boolean tieneSolicitud(Viaje viaje,Usuario viajero){
		String tienePermisos=this.validarSesion();
		if(tienePermisos==SUCCESS){
			this.setSolicitudesviaje(this.getSolicitudViajeDAO().listarSolicitudesViaje(viaje));
			for(SolicitudViaje solicitud : this.getSolicitudesviaje()){
				if(solicitud.getViajero().getId()==viajero.getId()){
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean eliminarSolicitud(Viaje viaje,Viajero viajeroId) throws Exception{
		List<SolicitudViaje> solicito= solicitudViajeDAO.yaSolicito(viaje, viajero);
		if(!solicito.isEmpty()){									
			for(SolicitudViaje solicitud : solicito){
				solicitudViajeDAO.eliminar(solicitud.getId());
				addFieldError("loginError", "La solicitud se elimino con exito!");
				return true;
			}
		}
		return false;
	}*/
}
