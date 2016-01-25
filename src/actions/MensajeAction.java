package actions;

import implementacionesDAO.FactoryDAO;
import implementacionesDAO.ForoMensajesDAOjpa;
import implementacionesDAO.MensajeDAOjpa;
import implementacionesDAO.ViajeDAOjpa;
import implementacionesDAO.ViajeroDAOjpa;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import model.ForoMensajes;
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
	private Usuario receptor;
	private MensajeDAOjpa mensajeDAO;
	private List<Mensaje> mensajeLista = new ArrayList<Mensaje>();
	private ViajeDAOjpa viajeDAO=(ViajeDAOjpa) FactoryDAO.getViajeDAO();
	private List<Viajero> listaReceptores ;
	private ViajeroDAOjpa viajeroDAO = (ViajeroDAOjpa) FactoryDAO.getViajeroDAO();
	private ForoMensajesDAOjpa foroMensajeDAO=(ForoMensajesDAOjpa) FactoryDAO.getForoMensajesDAO();
	private String receptorID;
	private List<Viajero> lista;
	private int viajeId;
	private String respDetalle;
	private String queNoSos;
	private String notif="";
	private Usuario user;
	
	
	
	public String getNotif() {
		return notif;
	}
	public void setNotif(String notif) {
		this.notif = notif;
	}
	public String getQueNoSos() {
		return queNoSos;
	}
	public void setQueNoSos(String queNoSos) {
		this.queNoSos = queNoSos;
	}
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
	public String getReceptorID() {
		return receptorID;
	}
	public void setReceptorID(String receptorID) {
		this.receptorID = receptorID;
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
	public Usuario getReceptor() {
		return receptor;
	}
	public void setReceptor(Usuario receptor) {
		this.receptor = receptor;
	}	
	/*public String listarMensajes(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		Usuario user = (Usuario) session.get("usrLogin");
		mensajeLista = mensajeDAO.listar(user);
		return SUCCESS;
	}*/
	public MensajeDAOjpa getMensajeDAO() {
		return mensajeDAO;
	}
	public void setMensajeDAO(MensajeDAOjpa mensajeDAO) {
		this.mensajeDAO = mensajeDAO;
	}
	public List<Mensaje> getMensajeLista() {
		return mensajeLista;
	}
	public void setMensajeLista(List<Mensaje> mensajeLista) {
		this.mensajeLista = mensajeLista;
	}
	public String verDetalleMensaje(){
		
		return SUCCESS;
	}
	public String nuevoMensaje(){	
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		String[] elId= request.getParameterValues("id");
		this.setViajeId(Integer.parseInt(elId[0]));
		this.listaUsuarios();
		return SUCCESS;
	}
	
	
	private void listaUsuarios(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		Usuario user = (Usuario) session.get("usrLogin");	
		this.listaReceptores= new ArrayList<Viajero>(); 
		Viaje unViaje=viajeDAO.encontrarPorId(this.viajeId);
		lista=(List<Viajero>) unViaje.getPasajeros();
		lista.add(unViaje.getConductor());
		for (Viajero viajero : lista) {	
			if (viajero.getId()!=user.getId()){
				listaReceptores.add(viajero);
			}
						
		}
	}
	
	/*public String enviarMensaje(){
		
		Map<String, Object> session = ActionContext.getContext().getSession();
		Usuario user = (Usuario) session.get("usrLogin");
		if (user != null) {
			if (user.getPerfil().equals("viajero")) {				
				if (this.receptorID!=null && !this.receptorID.isEmpty()&& this.asunto!=null && !this.asunto.isEmpty() && this.detalle!=null && !this.detalle.isEmpty() ){
					this.receptor=viajeroDAO.encontrar(Integer.parseInt(receptorID));
					this.fecha=new Date();
					this.estado="pendiente";
					this.emisor=user;
					Mensaje nuevoMensaje= new Mensaje (this.fecha, this.asunto,this.detalle,this.estado,this.emisor);
					this.mensajeDAO.registrar(nuevoMensaje);
					NotificacionAction notificacion=new NotificacionAction();
					this.mensajeLista=this.mensajeDAO.listar(user);
					notificacion.crearNotificacionPrivado(this.receptor, this.mensajeLista.get(this.mensajeLista.size()-1).getId());
					return SUCCESS;	
				}else{
					this.listaUsuarios();
					addFieldError("MensajeError", "Debe completar todos los campos");
					return INPUT;
				}
				
			}		
			else {
				return "sinpermisos";
			}
		} else {
			return "login";
		}
	}
	
	
	public String detalle(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		Usuario user = (Usuario) session.get("usrLogin");
		if (user != null) {
			if (user.getPerfil().equals("viajero")) {
				HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
				String[] elId= request.getParameterValues("id");
				this.id=Integer.parseInt(elId[0]);
				Mensaje elMensaje;
				elMensaje=this.mensajeDAO.encontrar(this.id);
				//cheque que el emisor o el receptor sea el ususario logueado
				//para que alguien no pueda cambiar el ID y ver cualquier mensaje
				if(elMensaje.getEmisor().getId()==user.getId() )
				{				
					if(elMensaje.getEmisor().getId()!=user.getId()){ // setea datos particulares para ver el mensaje siendo el receptor
						this.queNoSos="Emisor";					
						this.emisor=elMensaje.getEmisor();	
						this.receptorID=String.valueOf(elMensaje.getEmisor().getId());
						if(elMensaje.getEstado().equals("pendiente" )){
							elMensaje.setEstado("leido");
							
							this.mensajeDAO.modificar(elMensaje);
						}
						
					}else{    // setea datos particulares para  ver el mensaje siendo el emisor
						this.queNoSos="Receptor";
						this.emisor=elMensaje.getReceptor();					
						}
					this.asunto=elMensaje.getAsunto();    // setea datos en comun entre emisor y receptor
					this.detalle=elMensaje.getDetalle();
					this.estado=elMensaje.getEstado();				
					if(elMensaje.isMensaje_sistema()){   // si es un mensaje enviado automaticamente por el sistema
						this.queNoSos="Emisor";
					}				
					if(this.getNotif()!=""){
						new NotificacionAction().cambiarEstadoAVisitado(this.notif);
					}
					
					return SUCCESS;
				}
				else{
					return "sinpermisos";
				}
			}		
			else {
				return "sinpermisos";
			}
		} else {
			return "login";
		}

	}
	*/
	
	
	
	public String responder(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		Usuario user = (Usuario) session.get("usrLogin");
		if (user != null) {
			if (user.getPerfil().equals("viajero")) {
				this.fecha=new Date();
				this.receptor=this.viajeroDAO.encontrar(Integer.parseInt(this.receptorID));
				this.emisor=user;
				this.estado="pendiente";
				Mensaje nuevoMensaje= new Mensaje (this.fecha,this.respDetalle,this.estado,this.emisor);
				this.mensajeDAO.registrar(nuevoMensaje);
				Mensaje elMensaje;
				elMensaje=this.mensajeDAO.encontrar(this.id);		
				if(elMensaje.getEstado().equals("leido")){
					elMensaje.setEstado("respondido");
					this.mensajeDAO.modificar(elMensaje);
				}
				return SUCCESS;
			}		
			else {
				return "sinpermisos";
			}
		} else {
			return "login";
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
				return "sinpermisos";
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
				ForoMensajes nuevoMensaje= new ForoMensajes (new Date(),(Viajero)user,this.detalle,viaje);
				this.foroMensajeDAO.registrar(nuevoMensaje);
				Collection<Viajero> lista=viaje.getPasajeros();
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
	public Mensaje crearMensaje(String detalleMensaje) {
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
