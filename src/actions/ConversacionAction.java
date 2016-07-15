package actions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import implementacionesDAO.FactoryDAO;
import interfacesDAO.ConversacionDAO;
import interfacesDAO.UsuarioDAO;
import model.Conversacion;
import model.Mensaje;
import model.Viaje;
import model.Viajero;
import util.Generics;

public class ConversacionAction  extends ActionSupport{
	
	private static final long serialVersionUID = 1L;
	private int id;
	private List<Mensaje> mensajes;
	private String receptorID;
	private String asunto;
	private ConversacionDAO conversacionDAO= FactoryDAO.getConversacionDAO();
	private int viajeId;
	private String detalle;
	private Date fechaUltimaModificacion;
	private Viajero user;
	private Mensaje mensaje;
	private List<Conversacion> mensajeLista;
	private Conversacion conversacion;
	private List<conversacionVista> conversacionVista= new ArrayList<conversacionVista>();
	
	
	public int getViajeId() {
		return viajeId;
	}
	public void setViajeId(int viajeId) {
		this.viajeId = viajeId;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalleMensaje) {
		this.detalle = detalleMensaje;
	}
	public ConversacionDAO getConversacionDAO() {
		return conversacionDAO;
	}
	public void setConversacionDAO(ConversacionDAO conversacionDAO) {
		this.conversacionDAO = conversacionDAO;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Mensaje> getMensajes() {
		return mensajes;
	}
	public void setMensajes(List<Mensaje> mensajes) {
		this.mensajes = mensajes;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}	
	public String getReceptorID() {
		return receptorID;
	}
	public void setReceptorID(String receptorID) {
		this.receptorID = receptorID;
	}
	public Mensaje getMensaje() {
		return mensaje;
	}
	public void setMensaje(Mensaje mensaje) {
		this.mensaje = mensaje;
	}
	public List<Conversacion> getMensajeLista() {
		return mensajeLista;
	}
	public void setMensajeLista(List<Conversacion> mensajeLista) {
		this.mensajeLista = mensajeLista;
	}	
	public Conversacion getConversacion() {
		return conversacion;
	}
	public void setConversacion(Conversacion conversacion) {
		this.conversacion = conversacion;
	}
	public Viajero getUser() {
		return user;
	}
	public void setUser(Viajero user) {
		this.user = user;
	}
	public List<conversacionVista> getConversacionVista() {
		return conversacionVista;
	}
	public void setConversacionVista(List<conversacionVista> conversacionVista) {
		this.conversacionVista = conversacionVista;
	}	
	public Date getFechaUltimaModificacion() {
		return fechaUltimaModificacion;
	}
	public void setFechaUltimaModificacion(Date fechaUltimaModificacion) {
		this.fechaUltimaModificacion = fechaUltimaModificacion;
	}
	
	public String crearConversacion() throws NumberFormatException, Exception{
		String estaLogueado=this.validarSesion(); 
		if(estaLogueado==SUCCESS){
			Generics.getGenericConversacionAction().crearConversacion(receptorID, viajeId, user, this.getDetalle(), this.getAsunto());
			}			
		//}
		return estaLogueado;
	}
	
	private String listarConversaciones(){
		String estaLogueado=this.validarSesion();
		if(estaLogueado==SUCCESS){
			List<Conversacion> conversaciones=this.getConversacionDAO().listar(user);
			//remuevo el usuario logueado de la lista de participantes,
			//esta lista se usa en la vista para listar los participantes de una conversacion
			//y no me interesa que aparezca el usuario logueado en esa lista...
			for(Conversacion conversacion : conversaciones){
				conversacion.getViaje().setDireccionDestino(conversacion.getViaje().getDireccionDestino().split(",")[0]);
				conversacion.getViaje().setDireccionOrigen(conversacion.getViaje().getDireccionOrigen().split(",")[0]);
				Viajero remover=null;
				for(Viajero viajero :conversacion.getParticipantesConversacion()){
					if(viajero.getId()==user.getId()){
						remover=viajero;
					}
				}
				if(remover!=null){
					conversacion.getParticipantesConversacion().remove(remover);
				}
			}
			this.setMensajeLista(conversaciones);			
		}
		return estaLogueado;
	}
	
	public static Conversacion crearForo(String asunto,Viaje viaje,Viajero conductor) throws Exception{
		Conversacion foro=new Conversacion();
		foro.setAsunto(asunto);
		foro.setViaje(viaje);
		List<Viajero> participantes= new ArrayList<Viajero>();
		participantes.add(conductor);
		foro.setParticipantesConversacion(participantes);
		FactoryDAO.getConversacionDAO().registrar(foro);
		return foro;
	}
	public static Conversacion crearForoSinPersistir(String asunto,Viaje viaje,Viajero conductor){
		Conversacion foro=new Conversacion();
		foro.setAsunto(asunto);
		foro.setViaje(viaje);
		List<Viajero> participantes= new ArrayList<Viajero>();
		participantes.add(conductor);
		foro.setParticipantesConversacion(participantes);
		return foro;
	}
	public String detalle(){
		String estaLogueado=this.validarSesion();
		if(estaLogueado==SUCCESS){
			this.setConversacion(Generics.getGenericConversacionAction().detalle(id));		
			this.listarConversaciones();				
			for(Conversacion conversacion : this.getMensajeLista()){
				Mensaje ultimoMensaje=null;
				if(conversacion.getMensajes().size()>0){
					ultimoMensaje=(Mensaje) conversacion.getMensajes().toArray()[conversacion.getMensajes().size()-1];	
				}				
				this.getConversacionVista().add(new conversacionVista(conversacion,ultimoMensaje));
			}
			if(this.getConversacion()==null){
				if(this.getMensajeLista().size()>0){
					this.setConversacion(this.getMensajeLista().get(0));
				}
			}
		}
		return estaLogueado;
	}
	private String validarSesion(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		user = (Viajero) session.get("usrLogin");
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
	public String responderMensaje() throws Exception{
		String estaLogueado=this.validarSesion();
		if(estaLogueado==SUCCESS){
			Generics.getGenericConversacionAction().responderMensaje(id, this.getDetalle());
		}
		return estaLogueado;		
	}

	public class conversacionVista{
		Conversacion conversacion;
		Mensaje ultimoMensaje;
		
		conversacionVista(Conversacion conversacion,Mensaje mensaje){
			this.setConversacion(conversacion);
			this.setUltimoMensaje(mensaje);
		}
		public Conversacion getConversacion() {
			return conversacion;
		}
		public void setConversacion(Conversacion conversacion) {
			this.conversacion = conversacion;
		}
		public Mensaje getUltimoMensaje() {
			return ultimoMensaje;
		}
		public void setUltimoMensaje(Mensaje ultimoMensaje) {
			this.ultimoMensaje = ultimoMensaje;
		}
		
	}
}
