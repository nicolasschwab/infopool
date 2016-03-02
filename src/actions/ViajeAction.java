package actions;

import interfacesDAO.FrecuenciaViajeDAO;
import interfacesDAO.ViajeDAO;
import interfacesDAO.ViajeroDAO;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import model.Usuario;
import model.Viaje;
import model.Viajero;
import util.SessionUtil;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ViajeAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	private int id;
	
	/* DATOS FORMULARIO */
	private String direccionOrigen;
	private String direccionDestino;
	private String fechaViaje;
	private String ubicacionLocal;
	private String evento;
	
	/* */ 
	private ViajeDAO viajeDAO;
	private ViajeroDAO viajeroDAO;
	private FrecuenciaViajeDAO frecuenciaViajeDAO;
	private Viaje viaje;
	private Viajero viajero;
	public Usuario user;
	
	/* DATOS VISTAS */	
	private List<Viaje> listaViajes = null;
	private boolean soyViajero;
	private boolean soyConductor;
	private boolean soyPasajero;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDireccionOrigen() {
		return direccionOrigen;
	}
	public void setDireccionOrigen(String direccionOrigen) {
		this.direccionOrigen = direccionOrigen;
	}
	public String getDireccionDestino() {
		return direccionDestino;
	}
	public void setDireccionDestino(String direccionDestino) {
		this.direccionDestino = direccionDestino;
	}
	public String getFechaViaje() {
		return fechaViaje;
	}
	public void setFechaViaje(String fechaViaje) {
		this.fechaViaje = fechaViaje;
	}
	public String getUbicacionLocal() {
		return ubicacionLocal;
	}
	public void setUbicacionLocal(String ubicacionLocal) {
		this.ubicacionLocal = ubicacionLocal;
	}
	public String getEvento() {
		return evento;
	}
	public void setEvento(String evento) {
		this.evento = evento;
	}
		
	public ViajeDAO getViajeDAO() {
		return viajeDAO;
	}
	public void setViajeDAO(ViajeDAO viajeDAO) {
		this.viajeDAO = viajeDAO;
	}
	public ViajeroDAO getViajeroDAO() {
		return viajeroDAO;
	}
	public void setViajeroDAO(ViajeroDAO viajeroDAO) {
		this.viajeroDAO = viajeroDAO;
	}
	public FrecuenciaViajeDAO getFrecuenciaViajeDAO() {
		return frecuenciaViajeDAO;
	}
	public void setFrecuenciaViajeDAO(FrecuenciaViajeDAO frecuenciaViajeDAO) {
		this.frecuenciaViajeDAO = frecuenciaViajeDAO;
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
	public Usuario getUser() {
		return user;
	}
	public void setUser(Usuario user) {
		this.user = user;
	}	
	public List<Viaje> getListaViajes() {
		return listaViajes;
	}
	public void setListaViajes(List<Viaje> listaViajes) {
		this.listaViajes = listaViajes;
	}	
	
	
	public boolean getSoyViajero() {
		return soyViajero;
	}
	public void setSoyViajero(boolean soyViajero) {
		this.soyViajero = soyViajero;
	}	
	public boolean getSoyConductor() {
		return soyConductor;
	}
	public void setSoyConductor(boolean soyConductor) {
		this.soyConductor = soyConductor;
	}
	public boolean getSoyPasajero() {
		return soyPasajero;
	}
	public void setSoyPasajero(boolean soyPasajero) {
		this.soyPasajero = soyPasajero;
	}
	
	public String BusquedaViaje() {
		if (SessionUtil.checkLogin()){
			listaViajes = viajeDAO.obtenerUltimosViajesBusqueda();			
			return SUCCESS;
		}
		return "sinPermisos";
	}
	
	public String DetalleViaje() throws Exception {
		if (SessionUtil.checkLogin()){
			viajero = (Viajero) SessionUtil.getUsuario();						
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			if(request.getParameter("id") != null){
				viaje = viajeDAO.encontrarPorId(Integer.parseInt(request.getParameter("id")));
			}
			else{
				System.out.println("viaje id de la sesion");
				//viaje = viajeDAO.encontrarPorId(Integer.parseInt(session.get("id").toString()));				
			}								
			/*if(this.getNotif()!=""){
				new NotificacionAction().cambiarEstadoAVisitado(this.notif);
			}*/
			soyPasajero = viaje.esPasajero(viajero);
			soyConductor = viaje.esConductor(viajero);
			soyViajero = (!soyPasajero || !soyConductor);			
			return SUCCESS;
		}else{
			return "sinPermisos";
		}
	}
	
	/*
	public String registrarViaje() throws Exception {
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		String[] categorias = request.getParameterValues("viajediario");
		diasSemana = new ArrayList<DiasSemana>();
		if (categorias != null && categorias.length > 0) {
			for (int i = 0; i < categorias.length; i++) {
				diasSemana.add(DiasSemana.valueOf(categorias[i]));
			}
		}			
		Map<String, Object> session = ActionContext.getContext().getSession();
		String user = (String) session.get("perfil");
		if (user != null) {
			if (user.equals("viajero")) {
				if (((this.getTipoViaje().equals("vp")) && (this.getFechaIniciop().length() > 0)) || ((this.getTipoViaje().equals("vd")) && (this.getFechaIniciod().length() > 0))){
					if ( this.getTipoViaje().equals("vp") || ((this.getTipoViaje().equals("vd")) && (this.getDiasSemana().size() > 0))){
						if ((this.getHoraPartida().length() > 0) || ((this.getHoraPartida().length() > 0) && (this.getHoraRegreso().length() > 0))){
							if (this.getAsientos() > 0){
								if ((this.getDireccionDestino().length() > 0) && (this.getDireccionOrigen().length() > 0)){	 	
									ViajeDAO viaje = FactoryDAO.getViajeDAO();
									SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
									Date fechai = null;
									Date fechaf = null;
									if (this.getTipoViaje().equals("vp")){
										if (this.getFechaIniciop().length() > 0){
											fechai = sdf.parse(this.getFechaIniciop());
										}
										this.diasSemana = null;										
									}
									else{
										if (this.getFechaIniciod().length() > 0){
											fechai = sdf.parse(this.getFechaIniciod());
										}										
										if (this.getFechaFin().length() > 0){
											fechaf = sdf.parse(this.getFechaFin());
										}
									}									
									SimpleDateFormat sdh = new SimpleDateFormat("hh:mm");
									Date horap = sdh.parse(this.getHoraPartida());
									Date horar = null;
									if (this.getHoraRegreso().length() > 0){
										horar = sdh.parse(this.getHoraRegreso());
									}		
									Viajero conductor = (Viajero) session.get("usrLogin");
									Viaje v = new Viaje();				
									viaje.registrar(v);				
									return SUCCESS;
								}
								else{
									addFieldError("viajeError", "Debe completar las direcciones de origen y destino");
									return INPUT;
								}
							}
							else{
								addFieldError("viajeError", "Debe ingresar una cantidad de asientos mayor a 0");
								return INPUT;
							}
						}
						else{
							addFieldError("viajeError", "Debe seleccionar la hora de partida en la que realizará el viaje");
							return INPUT;
						}
					}
					else{
						addFieldError("viajeError", "Debe seleccionar al menos un día de la semana");
						return INPUT;
					}
				}
				else{
					addFieldError("viajeError", "Debe completar la fecha de inicio");
					return INPUT;
				}
			} else {
				return "sinPermisos";
			}
		} else {
			return "login";
		}
	}
	
	public String edicionViaje() throws NumberFormatException, Exception{
		if(this.validarSesion()){	
			Map<String, Object> session = ActionContext.getContext().getSession();
			usrlogueado = (Viajero) session.get("usrLogin");
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			viaje = viajeDAO.encontrar(Integer.parseInt(request.getParameter("id")));		
			//esPasajero = viaje.esPasajero(usrlogueado);
			return SUCCESS;
		}else{
			return "sinPermisos";
		}
	}
	
	public String editarViaje() throws ParseException {					
		Map<String, Object> session = ActionContext.getContext().getSession();
		String user = (String) session.get("perfil");
		
		ViajeDAO viajeDao = FactoryDAO.getViajeDAO();
		viaje = viajeDao.encontrar(this.id);
		
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		String[] categorias = request.getParameterValues("viajediario");
		diasSemana = new ArrayList<DiasSemana>();	
		if (categorias != null && categorias.length > 0) {
			for (int i = 0; i < categorias.length; i++) {
				diasSemana.add(DiasSemana.valueOf(categorias[i]));
			}
		}
		
		if (this.getTipoViaje() == null){
			this.tipoViaje = "ev";
			this.fechaIniciop = "";
			this.fechaIniciod = "";
		}
		if (user != null) {
			if (user.equals("viajero")) {				
				if ((this.evento_id != 0) || ((this.getTipoViaje().equals("vp")) && (this.getFechaIniciop().length() > 0)) || ((this.getTipoViaje().equals("vd")) && (this.getFechaIniciod().length() > 0))){
					if ( this.getTipoViaje().equals("vp") || ((this.getTipoViaje().equals("vd")) && (this.getDiasSemana().size() > 0)) || (this.evento_id != 0)){
						if ((this.getHoraPartida().length() > 0) || ((this.getHoraPartida().length() > 0) && (this.getHoraRegreso().length() > 0))){
							if (this.getAsientos() > 0){
								if (((this.evento_id != 0) && (this.getDireccionOrigen().length() > 0)) || ((this.getDireccionDestino().length() > 0) && (this.getDireccionOrigen().length() > 0))){
									if (this.evento_id == 0){
										SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");				
										Date fechai = null;
										Date fechaf = null;
										if (this.getTipoViaje().equals("vp")){
											if (this.getFechaIniciop().length() > 0){
												fechai = sdf.parse(this.getFechaIniciop());
											}
											this.diasSemana = null;	
										}
										else{
											if (this.getFechaIniciod().length() > 0){
												fechai = sdf.parse(this.getFechaIniciod());
											}
											if (this.getFechaFin().length() > 0){
												fechaf = sdf.parse(this.getFechaFin());
											}
										}
										viaje.setFechaInicio(fechai);
										viaje.setFechaFin(fechaf);
										viaje.setDiasSemana(diasSemana);
										viaje.setDireccionDestino(this.getDireccionDestino());
									}									
									SimpleDateFormat sdh = new SimpleDateFormat("hh:mm");
									Date horap = sdh.parse(this.getHoraPartida());
									Date horar = null;
									if (this.getHoraRegreso().length() > 0){
										horar = sdh.parse(this.getHoraRegreso());
									}
									viaje.setAsientos(this.getAsientos());									
									viaje.setHoraPartida(horap);
									viaje.setHoraRegreso(horar);
									viaje.setDireccionOrigen(this.getDireccionOrigen());									
									viajeDao.modificar(viaje);
									NotificacionAction notificacion=new NotificacionAction();
									for(Viajero pasajero:viaje.getPasajeros()){
										notificacion.crearNotificacionModificacionViaje(pasajero, viaje);
									}									
									return SUCCESS;
								}
								else{
									addFieldError("viajeError", "Debe completar las direcciones de origen y destino");
									return INPUT;
								}
							}
							else{
								addFieldError("viajeError", "Debe ingresar una cantidad de asientos mayor a 0");
								return INPUT;
							}
						}
						else{
							addFieldError("viajeError", "Debe seleccionar la hora de partida en la que realizará el viaje");
							return INPUT;
						}
					}
					else{
						addFieldError("viajeError", "Debe seleccionar al menos un día de la semana");
						return INPUT;
					}
				}
				else{					
					addFieldError("viajeError", "Debe completar la fecha de inicio");
					return INPUT;
				}
			} else {
				return "sinPermisos";
			}
		} else {
			return "login";
		}
	}
	
	public String asociacionEvento() throws NumberFormatException, Exception{
		if(this.validarSesion()){
			Map<String, Object> session = ActionContext.getContext().getSession();
			usrlogueado = (Viajero) session.get("usrLogin");
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			viaje = viajeDAO.encontrar(Integer.parseInt(request.getParameter("id")));		
			eventoLista = FactoryDAO.getEventoDAO().listar();
			return SUCCESS;
		}else{
			return "sinPermisos";
		}
	}
	
	public String asociarEvento() throws Exception{
		if(this.validarSesion()){		
			Map<String, Object> session = ActionContext.getContext().getSession();
			usrlogueado = (Viajero) session.get("usrLogin");
			viaje = viajeDAO.encontrar(this.id);
			EventoDAO eventodao = FactoryDAO.getEventoDAO();
			Evento evento = (Evento) eventodao.encontrar(this.evento_id);		
			//Actualizamos el viaje con los valores del evento
			viaje.setEventoAsociado(evento);
			//viaje.setDiasSemana(null);
			viaje.setDireccionDestino(evento.getUbicacion());
			viaje.setFechaInicio(evento.getFecha());
			viaje.setFechaFin(null);
			viajeDAO.modificar(viaje);
			return SUCCESS;
		}else{
			return "sinPermisos";
		}
	}
	
	public String cancelarViaje() throws NumberFormatException, Exception{
		if(this.validarSesion()){		
			Map<String, Object> session = ActionContext.getContext().getSession();
			usrlogueado = (Viajero) session.get("usrLogin");
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			viaje = viajeDAO.encontrar(Integer.parseInt(request.getParameter("id")));
			viaje.setActivo(false);
			viajeDAO.modificar(viaje);
			NotificacionAction notificacion=new NotificacionAction();
			for (Iterator<Viajero> i = viaje.obtenerPasajeros().iterator(); i.hasNext(); ){
				Viajero elViajero=i.next();			
				notificacion.crearNotificacionModificacionViaje(elViajero, viaje);
			}
			return SUCCESS;
		}else{
			return "sinPermisos";
		}
	}
	
	public String buscarViajePorEvento() throws Exception {
		if(this.validarSesion()){		
			EventoDAO eventodao = FactoryDAO.getEventoDAO();
			if (this.evento_id != 0){
				Evento evento = (Evento) eventodao.encontrar(this.evento_id);
				eventoLista = eventodao.listar();				
				viajeLista = (List<Viaje>) evento.getViajesAsociados();
			}
			else{
				addFieldError("loginError", "No existen eventos");			
			}
			return SUCCESS;
		}else{
			return "sinPermisos";
		}
	}
	
	public String listarPorFecha() throws Exception {
		if(this.validarSesion()){			
			EventoDAO unEvento = FactoryDAO.getEventoDAO();
			eventoLista = unEvento.listar();
			if (this.getFecha() != null && !this.getFecha().equals("")) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date fecha = sdf.parse(this.getFecha());
				ViajeDAO unViaje = FactoryDAO.getViajeDAO();
				viajeLista = unViaje.buscarPorFecha(fecha);
				return SUCCESS;
			} else {
				addFieldError("loginError", "Por favor seleccione una fecha");
				return SUCCESS;
			}
		}else{
			return "sinPermisos";
		}
	}

	public String listarPorDireccion() throws Exception {
		if(this.validarSesion()){			
			EventoDAO unEvento = FactoryDAO.getEventoDAO();
			eventoLista = unEvento.listar();
			if (this.direccionOrigen != null && !this.direccionOrigen.equals("")) {
				if (this.direccionDestino != null && !this.direccionDestino.equals("")) {
					viajeLista = this.viajeDAO.buscarPorDireccion(this.getDireccionOrigen(), this.getDireccionDestino());
				} else {
					addFieldError("loginError", "Por favor ingrese la direccion destino");
				}
			} else {
				addFieldError("loginError", "Por favor ingrese la direccion origen");
			}
			return SUCCESS;
		}else{
			return "sinPermisos";
		}
	}

	public String listarViajes() {
		if(this.validarSesion()){		
			Map<String, Object> session = ActionContext.getContext().getSession();
			Usuario user = (Usuario) session.get("usrLogin");
			viajeListaConductor = viajeDAO.listarViajesConductor(user);
			viajeListaPasajero = viajeDAO.listarViajesPasajero(user);
			return SUCCESS;
		}else{
			return "sinPermisos";
		}
	}

	

	public String listarTodosLosViajes() throws Exception {
		if(this.validarSesion()){		
			Map<String, Object> session = ActionContext.getContext().getSession();
			Usuario user = (Usuario) session.get("usrLogin");
			EventoDAO unEvento = FactoryDAO.getEventoDAO();
			eventoLista = unEvento.listar();
			viajeLista = viajeDAO.listarViajesNoAsociados(user,unEvento.encontrar(this.valor));
			return SUCCESS;
		}else{
			return "sinPermisos";
		}
	}

	public boolean validarPertenece(int viajeId, Viajero receptor) {
		if(this.validarSesion()){		
			Viaje viaje=FactoryDAO.getViajeDAO().encontrarPorId(viajeId);
			for(Viajero viajero :viaje.obtenerPasajeros()){
				if(viajero.getId()==receptor.getId()){
					return true;
				}
			}
			if(viaje.getConductor().getId()==receptor.getId()){
				return true;
			}
			return false;
		}else{
			return false;
		}
	}
	
	public String cancelarSubscripcionViaje() throws Exception{
		if(this.validarSesion()){
			if(this.validarPertenece(this.getId(), (Viajero)this.getUsrlogueado())){
				Viaje viaje =this.getViajeDAO().encontrarPorId(this.getId());
				if(new SolicitudViajeAction().eliminarSolicitud(viaje,(Viajero)this.getUsrlogueado())){
					List<Viajero> pasajeros=(List<Viajero>) viaje.obtenerPasajeros();
					for(Viajero viajero :pasajeros){
						if(viajero.getId()== ((Viajero)this.getUsrlogueado()).getId()){
							viaje.obtenerPasajeros().remove(viajero);
						}
					}
					this.getViajeDAO().modificar(viaje);
					return SUCCESS;
				}
				return "sinPermisos";				
			}
			return "sinPermisos";
		}else{
			return "sinPermisos";
		}
	}

	private boolean validarSesion(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		this.setUsrlogueado(null);
		this.setUsrlogueado((Usuario) session.get("usrLogin"));
		if(this.getUsrlogueado()==null){
			return false;
		}
		return true;
	}*/
}
