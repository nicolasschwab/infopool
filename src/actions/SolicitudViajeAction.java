package actions;

import implementacionesDAO.FactoryDAO;
import implementacionesDAO.SolicitudViajeDAOjpa;
import implementacionesDAO.ViajeDAOjpa;
import implementacionesDAO.ViajeroDAOjpa;

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
	private SolicitudViaje solicitud= new SolicitudViaje();
	private Viaje unViaje = new Viaje();
	
	

	private SolicitudViaje solicitudviaje = new SolicitudViaje();
	private List<SolicitudViaje> solicitudesviaje = new ArrayList<SolicitudViaje>();
	
	private SolicitudViajeDAOjpa solicitudViajeDAO;
	private ViajeDAOjpa viajeDAO = new ViajeDAOjpa();
	private ViajeroDAOjpa viajeroDAO = new ViajeroDAOjpa();
	
	


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

	public SolicitudViajeDAOjpa getSolicitudViajeDAO() {
		return solicitudViajeDAO;
	}

	public void setSolicitudViajeDAO(SolicitudViajeDAOjpa solicitudDAO) {
		this.solicitudViajeDAO = solicitudDAO;
	}
	
	
	public String solicitudesViaje(){
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		viaje = viajeDAO.encontrar(Integer.parseInt(request.getParameter("id"))); 
		solicitudesviaje = solicitudViajeDAO.listarSolicitudesViaje(viaje);
		return SUCCESS;
	}
	
	public String registroSolicitudViaje(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		String user = (String) session.get("perfil");
		if (user != null) {	
			if (user.equals("viajero")) {
				viajero = viajeroDAO.encontrar(((Usuario)session.get("usrLogin")).getId());
				HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
				viaje = viajeDAO.encontrar(Integer.parseInt(request.getParameter("id")));
				SolicitudViajeDAOjpa unaSolicitud= new SolicitudViajeDAOjpa();
				List<SolicitudViaje> solicito= unaSolicitud.yaSolicito(viaje, viajero);
				if(solicito.isEmpty()){
					fechaSolicitud = new Date();				
					estado = EstadoSolicitud.PENDIENTE;					
					solicitudviaje = new SolicitudViaje(fechaSolicitud,estado,viaje,viajero);					
					solicitudViajeDAO = (SolicitudViajeDAOjpa) FactoryDAO.getSolicitudViajeDAO();
					solicitudViajeDAO.registrar(solicitudviaje);
					return SUCCESS;				
				}else{
					addFieldError("loginError", "Usted ya solicito participar en este viaje");
					return SUCCESS;
				}
			}
			else{
				return "sinpermisos";
			}
		}
		else{
			return "login";
		}	
	}

	public ViajeroDAOjpa getViajeroDAO() {
		return viajeroDAO;
	}

	public void setViajeroDAO(ViajeroDAOjpa viajeroDAO) {
		this.viajeroDAO = viajeroDAO;
	}
	
	public String aceptarSolicitudViaje(){
		
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		SolicitudViajeDAOjpa solicitudViaje= new SolicitudViajeDAOjpa();
		solicitud = solicitudViaje.encontrar(Integer.parseInt(request.getParameter("id")));
		solicitud.setEstado(EstadoSolicitud.ACEPTADO);
		solicitudViaje.modificar(solicitud);
		unViaje=solicitud.getViaje();
		solicitudesviaje = solicitudViajeDAO.listarSolicitudesViaje(unViaje);
		Viajero pasajero= solicitud.getViajero();
		pasajero.getMisViajesPasajero().add(unViaje);
		ViajeroDAOjpa viajero = new ViajeroDAOjpa();
		viajero.modificar(pasajero);		
		return SUCCESS;
	}
	
	public Viaje getUnViaje() {
		return unViaje;
	}


	public void setUnViaje(Viaje unViaje) {
		this.unViaje = unViaje;
	}


	public String rechazarSolicitudViaje(){
		
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		SolicitudViajeDAOjpa solicitudViaje= new SolicitudViajeDAOjpa();
		solicitud = solicitudViaje.encontrar(Integer.parseInt(request.getParameter("id")));
		solicitud.setEstado(EstadoSolicitud.RECHAZADO);
		solicitudViaje.modificar(solicitud);
		solicitudesviaje = solicitudViajeDAO.listarSolicitudesViaje(solicitud.getViaje());
		Viajero pasajero= solicitud.getViajero();
		pasajero.getMisViajesPasajero().remove(unViaje);
		ViajeroDAOjpa viajero = new ViajeroDAOjpa();
		viajero.modificar(pasajero);
		return SUCCESS;
	}

}
