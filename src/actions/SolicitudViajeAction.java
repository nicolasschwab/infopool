package actions;

import implementacionesDAO.FactoryDAO;
import implementacionesDAO.SolicitudViajeDAOjpa;
import implementacionesDAO.ViajeDAOjpa;
import implementacionesDAO.ViajeroDAOjpa;
import interfacesDAO.EventoDAO;
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
	
	private SolicitudViajeDAOjpa solicitudViajeDAO;	
	
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

	public SolicitudViajeDAOjpa getSolicitudViajeDAO() {
		return solicitudViajeDAO;
	}

	public void setSolicitudViajeDAO(SolicitudViajeDAOjpa solicitudDAO) {
		this.solicitudViajeDAO = solicitudDAO;
	}
	
	
	public String solicitudesViaje(){
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		ViajeDAOjpa viajeDAO = new ViajeDAOjpa();
		viaje = viajeDAO.encontrar(Integer.parseInt(request.getParameter("id"))); 
		solicitudesviaje = solicitudViajeDAO.listarSolicitudesViaje(viaje);		
		return SUCCESS;
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
					System.out.println("error al solicitar de nuevo la solicitud");
					fechaSolicitud = new Date();				
					estado = EstadoSolicitud.PENDIENTE;					
					solicitudviaje = new SolicitudViaje(fechaSolicitud,estado,viaje,viajero);					
					//solicitudViajeDAO = (SolicitudViajeDAOjpa) FactoryDAO.getSolicitudViajeDAO();
					solicitudViajeDAO.registrar(solicitudviaje);					
					return SUCCESS;				
				}else{					
					addFieldError("loginError", "Usted ya solicito participar en este viaje");
					return INPUT;
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
		
	public String aceptarSolicitudViaje(){		
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
			return SUCCESS;
		}
		else{
			solicitudesviaje = solicitudViajeDAO.listarSolicitudesViaje(viaje);
			addFieldError("loginError", "Se completaron todos los asientos!");
			return INPUT;
		}
	}
	
	public String rechazarSolicitudViaje(){
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
		return SUCCESS;
	}
	
	public String cancelarSolicitudViaje(){		
		ViajeroDAOjpa viajeroDAO = new ViajeroDAOjpa();
		ViajeDAOjpa viajeDAO = new ViajeDAOjpa();
		Map<String, Object> session = ActionContext.getContext().getSession();
		String user = (String) session.get("perfil");
		if (user != null) {	
			if (user.equals("viajero")) {
				HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
				
				viajero = viajeroDAO.encontrar(((Usuario)session.get("usrLogin")).getId());				
				viaje = viajeDAO.encontrar(Integer.parseInt(request.getParameter("id")));	
				
				List<SolicitudViaje> solicito= solicitudViajeDAO.yaSolicito(viaje, viajero);
				if(!solicito.isEmpty()){									
					// FALTA ELIMINAR LA SOLICITUD Y AL VIAJERO
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

}
