package actions;

import implementacionesDAO.FactoryDAO;
import implementacionesDAO.MensajeDAOjpa;
import implementacionesDAO.ViajeDAOjpa;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import model.Conversacion;
import model.Mensaje;
import model.Usuario;
import model.Viaje;
import model.Viajero;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class MensajeAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private int id;
	private Date fecha;
	private String asunto;
	private String detalle;
	private String estado;	
	private Usuario emisor;
	private MensajeDAOjpa mensajeDAO;
	private ViajeDAOjpa viajeDAO=(ViajeDAOjpa) FactoryDAO.getViajeDAO();
	private List<Viajero> listaReceptores ;	
	private List<Viajero> lista;
	private int viajeId;
	private String respDetalle;
	private Usuario user;
	
	public String getRespDetalle() {
		return respDetalle;
	}
	public void setRespDetalle(String respDetalle) {
		this.respDetalle = respDetalle;
	}
	public int getViajeId() {
		return viajeId;
	}
	public void setViajeId(int viajeId) {
		this.viajeId = viajeId;
	}
	public List<Viajero> getLista() {
		return lista;
	}
	public void setLista(List<Viajero> lista) {
		this.lista = lista;
	}
	public List<Viajero> getListaReceptores() {
		return listaReceptores;
	}
	public void setListaReceptores(List<Viajero> listaReceptores) {
		this.listaReceptores = listaReceptores;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Usuario getEmisor() {
		return emisor;
	}
	public void setEmisor(Usuario emisor) {
		this.emisor = emisor;
	}	
	public MensajeDAOjpa getMensajeDAO() {
		return mensajeDAO;
	}
	public void setMensajeDAO(MensajeDAOjpa mensajeDAO) {
		this.mensajeDAO = mensajeDAO;
	}
	public String verDetalleMensaje(){
		return this.validarSesion();		
	}
	public String nuevoMensaje(){
		String tienePermisos=this.validarSesion();
		if(tienePermisos==SUCCESS){		
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		String[] elId= request.getParameterValues("id");
		this.setViajeId(Integer.parseInt(elId[0]));
		this.listaUsuarios();
		}
		return tienePermisos;
	}	
	
	private void listaUsuarios(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		Usuario user = (Usuario) session.get("usrLogin");	
		this.listaReceptores= new ArrayList<Viajero>(); 
		Viaje unViaje=viajeDAO.encontrarPorId(this.viajeId);
		lista=(List<Viajero>) unViaje.obtenerPasajeros();
		lista.add(unViaje.getConductor());
		for (Viajero viajero : lista) {	
			if (viajero.getId()!=user.getId()){
				listaReceptores.add(viajero);
			}
						
		}
	}	
	public String nuevoMensajeForo(){
		String resul=this.validarSesion();
		if(resul.equals(SUCCESS)){
				HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
				String[] elId= request.getParameterValues("id");
				this.setViajeId(Integer.parseInt(elId[0]));		
		}
		return resul;
			
	}
	private String validarSesion(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		user = (Usuario) session.get("usrLogin");
		if (user != null) {
			if (user.getPerfil().equals("viajero")) {				
				return SUCCESS;
			}		
			else {
				return "sinPermisos";
			}
		} else {
			return "login";
		}
	}
	
	public String enviarMensajeForo() throws ParseException{
		String resul=this.validarSesion();
		if(resul.equals(SUCCESS)){
			if (!this.detalle.equals("")){
				Map<String, Object> session = ActionContext.getContext().getSession();
				Usuario user = (Usuario) session.get("usrLogin");
				Viaje viaje=viajeDAO.encontrar(this.viajeId);				
				Collection<Viajero> lista=viaje.obtenerPasajeros();
				session.put("id",this.viajeId);
				lista.add(viaje.getConductor());
				for (Iterator<Viajero> i = lista.iterator(); i.hasNext(); ){
					Viajero elViajero=i.next();
					if(elViajero.getId()!=user.getId()){
						//Mensaje Mensaje= new Mensaje(new Date(),"Nuevo Mensaje en el foro","Hola compañero, eh publicado un mensaje el foro del viaje que compartimos a "+viaje.getDireccionDestino()+" el "+viaje.getFechaInicio(),"pendiente",user,true);
						//this.mensajeDAO.registrar(Mensaje);
					}				
				}
			}
		}
		return resul;
	}
	public Mensaje crearMensaje(String detalleMensaje,Conversacion conversacion) throws Exception {
		String permisos=this.validarSesion();
		if(permisos==SUCCESS){
			this.fecha=new Date();
			this.emisor=user;
			this.estado="pendiente";
			Mensaje nuevoMensaje= new Mensaje(this.fecha, detalleMensaje,this.estado,this.emisor);
			FactoryDAO.getMensajeDAO().registrar(nuevoMensaje);
			return nuevoMensaje;
		}
		return null;
	}
}
