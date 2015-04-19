package actions;

import implementacionesDAO.ViajeroDAOjpa;

import java.util.ArrayList;
import java.util.List;

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
		viajeroLista = viajeroDAO.listar();
		return SUCCESS;
	}
	
	public String detalleViajero(){
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		viajero = viajeroDAO.encontrar(Integer.parseInt(request.getParameter("id")));
		return SUCCESS;
	}
	
	public String desactivarViajero(){
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		viajero = viajeroDAO.encontrar(Integer.parseInt(request.getParameter("id")));
		viajero.setActivo(false);
		viajeroDAO.modificar(viajero);
		return SUCCESS;
	}
	
	public String activarViajero(){
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		viajero = viajeroDAO.encontrar(Integer.parseInt(request.getParameter("id")));
		viajero.setActivo(true);
		viajeroDAO.modificar(viajero);
		return SUCCESS;
	}

}
