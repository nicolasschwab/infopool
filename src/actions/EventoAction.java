package actions;

import implementacionesDAO.EventoDAOjpa;
import implementacionesDAO.FactoryDAO;
import interfacesDAO.EventoDAO;
import interfacesDAO.ViajeDAO;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import model.CoordenadasLatLng;
import model.Evento;
import model.Usuario;
import model.Viaje;
import util.SessionUtil;

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
	/*public Double ubicacionLatitud;
	public Double ubicacionLongitud;*/
	public String horaComienzo;
	public String horaFin;
	public String descripcion;
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
	/*public Double getUbicacionLatitud() {
		return ubicacionLatitud;
	}
	public void setUbicacionLatitud(Double ubicacionLatitud) {
		this.ubicacionLatitud = ubicacionLatitud;
	}
	public Double getUbicacionLongitud() {
		return ubicacionLongitud;
	}
	public void setUbicacionLongitud(Double ubicacionLongitud) {
		this.ubicacionLongitud = ubicacionLongitud;
	}*/
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
	public String getHoraComienzo() {
		return horaComienzo;
	}
	public void setHoraComienzo(String horaComienzo) {
		this.horaComienzo = horaComienzo;
	}
	public String getHoraFin() {
		return horaFin;
	}
	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String registroEvento(){
		return tienePermisosAdmin();
	}
	
	public String registrarEvento() throws Exception{
		String tienePermisoDeAdmin=this.tienePermisosAdmin();
		if(tienePermisoDeAdmin==SUCCESS){
				if ((this.nombre.length() > 0) && (this.fechaHora.length() > 0) && (this.ubicacion.length() > 0)){
					if(!eventoDAO.existeNombreEvento(this.getNombre())){					
						EventoDAO evento = FactoryDAO.getEventoDAO();				
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
						Date fechaeve = sdf.parse(this.getFechaHora());
						/*CoordenadasLatLng puntoUbicacion = new CoordenadasLatLng(this.getUbicacionLatitud(), this.getUbicacionLongitud());
						Evento eve = new Evento(this.getNombre(),fechaeve,Time.valueOf(this.getHoraComienzo()+":00"),Time.valueOf(this.getHoraFin()+":00"),this.getWeb(),this.getUbicacion(),puntoUbicacion,this.getDescripcion(),true);*/						
						Evento eve = new Evento(this.getNombre(),fechaeve,Time.valueOf(this.getHoraComienzo()+":00"),Time.valueOf(this.getHoraFin()+":00"),this.getWeb(),this.getUbicacion(),this.getDescripcion(),true);
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
		return tienePermisoDeAdmin;
	}
	
	public String listarEventos(){
		eventoLista = eventoDAO.listar();
		return tienePermisos();
	}
	
	public String eventos(){
		return tienePermisosAdmin();
	}
	
	public String detalleEvento(){
		String tienePermisoDeAdmin=this.tienePermisosAdmin();
		if(tienePermisoDeAdmin==SUCCESS){
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		evnt = eventoDAO.encontrar(Integer.parseInt(request.getParameter("id")));
		}
		return tienePermisoDeAdmin;
	}
	
	public String edicionEvento(){
		String tienePermisoDeAdmin=this.tienePermisosAdmin();
		if(tienePermisoDeAdmin==SUCCESS){
			evnt = eventoDAO.encontrar(this.getId());
		}
		return tienePermisoDeAdmin;
	}
	
	public String editarEvento() throws Exception{		
		String tienePermisoDeAdmin=this.tienePermisosAdmin();
		if(tienePermisoDeAdmin==SUCCESS){
				if ((this.nombre.length() > 0) && (this.fechaHora.length() > 0) && (this.ubicacion.length() > 0)){
					HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
					evnt = eventoDAO.encontrar(Integer.parseInt(request.getParameter("id")));
					if (!eventoDAO.existeNombreEvento(this.getNombre(),evnt.getId())){
						EventoDAO evento = FactoryDAO.getEventoDAO();				
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						Date fechaeve = sdf.parse(this.getFechaHora().split(" ")[0].replace('/','-'));
						evnt.setNombre(this.getNombre());
						evnt.setFecha(fechaeve);
						evnt.setWeb(this.getWeb());
						evnt.setUbicacion(this.getUbicacion());
						if(this.getHoraFin().split(":").length==3){
							evnt.setHoraFin(Time.valueOf(this.getHoraFin()));
						}
						else{
							evnt.setHoraFin(Time.valueOf(this.getHoraFin()+":00"));
						}
						if(this.getHoraComienzo().split(":").length==3){
							evnt.setHoraInicio(Time.valueOf(this.getHoraComienzo()));
						}
						else{
							evnt.setHoraInicio(Time.valueOf(this.getHoraComienzo()+":00"));
						}
						evnt.setDescripcion(this.getDescripcion());
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
		return tienePermisoDeAdmin;
	}
	
	public String cancelarEvento() throws Exception{
		String tienePermisoDeAdmin=this.tienePermisosAdmin();
		if(tienePermisoDeAdmin==SUCCESS){
				EventoDAO evento = FactoryDAO.getEventoDAO();
				ViajeDAO viajedao = FactoryDAO.getViajeDAO();
				HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
				evnt = eventoDAO.encontrar(Integer.parseInt(request.getParameter("id")));
				evnt.setActivo(false);
				evento.modificar(evnt);
				
				//Cancelamos los viajes correspondientes al evento y se notifica a los viajeros
				for (Viaje viaje : evnt.getViajesAsociados()) {
					viaje.setActivo(false);
					viajedao.modificar(viaje);
				}
				
				//FALTA AVISAR A LOS VIAJES REGISTRADOS
				
			}
		return tienePermisoDeAdmin;
	}
	
	private String tienePermisosAdmin(){
		Usuario user=SessionUtil.getUsuario();
		if (user != null) {	
			if (user.soyAdministrador()) {
				return SUCCESS;
			}else{
				return "sinPermisos";
			}
		}
		else{
			return "sinPermisos";
		}
	}
	private String tienePermisos(){		
		Usuario user = SessionUtil.getUsuario();
		if (user != null) {	
			return SUCCESS;
		}
		else{
			return "sinPermisos";
		}
	}
	
	
}
