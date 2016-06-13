package actions;

import implementacionesDAO.FactoryDAO;
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

import util.Generics;
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
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			String idFrecuencia=request.getParameter("idFrecuenciaViaje");
			String respuesta= Generics.getGenericSolicitudAction().RegistroSolicitudViaje(idFrecuencia);
			idViaje=((FrecuenciaViaje) FactoryDAO.getFrecuenciaViajeDAO().encontrar(Integer.parseInt(idFrecuencia))).getViaje().getId();
			switch(respuesta){
				case "SUCCESS":
					return SUCCESS;
				case "INPUT":
					addFieldError("loginError", "Usted ya solicito participar en este viaje");
					return INPUT;
				default:
					return respuesta;
				}
		}
		else{
			return "login";
		}	
	}
	
	public String AceptarSolicitudViaje() throws Exception{		
		if(SessionUtil.checkLogin()){
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);			
			solicitudViaje = FactoryDAO.getSolicitudViajeDAO().encontrar(Integer.parseInt(request.getParameter("id")));
			String respuesta=Generics.getGenericSolicitudAction().AceptarSolicitudViaje(solicitudViaje);				
			idFrecuenciaViaje = solicitudViaje.getFrecuenciaViaje().getId();
			switch(respuesta)
			{
				case "SUCCESS":
					return SUCCESS;
				case "INPUT":
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
			String respuesta=Generics.getGenericSolicitudAction().RechazarSolicitudViaje(solicitudViaje);
			idFrecuenciaViaje=solicitudViaje.getFrecuenciaViaje().getId();
			switch(respuesta){
			case "SUCCESS":
				return SUCCESS;
			default:
				return "sinPermisos";
			}			
		}else{
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
			new NotificacionAction().crearNotificacionSolicitudCancelada(viaje);
			return SUCCESS;
			
		}
		return "sinPermisos";
	}
	
	public String SolicitudesFrecuenciaViaje() throws Exception{
		if(SessionUtil.checkLogin()){
			listaSolicitudes = Generics.getGenericSolicitudAction().SolicitudesFrecuenciaViaje(idFrecuenciaViaje);
			if(listaSolicitudes==null){
				return "sinPermisos"; 
			}
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
