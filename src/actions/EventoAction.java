package actions;

import implementacionesDAO.EventoDAOjpa;
import implementacionesDAO.FactoryDAO;
import interfacesDAO.EventoDAO;
import interfacesDAO.ViajeDAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import model.Evento;
import model.Viaje;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class EventoAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	public int id;
	public String nombre;
	public String fechaHora;
	public String web;
	public String ubicacion;
	private EventoDAOjpa eventoDAO;
	private List<Evento> eventoLista = new ArrayList<Evento>();
	private Evento evnt;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getFechaHora() {
		return fechaHora;
	}
	public void setFechaHora(String fechaHora) {
		this.fechaHora = fechaHora;
	}
	public String getWeb() {
		return web;
	}
	public void setWeb(String web) {
		this.web = web;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	public EventoDAOjpa getEventoDAO() {
		return eventoDAO;
	}
	public void setEventoDAO(EventoDAOjpa eventoDAO) {
		this.eventoDAO = eventoDAO;
	}
	public List<Evento> getEventoLista() {
		return eventoLista;
	}
	public void setEventoLista(List<Evento> eventoLista) {
		this.eventoLista = eventoLista;
	}
	public Evento getEvnt() {
		return evnt;
	}
	public void setEvnt(Evento evnt) {
		this.evnt = evnt;
	}
	
	public String registroEvento(){
		return SUCCESS;
	}
	
	public String registrarEvento() throws ParseException{
		Map<String, Object> session = ActionContext.getContext().getSession();
		String user = (String) session.get("perfil");
		if (user != null) {	
			if (user.equals("administrador")) {
				if ((this.nombre.length() > 0) && (this.fechaHora.length() > 0) && (this.ubicacion.length() > 0)){
					if(!eventoDAO.existeNombreEvento(this.getNombre())){					
						EventoDAO evento = FactoryDAO.getEventoDAO();				
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
						Date fechaeve = sdf.parse(this.getFechaHora());
						Evento eve = new Evento(this.getNombre(),fechaeve,this.getWeb(),this.getUbicacion());				
						evento.registrar(eve);
						return SUCCESS;										
					}else{
						addFieldError("nombre", "El nombre del evento ya existe!");
						return INPUT;
					}
				}
				else{
					addFieldError("obligatorios", "Los campos Nombre, Fecha y Ubicación son obligatorios!");
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
	
	public String listarEventos(){
		eventoLista = eventoDAO.listar();
		return SUCCESS;
	}
	
	public String eventos(){
		return SUCCESS;
	}
	
	public String detalleEvento(){
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		evnt = eventoDAO.encontrar(Integer.parseInt(request.getParameter("id")));
		return SUCCESS;
	}
	
	public String edicionEvento(){
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		evnt = eventoDAO.encontrar(Integer.parseInt(request.getParameter("id")));
		return SUCCESS;
	}
	
	public String editarEvento() throws ParseException{		
		Map<String, Object> session = ActionContext.getContext().getSession();
		String user = (String) session.get("perfil");
		if (user != null) {	
			if (user.equals("administrador")) {
				if ((this.nombre.length() > 0) && (this.fechaHora.length() > 0) && (this.ubicacion.length() > 0)){
					HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
					evnt = eventoDAO.encontrar(Integer.parseInt(request.getParameter("id")));
					if (!eventoDAO.existeNombreEvento(this.getNombre(),evnt.getId())){
						EventoDAO evento = FactoryDAO.getEventoDAO();				
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
						Date fechaeve = sdf.parse(this.getFechaHora());
						evnt.setNombre(this.getNombre());
						evnt.setFechaHora(fechaeve);
						evnt.setWeb(this.getWeb());
						evnt.setUbicacion(this.getUbicacion());
						evento.modificar(evnt);
						//FALTA AVISAR A LOS VIAJES REGISTRADOS
						return SUCCESS;
					}else{
						addFieldError("nombre", "El nombre del evento ya existe!");
						return INPUT;
					}
				}
				else{
					addFieldError("obligatorios", "Los campos Nombre, Fecha y Ubicación son obligatorios!");
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
	
	public String cancelarEvento() throws ParseException{		
		Map<String, Object> session = ActionContext.getContext().getSession();
		String user = (String) session.get("perfil");
		if (user != null) {	
			if (user.equals("administrador")) {
				EventoDAO evento = FactoryDAO.getEventoDAO();
				ViajeDAO viajedao = FactoryDAO.getViajeDAO();
				HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
				evnt = eventoDAO.encontrar(Integer.parseInt(request.getParameter("id")));
				evnt.setActivo(false);
				evento.modificar(evnt);
				
				//Cancelamos los viajes correspondientes al evento y se notifica a los viajeros
				for (Viaje viaje : evnt.getViajes()) {
					viaje.setActivo(false);
					viajedao.modificar(viaje);
				}
				
				//FALTA AVISAR A LOS VIAJES REGISTRADOS
				return SUCCESS;
			}else{
				return "sinpermisos";
			}
		}
		else{
			return "login";
		}
	}
	
	
}
