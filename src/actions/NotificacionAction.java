package actions;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;


import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import implementacionesDAO.FactoryDAO;
import implementacionesDAO.NotificacionDAOjpa;
import interfacesDAO.UsuarioDAO;
import model.Notificacion;
import model.Usuario;
import model.Viaje;
import model.Viajero;

public class NotificacionAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private int id;
	private String mensaje;	
	private Viajero emisor;	
	private Viajero receptor;
	private Collection<Notificacion> notificacionesDelUsuario;
	private List<NotifiacionVista> valoresJson= new ArrayList<NotifiacionVista>();
	private Usuario usrLogueado;
	public NotificacionDAOjpa notificacionDAO=FactoryDAO.getNotificacionDAO();;
	private String[] argumentos= new String[3];

	public List<NotifiacionVista> getValoresJson() {
		return valoresJson;
	}
	
	public void setValoresJson(List<NotifiacionVista> valoresJson) {
		this.valoresJson = valoresJson;
	}
	
	public NotificacionDAOjpa getNotificacionDAO() {
		return notificacionDAO;
	}
	
	public void setNotificacionDAO(NotificacionDAOjpa notificacionDAO) {
		this.notificacionDAO = notificacionDAO;
	}
	
	public String[] getArgumentos() {
		return argumentos;
	}

	public void setArgumentos(String[] argumentos) {
		this.argumentos = argumentos;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getMensaje() {
		return mensaje;
	}
	
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	public Viajero getEmisor() {
		return emisor;
	}
	
	public void setEmisor(Viajero emisor) {
		this.emisor = emisor;
	}
	
	public Viajero getReceptor() {
		return receptor;
	}
	
	public void setReceptor(Viajero receptor) {
		this.receptor = receptor;
	}
	
	public Collection<Notificacion> getNotificacionesDelUsuario() {
		return notificacionesDelUsuario;
	}
	
	public void setNotificacionesDelUsuario(Collection<Notificacion> respuestaAjax) {
		this.notificacionesDelUsuario = respuestaAjax;
	}
	
	public Usuario getUsrLogueado() {
		return usrLogueado;
	}
	
	public void setUsrLogueado(Usuario usrLogueado) {
		this.usrLogueado =(Usuario) usrLogueado;
	}
	
	//Metodo que llama AJAX para refrescar las notificaciones
	public String misNotificaciones(){
		if(this.validarLogin()){
			this.setNotificacionesDelUsuario(notificacionDAO.ListarPorUsuario(this.getUsrLogueado()));
			this.prepararRespuestaAjax();
		}
		return SUCCESS;
	}
	
	//metodo ajax llamado solo cuando clickean el icono de notificaciones
	//este metodo ademas de listar las notificaciones actualiza su estado 
	//a "visto"
	public String misNotificacionesActualizar(){
		if(this.validarLogin()){			
			this.setNotificacionesDelUsuario(notificacionDAO.ListarPorUsuario(this.getUsrLogueado()));
			for (Notificacion notificacion: this.getNotificacionesDelUsuario() ) {
				if(notificacion.getEstado().getId()==1){
					notificacion.setEstado(FactoryDAO.getEstadoNotificacionDAO().traerVisto());
					notificacionDAO.modificar(notificacion);
				}
			}
			this.prepararRespuestaAjax();
		}
		return SUCCESS;
	}
	
	//Este metodo cambia el estado de una notificacion a Visitado
	//Solo si existe la notificacion en la BBDD y si esa notificacion
	//Le pertenece al ususario logueado
	public void cambiarEstadoAVisitado(String idNoti){
		if(this.validarLogin()){
			Notificacion notificacion=notificacionDAO.encontrarPorId(idNoti);
			if(notificacion!=null){
				if(notificacion.getReceptor().getId()==this.usrLogueado.getId()){
					notificacion.setEstado(FactoryDAO.getEstadoNotificacionDAO().traerVisitado());
					notificacionDAO.modificar(notificacion);
				}
			}
		}
	}
	//Metodo que crea una clase NotificacionVista por cada notificacion
	//que correspona al ususario logueado
	private void prepararRespuestaAjax(){
		for (Notificacion notificacion: this.getNotificacionesDelUsuario()) {
			//inner class (mas abajo)
			NotifiacionVista unaNotificacion=new NotifiacionVista(notificacion.getEmisor().getId(),notificacion.getMensaje(),notificacion.getLink(),notificacion.getTipo(),notificacion.getHora().toString(),notificacion.getEstado().getTexto());
			//seteo la variable que es capturada en la vista
			this.getValoresJson().add(unaNotificacion);
		}	
	}
	
	private boolean validarLogin(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		String user = (String) session.get("perfil");
		if (user == null) {
			return false;
		}
		this.setUsrLogueado((Usuario) session.get("usrLogin"));
		return true;
	}
	
	//Mensajes para crear notificaciones
	//El link debe apuntar a un viaje
	public boolean crearNotificacionComentario(Usuario receptor,Viaje elViaje){
		if(this.validarLogin()){
			this.argumentos[0]=this.getUsrLogueado().getUsuario();
			this.argumentos[1]=elViaje.getDireccionDestino();			
			this.setMensaje(getText("notificacion.mensaje.viaje.comentario",this.argumentos));		
			this.crearRegistrarNotificacion(receptor, "detalleViaje.action?id="+elViaje.getId(),"ban");
			return true;
		}else{
			return false;
		}
	}
	
	//El link debe apuntar a un viaje
	public boolean crearNotificacionSalida(Usuario receptor,Viaje elViaje){
		if(this.validarLogin()){
			this.argumentos[0]=this.getUsrLogueado().getUsuario();
			this.argumentos[1]=elViaje.getDireccionDestino();			
			this.setMensaje(getText("notificacion.mensaje.viaje.salida",this.argumentos));		
			this.crearRegistrarNotificacion(receptor, "detalleViaje.action?id="+elViaje.getId(),"comment");
			return true;
		}else{
			return false;
		}
	}
	
	//El link debe apuntar a un viaje
	public boolean crearNotificacionModificacionViaje(Usuario receptor,Viaje elViaje){
		if(this.validarLogin()){
			this.argumentos[0]=this.getUsrLogueado().getUsuario();
			this.argumentos[1]=elViaje.getDireccionDestino();				
			this.setMensaje(getText("notificacion.mensaje.viaje.modificacion",this.argumentos));		
			this.crearRegistrarNotificacion(receptor, "detalleViaje.action?id="+elViaje.getId(),"pencil");
			return true;
		}else{
			return false;
		}
	}
	
	public boolean crearNotificacionCancelarViaje(Usuario receptor,Viaje elViaje){
		if(this.validarLogin()){
			this.argumentos[0]=this.getUsrLogueado().getUsuario();
			this.argumentos[1]=elViaje.getDireccionDestino();				
			this.setMensaje(getText("notificacion.mensaje.viaje.cancelar",this.argumentos));
			Notificacion notificacion=this.crearNotificacion(receptor, "#","ban");
			this.registrarNotificacion(notificacion);
			return true;
		}else{
			return false;
		}
	}

	public boolean crearNotificacionCalificacion(){
		//para no mostrar quien califico a quien, el usuario que se muestre
		//en la notificacion sera un usuario que represente al sistema
		if(this.validarLogin()){
			this.setMensaje(getText("notificacion.mensaje.calificacion"));
			UsuarioDAO usuarioDAO=FactoryDAO.getUsuarioDAO();
			this.setReceptor((Viajero)usuarioDAO.encontrarUsuarioSistema());
			Notificacion notificacion=this.crearNotificacion(this.getReceptor(), "miPerfil","star");
			this.registrarNotificacion(notificacion);
			return true;
		}else{
			return false;
		}
	}
	
	//El link debe apuntar a un viaje
	public boolean crearNotificacionSolicitudAceptar(Usuario receptor,Viaje elViaje){
		if(this.validarLogin()){
			this.argumentos[0]=this.getUsrLogueado().getUsuario();
			this.argumentos[1]=elViaje.getDireccionOrigen().split(",")[0];
			this.argumentos[2]=elViaje.getDireccionDestino().split(",")[0];		
			this.setMensaje(getText("notificacion.mensaje.solicitud.aceptar",this.argumentos));
			this.crearRegistrarNotificacion(receptor, "detalleViaje.action?id="+elViaje.getId(),"check-circle");
			return true;
		}else{
			return false;
		}
	}
	
	//El link debe apuntar a un viaje
	public boolean crearNotificacionRechazoSolicitud(Usuario receptor,Viaje elViaje){
		if(this.validarLogin()){
			this.argumentos[0]=this.getUsrLogueado().getUsuario();			
			this.argumentos[1]=elViaje.getDireccionOrigen().split(",")[0];
			this.argumentos[2]=elViaje.getDireccionDestino().split(",")[0];
			this.setMensaje(getText("notificacion.mensaje.solicitud.rechazar",this.argumentos));
			this.crearRegistrarNotificacion(receptor, "detalleViaje.action?id="+elViaje.getId(),"times");			
			return true;
		}else{
			return false;
		}
	}
	
	//El link debe apuntar a un viaje
	//tiene solo un parametro porque el receptor es el conductor del viaje
	public boolean crearNotificacionSolicitudNueva(Viaje elViaje){
		if(this.validarLogin()){
			this.argumentos[0]=this.getUsrLogueado().getUsuario();
			this.argumentos[1]=elViaje.getDireccionOrigen().split(",")[0];
			this.argumentos[2]=elViaje.getDireccionDestino().split(",")[0];
			this.setMensaje(getText("notificacion.mensaje.solicitud.nueva",this.argumentos));			
			this.crearRegistrarNotificacion(elViaje.getConductor(), "solicitudesViaje.action?id="+elViaje.getId(),"flag-o");
			return true;
		}else{
			return false;
		}
	}
	
	//El link debe apuntar a un mensaje
	public boolean crearNotificacionPrivado(Usuario receptor,int idMensaje){
		if(this.validarLogin()){
			this.argumentos[0]=this.getUsrLogueado().getUsuario();
			this.setMensaje(getText("notificacion.mensaje.privado",this.argumentos));
			this.crearRegistrarNotificacion(receptor, "detalle.action?id="+idMensaje,"comment");
		return true;
		}else{
			return false;
		}
	}
	
	//Mensajes privados genericos para reusar codigo
	private void crearRegistrarNotificacion(Usuario receptor, String link,String tipo){
		Notificacion notificacion=this.crearNotificacion(receptor, link, tipo);
		this.registrarNotificacion(notificacion);
	}
	
	private void registrarNotificacion(Notificacion notificacion){
		FactoryDAO.getUsuarioDAO().modificar(notificacion.getEmisor());
		FactoryDAO.getUsuarioDAO().modificar(notificacion.getReceptor());
		this.getNotificacionDAO().registrar(notificacion);
	}
	
	//Metodo que finalmente crea una notificacion
	private Notificacion crearNotificacion(Usuario receptor, String link,String elTipo){
		Notificacion notificacion=new Notificacion();
		notificacion.setId(UUID.randomUUID().toString().replaceAll("-",""));
		notificacion.setEmisor(this.getUsrLogueado());
		notificacion.setReceptor(receptor);
		notificacion.setTipo(elTipo);
		notificacion.setLink((link+"&notif="+notificacion.getId()));
		notificacion.setHora(new Date());
		notificacion.setMensaje(this.getMensaje());		
		notificacion.setEstado(FactoryDAO.getEstadoNotificacionDAO().traerNoVisto());
		return notificacion;
	}
	
	public String redireccion(){
		
		return SUCCESS;
	}
	//Inner class que es de la cual se levantan lon datos en la vista
	public class NotifiacionVista{
		int idEmisor;
		String mensaje;
		String link;
		String tipo;
		String fecha;
		String estado;
		
		NotifiacionVista(int id,String elMensaje,String elLink,String elTipo,String laFecha,String elEstado){
			Map<String, Object> session = ActionContext.getContext().getSession();
			String user = (String) session.get("perfil");
			if(user!=null){
			this.idEmisor=id;
			this.mensaje=elMensaje;
			this.link=elLink;
			this.tipo=elTipo;
			this.fecha=laFecha.substring(0,10);
			this.estado=elEstado;
			}
		}
		public int getIdEmisor() {
			return idEmisor;
		}
		public void setIdEmisor(int idEmisor) {
			this.idEmisor = idEmisor;
		}
		public String getMensaje() {
			return mensaje;
		}
		public void setMensaje(String mensaje) {
			this.mensaje = mensaje;
		}
		public String getLink() {
			return link;
		}
		public void setLink(String link) {
			this.link = link;
		}
		public String getTipo() {
			return tipo;
		}
		public void setTipo(String tipo) {
			this.tipo = tipo;
		}
		public String getFecha() {
			return fecha;
		}
		public void setFecha(String fecha) {
			this.fecha = fecha.substring(0,9);
		}
		public String getEstado() {
			return estado;
		}
		public void setEstado(String estado) {
			this.estado = estado;
		}
		
		
	}
}
