package actions;

import implementacionesDAO.ViajeroDAOjpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import model.Viajero;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ViajeroAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private ViajeroDAOjpa viajeroDAO;
	private List<Viajero> viajeroLista = new ArrayList<Viajero>();
	private Viajero viajero;
	
	public ViajeroDAOjpa getViajeroDAO() {
		return viajeroDAO;
	}
	public void setViajeroDAO(ViajeroDAOjpa viajeroDAO) {
		this.viajeroDAO = viajeroDAO;
	}
	public List<Viajero> getViajeroLista() {
		return viajeroLista;
	}
	public void setViajeroLista(List<Viajero> viajeroLista) {
		this.viajeroLista = viajeroLista;
	}	
	public Viajero getViajero() {
		return viajero;
	}
	public void setViajero(Viajero viajero) {
		this.viajero = viajero;
	}
	
	
	public String viajeros(){
		String tienePermisosAdimin=validarSesionAdmin();
		if(tienePermisosAdimin==SUCCESS){
			viajeroLista = viajeroDAO.listar();
		}
		return tienePermisosAdimin;
	}
	
	public String detalleViajero(){
		String tienePermisosAdimin=validarSesionAdmin();
		if(tienePermisosAdimin==SUCCESS){
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			viajero = viajeroDAO.encontrar(Integer.parseInt(request.getParameter("id")));
		}
		return tienePermisosAdimin;
	}
	
	public String desactivarViajero(){
		String tienePermisosAdimin=validarSesionAdmin();
		if(tienePermisosAdimin==SUCCESS){	
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			viajero = viajeroDAO.encontrar(Integer.parseInt(request.getParameter("id")));
			viajero.setActivo(false);
			viajeroDAO.modificar(viajero);
		}
		return tienePermisosAdimin;
	}
	
	public String activarViajero(){
		String tienePermisosAdimin=validarSesionAdmin();
		if(tienePermisosAdimin==SUCCESS){		
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			viajero = viajeroDAO.encontrar(Integer.parseInt(request.getParameter("id")));
			viajero.setActivo(true);
			viajeroDAO.modificar(viajero);
		}
		return tienePermisosAdimin;
	}
	
	private String validarSesionAdmin(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		String user = (String) session.get("perfil");
		if (user != null) {	
			if (user.equals("administrador")) {
				return SUCCESS;
			}else{
				return "sinPermisos";
			}
		}
		else{
			return "login";
		}
	}

}
