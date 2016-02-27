package actions;

import implementacionesDAO.CalificacionDAOjpa;
import implementacionesDAO.FactoryDAO;
import implementacionesDAO.ViajeDAOjpa;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import model.Calificacion;
import model.Usuario;
import model.Viaje;
import model.Viajero;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class CalificacionAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	private List<Viajero> pasajeros;
	private int idCalificacion;
	private int idViaje;	
	private int idPasajero;
	private int calificacionnro;
	private Viajero calificador;
	private Viajero calificado;	
	private Viaje viaje;
	
	public CalificacionDAOjpa calificacionDAO;
	
	public int getIdViaje() {
		return idViaje;
	}
	public void setIdViaje(int idViaje) {
		this.idViaje = idViaje;
	}
	public List<Viajero> getPasajeros() {
		return pasajeros;
	}
	public void setPasajeros(List<Viajero> pasajeros) {
		this.pasajeros = pasajeros;
	}
	public CalificacionDAOjpa getCalificacionDAO() {
		return calificacionDAO;
	}
	public void setCalificacionDAO(CalificacionDAOjpa calificacionDAO) {
		this.calificacionDAO = calificacionDAO;
	}
	public int getIdCalificacion() {
		return idCalificacion;
	}
	public void setIdCalificacion(int idCalificacion) {
		this.idCalificacion = idCalificacion;
	}
	public int getIdPasajero() {
		return idPasajero;
	}
	public void setIdPasajero(int idPasajero) {
		this.idPasajero = idPasajero;
	}
	public int getCalificacionnro() {
		return calificacionnro;
	}
	public void setCalificacionnro(int calificacionnro) {
		this.calificacionnro = calificacionnro;
	}
	public Viajero getCalificador() {
		return calificador;
	}
	public void setCalificador(Viajero calificador) {
		this.calificador = calificador;
	}
	public Viajero getCalificado() {
		return calificado;
	}
	public void setCalificado(Viajero calificado) {
		this.calificado = calificado;
	}
	public Viaje getViaje() {
		return viaje;
	}
	public void setViaje(Viaje viaje) {
		this.viaje = viaje;
	}
	public String calificaciones(){
		String resul=this.validarSesion();
		if(resul==SUCCESS){
			Map<String, Object> session = ActionContext.getContext().getSession();
			Usuario user = (Usuario) session.get("usrLogin");			
			ViajeDAOjpa viajeDAO = new ViajeDAOjpa();
			viaje = viajeDAO.encontrar(this.idViaje);
			pasajeros=(List<Viajero>) viaje.obtenerPasajeros();
			pasajeros.add(viaje.getConductor());
			Iterator<Viajero> viajerosIterator = pasajeros.iterator();
			while(viajerosIterator.hasNext()) {
				Viajero unViajero= viajerosIterator.next();
				if(unViajero.getId()==user.getId()){
					viajerosIterator.remove();
				}
			}
		}
		return resul;
	}	
	
	public String calificarViaje() throws Exception{
		String resul=this.validarSesion();
		if(resul==SUCCESS){
			Map<String, Object> session = ActionContext.getContext().getSession();
			Usuario usrlogueado = (Usuario) session.get("usrLogin");
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			this.idViaje = Integer.parseInt(request.getParameter("idViaje"));
			viaje = FactoryDAO.getViajeDAO().encontrar(this.idViaje);
			calificado = FactoryDAO.getViajeroDAO().encontrar(Integer.parseInt(request.getParameter("idPasajero")));
			calificador = (Viajero) usrlogueado;
			//Calificacion calificacion = new Calificacion(calificador, calificado, this.calificacionnro, viaje);
			Calificacion calificacion = new Calificacion();
			calificacionDAO.registrar(calificacion);
			NotificacionAction notificacion=new NotificacionAction();
			notificacion.crearNotificacionCalificacion();
			return SUCCESS;
		}
		return resul;
	}
	
	private String validarSesion(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		Usuario user = (Usuario) session.get("usrLogin");
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
	
}
