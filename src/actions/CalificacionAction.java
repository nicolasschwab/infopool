package actions;

import implementacionesDAO.CalificacionDAOjpa;
import implementacionesDAO.FactoryDAO;
import implementacionesDAO.ViajeDAOjpa;

import java.util.Date;
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
	private String idViaje;	
	private String idPasajero;
	private int calificacionnro;
	private Viajero calificador;
	private Viajero calificado;	
	private Viaje viaje;
	
	public CalificacionDAOjpa calificacionDAO;
	
	public String getIdViaje() {
		return idViaje;
	}
	public void setIdViaje(String idViaje) {
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
	public String getIdPasajero() {
		return idPasajero;
	}
	public void setIdPasajero(String idPasajero) {
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
			viaje = viajeDAO.encontrar(Integer.parseInt(this.idViaje));
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
			viaje = FactoryDAO.getViajeDAO().encontrar(Integer.parseInt(this.idViaje));
			calificado = FactoryDAO.getViajeroDAO().encontrar(Integer.parseInt(this.getIdPasajero()));
			calificador = (Viajero) usrlogueado;
			Calificacion calificacionAntigua=calificacionDAO.encontrarCalificacion(calificador,calificado,viaje);
			if(calificacionAntigua==null){//reviso si el usr loguado ya habia calificado a este usuario en este viaje				
				Calificacion calificacion = new Calificacion(calificador, calificado, this.calificacionnro,new Date(), viaje);
				calificacionDAO.registrar(calificacion);
				/*NotificacionAction notificacion=new NotificacionAction();
				notificacion.crearNotificacionCalificacion();*/
			}else{ //si ya lo habia calificado modifico la calificacion
				calificacionAntigua.setCalificacion(this.calificacionnro);
				calificacionDAO.modificar(calificacionAntigua);
			}
			this.actualizarCalificacion(calificado);
			new NotificacionAction().crearNotificacionCalificacion(calificado);
			return SUCCESS;
		}
		return resul;
	}
	
	private void actualizarCalificacion(Viajero calificado) throws Exception{
		List<Calificacion> calificaciones=calificacionDAO.listarCalificaciones(calificado.getId());
		int cant=calificaciones.size();
		int valor=0;
		for(Calificacion c: calificaciones){
			valor+=c.getCalificacion();
		}
		calificado.setCalificacion((valor/cant));
		FactoryDAO.getViajeroDAO().modificar(calificado);
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
