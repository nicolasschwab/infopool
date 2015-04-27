package actions;

import implementacionesDAO.CalificacionDAOjpa;
import implementacionesDAO.FactoryDAO;
import implementacionesDAO.ViajeDAOjpa;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Collection;

import model.Usuario;
import model.Viaje;
import model.Viajero;





import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class CalificacionAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	private List<Viajero> pasajeros= new ArrayList<Viajero>();
	private int idViaje;	

	public CalificacionDAOjpa calificacionDAO;
	
	
	
	
	public int getIdViaje() {
		return idViaje;
	}

	public void setIdViaje(int idViaje) {
		this.idViaje = idViaje;
	}

	public CalificacionDAOjpa getCalificacionDAO() {
		return calificacionDAO;
	}

	public void setCalificacionDAO(CalificacionDAOjpa calificacionDAO) {
		this.calificacionDAO = calificacionDAO;
	}

	public String calificaciones(){
		String resul=this.validarSesion();
		if(resul==SUCCESS){
			Map<String, Object> session = ActionContext.getContext().getSession();
			Usuario user = (Usuario) session.get("usrLogin");
			ViajeDAOjpa ViajeDAO= (ViajeDAOjpa) FactoryDAO.getViajeDAO();
			Viaje unViaje=ViajeDAO.encontrar(this.idViaje);
			this.pasajeros=(List<Viajero>) unViaje.getPasajeros();
			pasajeros.add(unViaje.getConductor());
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
	
	public List<Viajero> getPasajeros() {
		return pasajeros;
	}

	public void setPasajeros(List<Viajero> pasajeros) {
		this.pasajeros = pasajeros;
	}
	
	
	private String validarSesion(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		Usuario user = (Usuario) session.get("usrLogin");
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
	
}
