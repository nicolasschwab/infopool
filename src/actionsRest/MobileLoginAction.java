package actionsRest;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;


import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import actionsGeneric.GenericLoginAction;
import dto.GenericDto;
import dto.ViajeroDto;
import implementacionesDAO.FactoryDAO;
import model.Usuario;

import util.Dozer;
import util.Generics;
import util.SessionUtil;
import util.Validacion;

public class MobileLoginAction implements SessionAware,ModelDriven<GenericDto>{
	
	private String usr;
	private String clave;
	private GenericDto model;
	private String uuid;
	private SessionMap<String, Object> sessionMap;

	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public SessionMap<String, Object> getSession() {
		return sessionMap;
	}
	public void setSession(SessionMap<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}	
	public String getUsr() {
		return usr;
	}
	public void setUsr(String usr) {
		this.usr = usr;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}

	@Action("/sesion/login")
	public void index() throws Exception{
		if(Validacion.stringNoVacio(this.getClave()) && Validacion.stringNoVacio(this.getUsr()) ){
			String login=Generics.getGenericLoginAction().iniciarSesionGeneric(this.getUsr(), this.getClave(), FactoryDAO.getUsuarioDAO(), sessionMap);
			if (login=="success"){
				String uuid= this.crearUUID();
				Usuario usr=SessionUtil.getUsuario();
				this.setearModel("1",uuid, (Dozer.getMapper().map(usr,ViajeroDto.class)));
				usr.setUuid(uuid);
				FactoryDAO.getUsuarioDAO().modificar(usr);
				this.agregarSesion(usr);
			}else{
				this.setearModel("2", "datos incorrectos!", null);
			}
		}
	}
	
	private void agregarSesion(Usuario usr){
		sessionMap.put("usrLogin", usr);
	}
	
	private String crearUUID(){
		return UUID.randomUUID().toString().replaceAll("-","");
	}
	
	private void setearModel(String estado, String mensaje, ViajeroDto viajero) {
		this.getModel().agregarUnicoResutado(viajero);
		this.getModel().setEstado(estado);
		this.getModel().setMensaje(mensaje);
	}
	
	@Action("/sesion/logout")
	public void cerrarSesion() throws Exception {
		if(SessionUtil.checkLoginMobile(uuid)){
			Usuario usr=SessionUtil.getUsuario();
			usr.setUuid(null);
			FactoryDAO.getUsuarioDAO().modificar(usr);
			Generics.getGenericLoginAction().cerrarSesionGeneric(sessionMap);			
		}
		
	}
	
	@Override
	public void setSession(Map<String, Object> map) {
		sessionMap = (SessionMap<String, Object>) map;		
	}

	
	public GenericDto getModel() {
		if(model==null){
			model=new GenericDto();
		}
		return model;
	}
	
	public void setModel(GenericDto model){
		this.model=model;
	}
}
