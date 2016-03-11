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
			ViajeAction viajeAction=new ViajeAction();
			Viajero receptor=FactoryDAO.getViajeroDAO().encontrar(Integer.parseInt(receptorID));
			//if(viajeAction.validarPertenece(viajeId,receptor)){				
				Conversacion laConversacion=this.getConversacionDAO().encontrarPorViajeEIntegrantes(viajeId,user,receptor);
				MensajeAction mensajeAction=new MensajeAction();
				this.setMensaje(mensajeAction.crearMensaje(this.getDetalle()));// creo y persisto el mensaje
				UsuarioDAO usuarioDAO=FactoryDAO.getUsuarioDAO();
				if(laConversacion==null){
					laConversacion=new Conversacion();// en este paso se setea por defecto la fecha de la ultima modificacion
					laConversacion.setAsunto(this.getAsunto());
					laConversacion.setViaje(FactoryDAO.getViajeDAO().encontrar(viajeId));
					Collection<Viajero> participantes = new ArrayList();
					participantes.add(user);
					participantes.add(receptor);
					laConversacion.setParticipantesConversacion(participantes);
					Collection<Mensaje> mensajes= new ArrayList<Mensaje>();
					mensajes.add(this.getMensaje());
					laConversacion.setMensajes(mensajes);
					conversacionDAO.registrar(laConversacion); // persisto la nueva conversacion
				}else{ //ya existe una conversacion entre estos usuario para este viaje, se agrega el mensaje a la conversacion
					Collection<Mensaje> mensajes=laConversacion.getMensajes();
					mensajes.add(this.getMensaje());
					laConversacion.setMensajes(mensajes);
					laConversacion.setFechaUltimaModificacion(new Date());
					conversacionDAO.modificar(laConversacion); //modifico la conversacion
				}
			}
			
		//}
		return estaLogueado;
	}
	
	public String listarConversaciones(){
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
	
	public String detalle(){
		String estaLogueado=this.validarSesion();
		if(estaLogueado==SUCCESS){
			this.setConversacion(conversacionDAO.encontrarPorId(id));
			if(this.getConversacion()!=null){
				boolean pertenece=false;
				for(Viajero viajero :this.getConversacion().getParticipantesConversacion()){
					if(viajero.getId()==user.getId()){
						pertenece=true;
					}
				}
				if(!pertenece){
					this.setConversacion(null);
				}
			}			
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
			this.setConversacion(conversacionDAO.encontrarPorId(this.getId()));
			for(Viajero viajero :conversacion.getParticipantesConversacion()){
				if(viajero.getId()==user.getId()){
					this.setMensaje(new MensajeAction().crearMensaje(this.getDetalle())); // creo y persisto el mensaje
					this.getConversacion().getMensajes().add(this.getMensaje());
					FactoryDAO.getViajeDAO().modificar(user);
					FactoryDAO.getViajeDAO().modificar(viajero);
					this.getConversacion().setFechaUltimaModificacion(new Date());
					conversacionDAO.modificar(this.getConversacion());
				}
			}
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
