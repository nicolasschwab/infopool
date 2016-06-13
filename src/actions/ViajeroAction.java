package actions;

import interfacesDAO.SolicitudViajeDAO;
import interfacesDAO.ViajeDAO;
import interfacesDAO.ViajeroDAO;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.SolicitudViaje;
import model.Usuario;
import model.Viaje;
import model.Viajero;

import org.apache.struts2.ServletActionContext;

import util.Generics;
import util.SessionUtil;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ViajeroAction extends ActionSupport{
	
	private static final long serialVersionUID = 1L;

	private ViajeroDAO viajeroDAO;
	private ViajeDAO viajeDAO;
	private SolicitudViajeDAO solicitudViajeDAO;
	private Viajero viajero;
	private Usuario user;	
	
	/* Listados utilizados en la vista */
	private List<Viajero> listaViajeros = null;	
	private List<Viaje> listaViajesConductor = null;
	private List<Viaje> listaViajesPasajero = null;
	private List<Viaje> listaUltimosViajes = null;
	private List<SolicitudViaje> listaSolicitudesViajes = null;
	
	
	public ViajeroDAO getViajeroDAO() {
		return viajeroDAO;
	}
	public void setViajeroDAO(ViajeroDAO viajeroDAO) {
		this.viajeroDAO = viajeroDAO;
	}
	public ViajeDAO getViajeDAO() {
		return viajeDAO;
	}
	public void setViajeDAO(ViajeDAO viajeDAO) {
		this.viajeDAO = viajeDAO;
	}
	public SolicitudViajeDAO getSolicitudViajeDAO() {
		return solicitudViajeDAO;
	}
	public void setSolicitudViajeDAO(SolicitudViajeDAO solicitudViajeDAO) {
		this.solicitudViajeDAO = solicitudViajeDAO;
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
	public List<Viajero> getListaViajeros() {
		return listaViajeros;
	}
	public void setListaViajeros(List<Viajero> listaViajeros) {
		this.listaViajeros = listaViajeros;
	}
	public List<Viaje> getListaViajesConductor() {
		return listaViajesConductor;
	}
	public void setListaViajesConductor(List<Viaje> listaViajes) {
		this.listaViajesConductor = listaViajes;
	}
	public List<Viaje> getListaViajesPasajero() {
		return listaViajesPasajero;
	}
	public void setListaViajesPasajero(List<Viaje> listaViajesPasajero) {
		this.listaViajesPasajero = listaViajesPasajero;
	}
	public List<Viaje> getListaUltimosViajes() {
		return listaUltimosViajes;
	}
	public void setListaUltimosViajes(List<Viaje> listaUltimosViajes) {
		this.listaUltimosViajes = listaUltimosViajes;
	}
	public List<SolicitudViaje> getListaSolicitudesViajes() {
		return listaSolicitudesViajes;
	}
	public void setListaSolicitudesViajes(
			List<SolicitudViaje> listaSolicitudesViajes) {
		this.listaSolicitudesViajes = listaSolicitudesViajes;
	}
	
	
	private String validarSesionAdmin(){		
		if (SessionUtil.checkLogin()) {	
			if (SessionUtil.getUsuario().soyAdministrador()) {
				return SUCCESS;
			}else{
				return "sinPermisos";
			}
		}
		else{
			return "login";
		}
	}
	
	public String viajeros() throws Exception{
		String tienePermisosAdmin = validarSesionAdmin();
		if(tienePermisosAdmin == SUCCESS){
			listaViajeros = viajeroDAO.listar();
		}
		return tienePermisosAdmin;
	}
	
	public String misViajes() throws Exception{		
		if (SessionUtil.checkLogin()){			
			listaViajesConductor = Generics.getGenericViajeAction().viajesUsuarioConductor();
			listaViajesPasajero = Generics.getGenericViajeAction().viajesUsuarioViajero();	
			//HISTORIAL DE VIAJES!!	
			return SUCCESS;
		}
		return "sinPermisos";
	}
	
	public String ViajesPasajero() throws Exception{		
		if (SessionUtil.checkLogin()){			
			viajero = (Viajero)SessionUtil.getUsuario();			
			listaViajesConductor = viajeDAO.obtenerViajesPasajeroEstado(viajero, true);			
			return SUCCESS;
		}
		return "sinPermisos";
	}
	
	public String HistorialViajes() throws Exception{
		if (SessionUtil.checkLogin()){			
			viajero = (Viajero)SessionUtil.getUsuario();			
			listaViajesConductor = viajeDAO.obtenerHistorialViajesEstado(viajero);			
			return SUCCESS;
		}
		return "sinPermisos";
	}
	
	public String Solicitudes() throws Exception{		
		if(SessionUtil.checkLogin()){
			user = SessionUtil.getUsuario();
			viajero = (Viajero)SessionUtil.getUsuario();
			listaSolicitudesViajes = solicitudViajeDAO.obtenerUltimasSolicitudes(viajero);
			return SUCCESS;						
		}	
		return "sinPermisos";
	}
	
	/* -- METODOS POR REVISAR -- */
	public String detalleViajero() throws NumberFormatException, Exception{
		String tienePermisosAdmin=validarSesionAdmin();
		if(tienePermisosAdmin==SUCCESS){
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			viajero = viajeroDAO.encontrar(Integer.parseInt(request.getParameter("id")));
		}
		return tienePermisosAdmin;
	}
	
	public String desactivarViajero() throws NumberFormatException, Exception{
		String tienePermisosAdmin=validarSesionAdmin();
		if(tienePermisosAdmin==SUCCESS){	
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			viajero = viajeroDAO.encontrar(Integer.parseInt(request.getParameter("id")));
			viajero.setActivo(false);
			viajeroDAO.modificar(viajero);
		}
		return tienePermisosAdmin;
	}
	
	public String activarViajero() throws NumberFormatException, Exception{
		String tienePermisosAdmin=validarSesionAdmin();
		if(tienePermisosAdmin==SUCCESS){		
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			viajero = viajeroDAO.encontrar(Integer.parseInt(request.getParameter("id")));
			viajero.setActivo(true);
			viajeroDAO.modificar(viajero);
		}
		return tienePermisosAdmin;
	}

}