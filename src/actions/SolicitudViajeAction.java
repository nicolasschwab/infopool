package actions;

import implementacionesDAO.FactoryDAO;
import implementacionesDAO.SolicitudViajeDAOjpa;
import implementacionesDAO.ViajeDAOjpa;
import implementacionesDAO.ViajeroDAOjpa;
import interfacesDAO.EventoDAO;
import interfacesDAO.SolicitudViajeDAO;
import interfacesDAO.ViajeDAO;
import interfacesDAO.ViajeroDAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import model.EstadoSolicitud;
import model.SolicitudViaje;
import model.Usuario;
import model.Viaje;
import model.Viajero;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class SolicitudViajeAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private Date fechaSolicitud;
	private EstadoSolicitud estado;
	private Viaje viaje;	
	private Viajero viajero;
	private SolicitudViaje solicitud;
	private int idViaje;
	private SolicitudViaje solicitudviaje;
	private List<SolicitudViaje> solicitudesviaje = new ArrayList<SolicitudViaje>();	
	private SolicitudViajeDAO solicitudViajeDAO= FactoryDAO.getSolicitudViajeDAO();	
	private String notif="";	
	
	public String getNotif() {
		return notif;
	}
	public void setNotif(String notif) {
		this.notif = notif;
	}
	public int getIdViaje() {
		return idViaje;
	}
	public void setIdViaje(int idViaje) {
		this.idViaje = idViaje;
	}
	public SolicitudViaje getSolicitud() {
		return solicitud;
	}

	public void setSolicitud(SolicitudViaje solicitud) {
		this.solicitud = solicitud;
	}


	public int getId() {
		return id;
	}

	
	public void setId(int id) {
		this.id = id;
	}

	public Date getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public EstadoSolicitud getEstado() {
		return estado;
	}

	public void setEstado(EstadoSolicitud estado) {
		this.estado = estado;
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

	public SolicitudViaje getSolicitudviaje() {
		return solicitudviaje;
	}

	public void setSolicitudviaje(SolicitudViaje solicitudviaje) {
		this.solicitudviaje = solicitudviaje;
	}
	
	public List<SolicitudViaje> getSolicitudesviaje() {
		return solicitudesviaje;
	}

	public void setSolicitudesviaje(List<SolicitudViaje> solicitudesviaje) {
		this.solicitudesviaje = solicitudesviaje;
	}

	public SolicitudViajeDAO getSolicitudViajeDAO() {
		return solicitudViajeDAO;
	}

	public void setSolicitudViajeDAO(SolicitudViajeDAO solicitudDAO) {
		this.solicitudViajeDAO = solicitudDAO;
	}
	
	
	public String solicitudesViaje(){
		String tienePermisos=this.validarSesion();
		if(tienePermisos==SUCCESS){
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			ViajeDAO viajeDAO = FactoryDAO.getViajeDAO();
			viaje = viajeDAO.encontrar(Integer.parseInt(request.getParameter("id")));
			Map<String, Object> session = ActionContext.getContext().getSession();
			Usuario user = (Usuario) session.get("usrLogin");
			if(viaje.getConductor().getId()==user.getId()){
				solicitudesviaje = solicitudViajeDAO.listarSolicitudesViaje(viaje);
				if(this.getNotif()!=""){
					new NotificacionAction().cambiarEstadoAVisitado(this.notif);
				}
				return SUCCESS;
			}
			return "sinPermisos";
		}
		return tienePermisos;
	}
	
	public String registroSolicitudViaje(){		
		ViajeroDAOjpa viajeroDAO = new ViajeroDAOjpa();
		ViajeDAOjpa viajeDAO = new ViajeDAOjpa();
		Map<String, Object> session = ActionContext.getContext().getSession();
		String user = (String) session.get("perfil");
		if (user != null) {	
			if (user.equals("viajero")) {
				viajero = viajeroDAO.encontrar(((Usuario)session.get("usrLogin")).getId());
				HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
				viaje = viajeDAO.encontrar(Integer.parseInt(request.getParameter("id")));
				idViaje=viaje.getId();				
				List<SolicitudViaje> solicito= solicitudViajeDAO.yaSolicito(viaje, viajero);
				if(solicito.isEmpty()){
					fechaSolicitud = new Date();				
					estado = EstadoSolicitud.PENDIENTE;					
					solicitudviaje = new SolicitudViaje(fechaSolicitud,estado,viaje,viajero);					
					//solicitudViajeDAO = (SolicitudViajeDAOjpa) FactoryDAO.getSolicitudViajeDAO();
					solicitudViajeDAO.registrar(solicitudviaje);
					//registro una notificacion
					NotificacionAction notificacionAction=new NotificacionAction();
					notificacionAction.crearNotificacionSolicitudNueva(viaje);
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
		
	public String aceptarSolicitudViaje(){
		String tienePermisos=this.validarSesion();
		if(tienePermisos==SUCCESS){
			ViajeroDAOjpa viajeroDAO = new ViajeroDAOjpa();
			ViajeDAOjpa viajeDAO = new ViajeDAOjpa();
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			solicitud = solicitudViajeDAO.encontrar(Integer.parseInt(request.getParameter("id")));
			viaje = viajeDAO.encontrar(solicitud.getViaje().getId());
			idViaje = viaje.getId();	
			if (viaje.getAsientos() > viaje.getPasajeros().size()){
				solicitud.setEstado(EstadoSolicitud.ACEPTADO);
				solicitudViajeDAO.modificar(solicitud);
				solicitudesviaje = solicitudViajeDAO.listarSolicitudesViaje(viaje);
				viajero = solicitud.getViajero();
				viajero.getMisViajesPasajero().add(viaje);		
				viajeroDAO.modificar(viajero);
				NotificacionAction notificacion=new NotificacionAction();
				notificacion.crearNotificacionSolicitudAceptar(viajero, viaje);
				return SUCCESS;
			}
			else{
				solicitudesviaje = solicitudViajeDAO.listarSolicitudesViaje(viaje);
				addFieldError("loginError", "Se completaron todos los asientos!");
				return INPUT;
			}
		}
		return tienePermisos;
	}
	
	public String rechazarSolicitudViaje(){
		String tienePermisos=this.validarSesion();
		if(tienePermisos==SUCCESS){
			ViajeroDAOjpa viajeroDAO = new ViajeroDAOjpa();
			ViajeDAOjpa viajeDAO = new ViajeDAOjpa();
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			solicitud = solicitudViajeDAO.encontrar(Integer.parseInt(request.getParameter("id")));
			solicitud.setEstado(EstadoSolicitud.RECHAZADO);
			solicitudViajeDAO.modificar(solicitud);		
			viaje = viajeDAO.encontrar(solicitud.getViaje().getId());
			Viajero viajeroSolicitud = solicitud.getViajero();		
			if (viaje.esPasajero(viajeroSolicitud)){			
				viajeroSolicitud.getMisViajesPasajero().remove(viaje);		
				viajeroDAO.modificar(viajeroSolicitud);		
			}		
			solicitudesviaje = solicitudViajeDAO.listarSolicitudesViaje(solicitud.getViaje());
			idViaje = viaje.getId();	
			NotificacionAction notificacion=new NotificacionAction();
			notificacion.crearNotificacionRechazoSolicitud(viajeroSolicitud, viaje);
		}
		return tienePermisos;
	}
	
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
	
	public String cancelacionSolicitudViaje(){		
		String tienePermisos=this.validarSesion();
		if(tienePermisos==SUCCESS){
				ViajeroDAO viajeroDAO = FactoryDAO.getViajeroDAO();
				ViajeDAO viajeDAO = FactoryDAO.getViajeDAO();
				HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);				
				viajero = viajeroDAO.encontrar(((Usuario)ActionContext.getContext().getSession().get("usrLogin")).getId());				
				viaje = viajeDAO.encontrar(Integer.parseInt(request.getParameter("id")));
				this.setIdViaje(viaje.getId());
				if(!this.eliminarSolicitud(viaje, viajero)){
					addFieldError("loginError", "Usted no forma parte de este viaje");
				}
		}
		return tienePermisos;
	}
	
	public boolean eliminarSolicitud(Viaje viaje,Viajero viajeroId){
		List<SolicitudViaje> solicito= solicitudViajeDAO.yaSolicito(viaje, viajero);
		if(!solicito.isEmpty()){									
			for(SolicitudViaje solicitud : solicito){
				solicitudViajeDAO.eliminar(solicitud.getId());
				addFieldError("loginError", "La solicitud se elimino con exito!");
				return true;
			}
		}
		return false;
	}
	
	private String validarSesion(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		String user = (String) session.get("perfil");
		if (user != null) {	
			if (user.equals("viajero")) {
				return SUCCESS;
			}
			else{
				return "sinPermisos";
			}
		}
		else{
			return "login";
		}	
	}
}
