package actions;

import implementacionesDAO.DenunciaDAOjpa;
import implementacionesDAO.FactoryDAO;
import implementacionesDAO.ViajeDAOjpa;
import implementacionesDAO.ViajeroDAOjpa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import model.Denuncia;
import model.Usuario;
import model.Viaje;
import model.Viajero;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class DenunciaAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	private DenunciaDAOjpa denunciaDAO;
	private ViajeDAOjpa viajeDAO = (ViajeDAOjpa) FactoryDAO.getViajeDAO();
	private ViajeroDAOjpa viajeroDAO = (ViajeroDAOjpa) FactoryDAO.getViajeroDAO();
	private int id;
	private Date fecha;
	private String motivo;
	private String mensaje;
	private String respuesta;
	private String denunciado;
	private Viajero denunciante;
	private Viaje viaje = new Viaje();
	private List<Viajero> listaDenunciados = new ArrayList<Viajero>();
	
	
	public List<Viajero> getListaDenunciados() {
		return listaDenunciados;
	}


	public void setListaDenunciados(List<Viajero> listaDenunciados) {
		this.listaDenunciados = listaDenunciados;
	}


	public ViajeDAOjpa getViajeDAO() {
		return viajeDAO;
	}


	public void setViajeDAO(ViajeDAOjpa viajeDAO) {
		this.viajeDAO = viajeDAO;
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


	public String getMotivo() {
		return motivo;
	}


	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}


	public String getMensaje() {
		return mensaje;
	}


	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}


	public String getRespuesta() {
		return respuesta;
	}


	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}


	public String getDenunciado() {
		return denunciado;
	}


	public void setDenunciado(String denunciado) {
		this.denunciado = denunciado;
	}


	public Viajero getDenunciante() {
		return denunciante;
	}


	public void setDenunciante(Viajero denunciante) {
		this.denunciante = denunciante;
	}


	public Viaje getViaje() {
		return viaje;
	}


	public void setViaje(Viaje viaje) {
		this.viaje = viaje;
	}


	public DenunciaDAOjpa getDenunciaDAO() {
		return denunciaDAO;
	}


	public void setDenunciaDAO(DenunciaDAOjpa denunciaDAO) {
		this.denunciaDAO = denunciaDAO;
	}

	public String denuncias(){
		return SUCCESS;
	}
	
	public String nuevaDenuncia(){
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		viaje = viajeDAO.encontrar(Integer.parseInt(request.getParameter("id")));
		Map<String, Object> session = ActionContext.getContext().getSession();
		Usuario user = (Usuario) session.get("usrLogin");
		for (Viajero viajero : viaje.obtenerPasajeros()) {
			if(viajero.getId()!=user.getId()){
				listaDenunciados.add(viajero);
			}
		}
		if(viaje.getConductor().getId()!=user.getId()){
			listaDenunciados.add(viaje.getConductor());
		}
		
		return SUCCESS;
	}
	
	public String registrarDenuncia(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		Usuario user = (Usuario) session.get("usrLogin");
		if (user != null) {
			if (user.getPerfil().equals("viajero")) {
				fecha = new Date();
				Viajero viajeroDenunciado = viajeroDAO.encontrar(Integer.parseInt(this.denunciado));				 
				Denuncia d = new Denuncia(fecha,this.getMotivo(),this.getMensaje(),null,viajeroDenunciado,(Viajero)user);
				System.out.println("Ingreso al registro de la denuncia!!");
				denunciaDAO.registrar(d);				
				System.out.println("Salida del registro de la denuncia!!");
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
