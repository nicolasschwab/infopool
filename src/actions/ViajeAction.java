package actions;

import implementacionesDAO.EventoDAOjpa;
import implementacionesDAO.FactoryDAO;
import implementacionesDAO.ViajeDAOjpa;
import interfacesDAO.ViajeDAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import model.DiasSemana;
import model.Evento;
import model.ForoMensajes;
import model.Usuario;
import model.Viaje;
import model.Viajero;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ViajeAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private int id;
	private String fechaInicio;
	private String fechaFin;
	private List<DiasSemana> diasSemana = new ArrayList<DiasSemana>();
	private String horaPartida;
	private String horaRegreso;
	private int asientos;
	private String direccionDestino;
	private String direccionOrigen;
	private int activo;
	private int evento_id;
	private int conductor_id;
	
	private String tipoViaje;
	private String fechaIniciop;
	private String fechaIniciod;
	private String[] categorias;
	private String rol;
	private List<Evento> eventoLista = new ArrayList<Evento>();
	private ViajeDAOjpa viajeDAO;
	private List<Viaje> viajeListaConductor = new ArrayList<Viaje>();
	private List<Viaje> viajeListaPasajero = new ArrayList<Viaje>();
	private List<Viaje> viajeLista;
	private int valor = -3;
	private int cantpasajeros;
	private int cantsolicitudes;
	private String diasviaje;
	private String fecha;
	private String dirOrigen;
	private String dirDestino;
	private Viaje viaje = new Viaje();	
	private Usuario usrlogueado;
	private List<ForoMensajes> foroMensajes;
	
	


	

	private boolean esPasajero;
	
	/*
	 * Setter y Getters
	 */
	public int getActivo() {
		return activo;
	}

	public void setActivo(int activo) {
		this.activo = activo;
	}
	
	public int getAsientos() {
		return asientos;
	}
	
	public List<ForoMensajes> getForoMensajes() {
		return foroMensajes;
	}

	public void setForoMensajes(List<ForoMensajes> foroMensajes) {
		this.foroMensajes = foroMensajes;
	}

	public void setAsientos(int asientos) {
		this.asientos = asientos;
	}

	public String getDireccionDestino() {
		return direccionDestino;
	}

	public void setDireccionDestino(String direccionDestino) {
		this.direccionDestino = direccionDestino;
	}

	public String getDireccionOrigen() {
		return direccionOrigen;
	}

	public void setDireccionOrigen(String direccionOrigen) {
		this.direccionOrigen = direccionOrigen;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getHoraPartida() {
		return horaPartida;
	}

	public void setHoraPartida(String horaPartida) {
		this.horaPartida = horaPartida;
	}

	public String getHoraRegreso() {
		return horaRegreso;
	}

	public void setHoraRegreso(String horaRegreso) {
		this.horaRegreso = horaRegreso;
	}

	public int getConductor_id() {
		return conductor_id;
	}

	public void setConductor_id(int conductor_id) {
		this.conductor_id = conductor_id;
	}

	public int getEvento_id() {
		return evento_id;
	}

	public void setEvento_id(int evento_id) {
		this.evento_id = evento_id;
	}
	
	public String getTipoViaje() {
		return tipoViaje;
	}

	public void setTipoViaje(String tipoViaje) {
		this.tipoViaje = tipoViaje;
	}
		
	public String getFechaIniciop() {
		return fechaIniciop;
	}

	public void setFechaIniciop(String fechaIniciop) {
		this.fechaIniciop = fechaIniciop;
	}

	public String getFechaIniciod() {
		return fechaIniciod;
	}

	public void setFechaIniciod(String fechaIniciod) {
		this.fechaIniciod = fechaIniciod;
	}

	public String[] getCategorias() {
		return categorias;
	}

	public void setCategorias(String[] categorias) {
		this.categorias = categorias;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public List<Evento> getEventoLista() {
		return eventoLista;
	}

	public void setEventoLista(List<Evento> eventoLista) {
		this.eventoLista = eventoLista;
	}

	public ViajeDAOjpa getViajeDAO() {
		return viajeDAO;
	}

	public void setViajeDAO(ViajeDAOjpa viajeDAO) {
		this.viajeDAO = viajeDAO;
	}

	public List<Viaje> getViajeListaConductor() {
		return viajeListaConductor;
	}

	public void setViajeListaConductor(List<Viaje> viajeListaConductor) {
		this.viajeListaConductor = viajeListaConductor;
	}

	public List<Viaje> getViajeListaPasajero() {
		return viajeListaPasajero;
	}

	public void setViajeListaPasajero(List<Viaje> viajeListaPasajero) {
		this.viajeListaPasajero = viajeListaPasajero;
	}

	public List<Viaje> getViajeLista() {
		return viajeLista;
	}

	public void setViajeLista(List<Viaje> viajeLista) {
		this.viajeLista = viajeLista;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public int getCantpasajeros() {
		return cantpasajeros;
	}

	public void setCantpasajeros(int cantpasajeros) {
		this.cantpasajeros = cantpasajeros;
	}

	public int getCantsolicitudes() {
		return cantsolicitudes;
	}

	public void setCantsolicitudes(int cantsolicitudes) {
		this.cantsolicitudes = cantsolicitudes;
	}

	public String getDiasviaje() {
		return diasviaje;
	}

	public void setDiasviaje(String diasviaje) {
		this.diasviaje = diasviaje;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getDirOrigen() {
		return dirOrigen;
	}

	public void setDirOrigen(String dirOrigen) {
		this.dirOrigen = dirOrigen;
	}

	public String getDirDestino() {
		return dirDestino;
	}

	public void setDirDestino(String dirDestino) {
		this.dirDestino = dirDestino;
	}

	public Viaje getViaje() {
		return viaje;
	}

	public void setViaje(Viaje viaje) {
		this.viaje = viaje;
	}

	public List<DiasSemana> getDiasSemana() {
		return diasSemana;
	}

	public void setDiasSemana(List<DiasSemana> diasSemana) {
		this.diasSemana = diasSemana;
	}

	public Usuario getUsrlogueado() {
		return usrlogueado;
	}

	public void setUsrlogueado(Usuario usrlogueado) {
		this.usrlogueado = usrlogueado;
	}
	
	public boolean getEsPasajero() {
		return esPasajero;
	}

	public void setEsPasajero(boolean esPasajero) {
		this.esPasajero = esPasajero;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}

	/*
	 * Metodos
	 */	
	public String viajes() {
		return SUCCESS;
	}
	
	public String buscarViaje() {
		return SUCCESS;
	}
	
	public String buscarFecha() {
		return SUCCESS;
	}

	public String registroViaje() {
		return SUCCESS;
	}

	public String registrarViaje() throws ParseException {
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
						if ((this.getHoraPartida().length() > 0) && (this.getHoraRegreso().length() > 0)){
							if (this.getAsientos() > 0){
								if ((this.getDireccionDestino().length() > 0) && (this.getDireccionOrigen().length() > 0)){				
									
									ViajeDAO viaje = FactoryDAO.getViajeDAO();
									SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");				
									Date fechai = null;
									if (this.getTipoViaje().equals("vp")){
										if (this.getFechaIniciop().length() > 0){
											fechai = sdf.parse(this.getFechaIniciop());
										}
									}
									else{
										if (this.getFechaIniciod().length() > 0){
											fechai = sdf.parse(this.getFechaIniciod());
										}
									}
									Date fechaf = null;
									if (this.getFechaFin().length() > 0){
										fechaf = sdf.parse(this.getFechaFin());
									}
									SimpleDateFormat sdh = new SimpleDateFormat("hh:mm");
									Date horap = sdh.parse(this.getHoraPartida());
									Date horar = null;
									if (this.getHoraRegreso().length() > 0){
										horar = sdh.parse(this.getHoraRegreso());
									}		
									Viajero conductor = (Viajero) session.get("usrLogin");
									Viaje v = new Viaje(fechai, fechaf, diasSemana, horap, horar, 
											this.getAsientos(), this.getDireccionOrigen(),
											this.getDireccionDestino(), null, conductor);				
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
							addFieldError("viajeError", "Debe seleccionar la hora de partida y de regreso en el que realizará el viaje");
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
				return "sinpermisos";
			}
		} else {
			return "login";
		}
	}
	
	public String edicionViaje(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		usrlogueado = (Usuario) session.get("usrLogin");
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		viaje = viajeDAO.encontrar(Integer.parseInt(request.getParameter("id")));		
		esPasajero = viaje.esPasajero(usrlogueado);
		return SUCCESS;
	}
	
	public String editarViaje() throws ParseException {
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
						if ((this.getHoraPartida().length() > 0) && (this.getHoraRegreso().length() > 0)){
							if (this.getAsientos() > 0){
								if ((this.getDireccionDestino().length() > 0) && (this.getDireccionOrigen().length() > 0)){
									ViajeDAO viaje = FactoryDAO.getViajeDAO();
									SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");				
									Date fechai = null;
									if (this.getTipoViaje().equals("vp")){
										if (this.getFechaIniciop().length() > 0){
											fechai = sdf.parse(this.getFechaIniciop());
										}
									}
									else{
										if (this.getFechaIniciod().length() > 0){
											fechai = sdf.parse(this.getFechaIniciod());
										}
									}
									Date fechaf = null;
									if (this.getFechaFin().length() > 0){
										fechaf = sdf.parse(this.getFechaFin());
									}
									SimpleDateFormat sdh = new SimpleDateFormat("hh:mm");
									Date horap = sdh.parse(this.getHoraPartida());
									Date horar = null;
									if (this.getHoraRegreso().length() > 0){
										horar = sdh.parse(this.getHoraRegreso());
									}
							
									Viaje v = viaje.encontrar(this.id);
									v.setAsientos(this.getAsientos());
									v.setFechaInicio(fechai);
									v.setFechaFin(fechaf);
									v.setDiasSemana(diasSemana);
									v.setHoraPartida(horap);
									v.setHoraRegreso(horar);
									v.setDireccionOrigen(this.getDireccionOrigen());
									v.setDireccionDestino(this.getDireccionDestino());
									viaje.modificar(v);				
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
							addFieldError("viajeError", "Debe seleccionar la hora de partida y de regreso en el que realizará el viaje");
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
				return "sinpermisos";
			}
		} else {
			return "login";
		}
		//FALTA LA PARTE DE AVISAR A LOS PASAJEROS DE LA MODIFICACION
	}
	
	public String asociacionEvento(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		usrlogueado = (Usuario) session.get("usrLogin");
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		viaje = viajeDAO.encontrar(Integer.parseInt(request.getParameter("id")));		
		eventoLista = FactoryDAO.getEventoDAO().listar();
		return SUCCESS;
	}
	
	public String asociarEvento(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		usrlogueado = (Usuario) session.get("usrLogin");
		viaje = viajeDAO.encontrar(this.id);
		EventoDAOjpa eventodao = new EventoDAOjpa();
		Evento evento = (Evento) eventodao.encontrar(this.evento_id);		
		//Actualizamos el viaje con los valores del evento
		viaje.setEvento(evento);
		viaje.setDiasSemana(null);
		viaje.setDireccionDestino(evento.getUbicacion());
		viaje.setFechaInicio(evento.getFechaHora());
		viaje.setFechaFin(null);
		viajeDAO.modificar(viaje);
		return SUCCESS;
	}
	
	public String cancelarViaje(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		usrlogueado = (Usuario) session.get("usrLogin");
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		viaje = viajeDAO.encontrar(Integer.parseInt(request.getParameter("id")));
		viaje.setActivo(false);
		viajeDAO.modificar(viaje);
		//FALTA LA PARTE DE AVISAR A LOS PASAJEROS DE LA CANCELACION
		return SUCCESS;
	}
	
	public String buscarViajePorEvento() {		
		EventoDAOjpa eventodao = new EventoDAOjpa();
		Evento evento = (Evento) eventodao.encontrar(this.evento_id);
		eventoLista = eventodao.listar();				
		viajeLista = (List<Viaje>) evento.getViajes();		
		return SUCCESS;			
	}
	
	public String listarPorFecha() throws ParseException {
		EventoDAOjpa unEvento = new EventoDAOjpa();
		eventoLista = unEvento.listar();
		if (this.getFecha() != null && !this.getFecha().equals("")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date fecha = sdf.parse(this.getFecha());
			ViajeDAOjpa unViaje = new ViajeDAOjpa();
			viajeLista = unViaje.buscarPorFecha(fecha);
			return SUCCESS;
		} else {
			addFieldError("loginError", "Por favor seleccione una fecha");
			return SUCCESS;
		}
	}

	public String listarPorDireccion() {
		EventoDAOjpa unEvento = new EventoDAOjpa();
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
	}

	public String listarViajes() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		Usuario user = (Usuario) session.get("usrLogin");
		viajeListaConductor = viajeDAO.listarViajesConductor(user);
		viajeListaPasajero = viajeDAO.listarViajesPasajero(user);
		return SUCCESS;
	}

	public String detalleViaje() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		usrlogueado = (Usuario) session.get("usrLogin");
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		viaje = viajeDAO.encontrar(Integer.parseInt(request.getParameter("id")));		
		esPasajero = viaje.esPasajero(usrlogueado);
		foroMensajes= (List<ForoMensajes>) viaje.getMensajes();
		return SUCCESS;
	}

	public String listarTodosLosViajes() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		Usuario user = (Usuario) session.get("usrLogin");
		EventoDAOjpa unEvento = new EventoDAOjpa();
		eventoLista = unEvento.listar();
		viajeLista = viajeDAO.listarViajesNoAsociados(user,unEvento.encontrar(this.valor));
		return SUCCESS;
	}

}
