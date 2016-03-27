package actions;

import interfacesDAO.UsuarioDAO;
import interfacesDAO.ViajeroDAO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import model.Administrador;
import model.Auto;
import model.MarcaAuto;
import model.ModeloAuto;
import model.Usuario;
import model.Viajero;
import util.Csrf;
import util.SessionUtil;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import implementacionesDAO.FactoryDAO;

public class DatosUsuarioAction extends ActionSupport implements SessionAware{
	
	private static final long serialVersionUID = 1L;
	public int id;

	//DATOS FORMULARIO
	private String nombreUsuario;
	private String apellidoUsuario;
	private String fNacimientoUsuario;	
	private String telefonoUsuario;
	private String mailUsuario;	
	private String usuario;
	private String clave;
	private String rClave;
	private File fperfilUsuario;
	private String fperfilUsuarioContentType;
	private String fperfilUsuarioFileName;

	//Listas de marcas y modelos
	private List<MarcaAuto> marcas;
	private List<ModeloAuto> modelos;
	public String marcaSeleccionada;
	public String modeloSeleccionado;
	
	//Variable usadas en la edicion del perfil
	public String nombreUsuarioEdicion;
	public String apellidoUsuarioEdicion;
	public String fNacimientoUsuarioEdicion;	
	public String telefonoUsuarioEdicion;
	public String mailUsuarioEdicion;	
	public String usuarioEdicion;
	private File fperfilUsuarioEdicion;
	private SessionMap<String, Object> sessionMap;
	public String claveActualEdicion;
	public String claveNuevaEdicion;
	public String repetirClaveEdicion;
	private String respuesta;
	
	private UsuarioDAO usuarioDAO;
	public Usuario user;	
		
	
	public String getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public String getApellidoUsuario() {
		return apellidoUsuario;
	}
	public void setApellidoUsuario(String apellidoUsuario) {
		this.apellidoUsuario = apellidoUsuario;
	}
	public String getFNacimientoUsuario() {
		return fNacimientoUsuario;
	}
	public void setFNacimientoUsuario(String fNacimientoUsuario) {
		this.fNacimientoUsuario = fNacimientoUsuario;
	}
	public String getTelefonoUsuario() {
		return telefonoUsuario;
	}
	public void setTelefonoUsuario(String telefonoUsuario) {
		this.telefonoUsuario = telefonoUsuario;
	}
	public String getMailUsuario() {
		return mailUsuario;
	}
	public void setMailUsuario(String mailUsuario) {
		this.mailUsuario = mailUsuario;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getRClave() {
		return rClave;
	}
	public void setRClave(String rClave) {
		this.rClave = rClave;
	}
	public File getFperfilUsuario() {
		return fperfilUsuario;
	}
	public void setFperfilUsuario(File fperfilUsuario) {
		this.fperfilUsuario = fperfilUsuario;
	}
	public String getFperfilUsuarioContentType() {
		return fperfilUsuarioContentType;
	}
	public void setFperfilUsuarioContentType(String fperfilUsuarioContentType) {
		this.fperfilUsuarioContentType = fperfilUsuarioContentType;
	}
	public String getFperfilUsuarioFileName() {
		return fperfilUsuarioFileName;
	}
	public void setFperfilUsuarioFileName(String fperfilUsuarioFileName) {
		this.fperfilUsuarioFileName = fperfilUsuarioFileName;
	}
	
	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}
	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}	
	public String getNombreUsuarioEdicion() {
		return nombreUsuarioEdicion;
	}
	public void setNombreUsuarioEdicion(String nombreUsuarioEdicion) {
		this.nombreUsuarioEdicion = nombreUsuarioEdicion;
	}
	public String getApellidoUsuarioEdicion() {
		return apellidoUsuarioEdicion;
	}
	public void setApellidoUsuarioEdicion(String apellidoUsuarioEdicion) {
		this.apellidoUsuarioEdicion = apellidoUsuarioEdicion;
	}
	public String getfNacimientoUsuarioEdicion() {
		return fNacimientoUsuarioEdicion;
	}
	public void setfNacimientoUsuarioEdicion(String fNacimientoUsuarioEdicion) {
		this.fNacimientoUsuarioEdicion = fNacimientoUsuarioEdicion;
	}
	public String getTelefonoUsuarioEdicion() {
		return telefonoUsuarioEdicion;
	}
	public void setTelefonoUsuarioEdicion(String telefonoUsuarioEdicion) {
		this.telefonoUsuarioEdicion = telefonoUsuarioEdicion;
	}
	public String getMailUsuarioEdicion() {
		return mailUsuarioEdicion;
	}
	public void setMailUsuarioEdicion(String mailUsuarioEdicion) {
		this.mailUsuarioEdicion = mailUsuarioEdicion;
	}
	public String getUsuarioEdicion() {
		return usuarioEdicion;
	}
	public void setUsuarioEdicion(String usuarioEdicion) {
		this.usuarioEdicion = usuarioEdicion;
	}
	public SessionMap<String, Object> getSessionMap() {
		return sessionMap;
	}
	public void setSessionMap(SessionMap<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}
	public File getFperfilUsuarioEdicion() {
		return fperfilUsuarioEdicion;
	}
	public void setFperfilUsuarioEdicion(File fperfilUsuarioEdicion) {
		this.fperfilUsuarioEdicion = fperfilUsuarioEdicion;
	}
	public String getClaveActualEdicion() {
		return claveActualEdicion;
	}
	public void setClaveActualEdicion(String claveActualEdicion) {
		this.claveActualEdicion = claveActualEdicion;
	}
	public String getClaveNuevaEdicion() {
		return claveNuevaEdicion;
	}
	public void setClaveNuevaEdicion(String claveNuevaEdicion) {
		this.claveNuevaEdicion = claveNuevaEdicion;
	}
	public String getRepetirClaveEdicion() {
		return repetirClaveEdicion;
	}
	public void setRepetirClaveEdicion(String repetirClaveEdicion) {
		this.repetirClaveEdicion = repetirClaveEdicion;
	}
	public List<MarcaAuto> getMarcas() {
		return marcas;
	}
	public void setMarcas(List<MarcaAuto> marcas) {
		this.marcas = marcas;
	}
	public List<ModeloAuto> getModelos() {
		return modelos;
	}
	public void setModelos(List<ModeloAuto> modelos) {
		this.modelos = modelos;
	}	
	public String getMarcaSeleccionada() {
		return marcaSeleccionada;
	}
	public void setMarcaSeleccionada(String marcaSeleccionada) {
		this.marcaSeleccionada = marcaSeleccionada;
	}
	public String getModeloSeleccionado() {
		return modeloSeleccionado;
	}
	public void setModeloSeleccionado(String modeloSeleccionado) {
		this.modeloSeleccionado = modeloSeleccionado;
	}
	public String registrarUsuario() throws Exception{
		if (!SessionUtil.checkLogin()) {
			if (this.getApellidoUsuario() == null){
				return INPUT;
			}
			if ((this.getApellidoUsuario().length() > 0) && (this.getNombreUsuario().length() > 0) && (this.getFNacimientoUsuario().length() > 0) && (this.getTelefonoUsuario().length() > 0) && (this.getMailUsuario().length() > 0) && (this.getUsuario().length() >0) && (this.getClave().length() > 0) && (this.getRClave().length() > 0) && (this.getFperfilUsuario() != null)){
				if (this.getRClave().equals(this.getClave())){
					Usuario u = usuarioDAO.existeNombreUsuario(this.getUsuario());
					if (u == null) {				
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						Date fnac = sdf.parse(this.getFNacimientoUsuario());
						Date fing = new Date();
						if (this.fperfilUsuario != null){
							byte[] b = new byte[(int) this.fperfilUsuario.length()];
							try {
								FileInputStream fileInputStream = new FileInputStream(this.fperfilUsuario);
								fileInputStream.read(b);
							} catch (IOException e1) {
								System.out.println("Error Reading The File.");
								e1.printStackTrace();
							}
							user = new Viajero(this.getNombreUsuario(),this.getApellidoUsuario(),this.getTelefonoUsuario(),this.getMailUsuario(),fnac,b,this.getUsuario(),this.getClave(),fing,true);
						}								
						usuarioDAO.registrar(user);
						return SUCCESS;
					}
					else{						
						addFieldError("loginError", "El nombre de usuario ya existe");
						return INPUT;
					}
				} else {
					addFieldError("loginError", "Las claves no coinciden!");
					return INPUT;
				}
			}
			else{
				addFieldError("loginError", "Debe completar todos los campos para poder registrarse!");
				return INPUT;
			}
		} else {
			return "conectado";
		}
	}
	
	public String verPerfilViajero() throws Exception {		
		if(SessionUtil.checkLogin()){
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			String idNotif=request.getParameter("notif");
			if(idNotif!=""){
				new NotificacionAction().cambiarEstadoAVisitado(idNotif);
				
			}
			this.setMarcas(FactoryDAO.getMarcaAutoDAO().listar());
			user = SessionUtil.getUsuario();
			id = user.getId();			
			return SUCCESS;
		}else{			
			return "sinPermisos";
		}
	}
	
	public String traerModelosDeMarca(){
		if(SessionUtil.checkLogin()){
			if(this.getMarcaSeleccionada()!=null & this.getMarcaSeleccionada()!=""){
				this.setModelos(FactoryDAO.getModeloAutoDAO().encontrarPorMarca(this.getMarcaSeleccionada()));
				return SUCCESS;
			}
		}
		return "sinPermisos";
	}
	
	public String edicionUsuario(){
		if(SessionUtil.checkLogin()){
			return SUCCESS;
		}else{
			return "sinPermisos";
		}
	}
	
	public String editarAuto() throws Exception{
		System.out.println("HOLAAAAAAA");
		if(SessionUtil.checkLogin()){
			if(this.getModeloSeleccionado()!=null && this.getModeloSeleccionado()!=""){
				if(this.getMarcaSeleccionada()!=null && this.getMarcaSeleccionada()!=""){
					Viajero viajero=FactoryDAO.getViajeroDAO().encontrar(SessionUtil.getUsuario().getId());
					Auto auto=viajero.getAuto();
					MarcaAuto marca=FactoryDAO.getMarcaAutoDAO().encontrar(Integer.parseInt(marcaSeleccionada));
					ModeloAuto modelo=FactoryDAO.getModeloAutoDAO().encontrar(Integer.parseInt(modeloSeleccionado));
					if(auto==null){
						auto=new Auto();
					}
					auto.setMarca(marca);
					auto.setModelo(modelo);
					System.out.println("HOLAAAAAAA");
					viajero.setAuto(auto);
					//FactoryDAO.getAutoDAO().registrar(auto);
					FactoryDAO.getViajeroDAO().modificar(viajero);
				}
			}
			return SUCCESS;
		}
		return "sinPermisos";
	}
	
	public String actualizarUsuario() throws Exception{
		if(SessionUtil.checkLogin()){
			ViajeroDAO viajeroDAO=FactoryDAO.getViajeroDAO();
			Viajero user=viajeroDAO.encontrar(SessionUtil.getUsuario().getId());
			if(this.getUsuarioEdicion()!=null){
				if(!(user.getUsuario().equals(this.getUsuarioEdicion()))){
					user.setUsuario(this.getUsuarioEdicion());
				}
			}
			if(this.getNombreUsuarioEdicion()!=null){
				if(!(user.getNombre().equals(this.getNombreUsuarioEdicion()))){
					user.setNombre(this.getNombreUsuarioEdicion());
				}
			}	
			if(this.getApellidoUsuarioEdicion()!=null){
				if(!(user.getApellido().equals(this.getApellidoUsuarioEdicion()))){
					user.setApellido(this.getApellidoUsuarioEdicion());
				}
			}	
			if(this.getMailUsuarioEdicion()!=null){
				if(!(user.getMail().equals(this.getMailUsuarioEdicion()))){
					user.setMail(this.getMailUsuarioEdicion());
				}
			}	
			if(this.getfNacimientoUsuarioEdicion()!=null){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date fnac = sdf.parse(this.getfNacimientoUsuarioEdicion());
				if(!(user.getFechaNacimiento().equals(fnac))){
					user.setFechaNacimiento(fnac);
				}
			}	
			if(this.getTelefonoUsuarioEdicion()!=null){
				if(!(user.getTelefono().equals(this.getTelefonoUsuarioEdicion()))){
					user.setTelefono(this.getTelefonoUsuarioEdicion());
				}
			}	
			viajeroDAO.modificar(user);
			sessionMap.put("usrLogin", user);
		}else{
			return "sinLogueo";
		}
		return SUCCESS;
	}
	@Override
	public void setSession(Map<String, Object> map) {
		sessionMap = (SessionMap<String, Object>) map;		
	}
	
	public String modificarImagen() throws Exception{
		if(SessionUtil.checkLogin()){
			if(this.getFperfilUsuarioEdicion()!=null){
				Viajero usr=FactoryDAO.getViajeroDAO().encontrar(SessionUtil.getUsuario().getId());
				byte[] b = new byte[(int) this.fperfilUsuarioEdicion.length()];
				try {
					FileInputStream fileInputStream = new FileInputStream(this.getFperfilUsuarioEdicion());
					fileInputStream.read(b);
				} catch (IOException e1) {
					System.out.println("Error Reading The File.");
					e1.printStackTrace();
				}
				usr.setFotoPerfil(b);
				FactoryDAO.getViajeroDAO().modificar(usr);
				sessionMap.put("usrLogin", usr);
			}	
			return SUCCESS;
		}
		return "sinPermisos";
		
	}
	
	public String actualizarContrasena() throws Exception{
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		String csrfId=Csrf.getTokenId();
		String csrfValue=request.getParameter(csrfId);
		if(SessionUtil.checkLogin()){		
			if(csrfId!=null){
				if(csrfValue!=null){
					if(Csrf.checkValues(csrfId, csrfValue)){
						if(this.getClaveActualEdicion()!=null & this.getClaveActualEdicion()!=""){
							if(this.getClaveNuevaEdicion()!=null & this.getClaveNuevaEdicion()!=""){
								if(this.getRepetirClaveEdicion()!=null & this.getRepetirClaveEdicion()!=""){
									if(this.getClaveNuevaEdicion().equals(this.getRepetirClaveEdicion())){
										Usuario user=this.getUsuarioDAO().encontrarPorId(String.valueOf(SessionUtil.getUsuario().getId()));
										if(user.getClave().equals(this.getClaveActualEdicion())){
											user.setClave(this.getClaveNuevaEdicion());
											this.getUsuarioDAO().modificar(user);
											this.setRespuesta("modificado");
											return SUCCESS;
										}else{
											this.setRespuesta("la clave actual no es la correcta");
											return SUCCESS;
										}										
									}else{
										this.setRespuesta("las claves no coinciden");
										return SUCCESS;
									}
								}else{
									this.setRespuesta("la clave no puede estar vacia");
									return SUCCESS;
								}
							}else{
								this.setRespuesta("la clave no puede estar vacia");
								return SUCCESS;
							}
						}else{
							this.setRespuesta("la clave no puede estar vacia");
							return SUCCESS;
						}
					}else{
						this.setRespuesta("la clave no puede estar vacia");
						return SUCCESS;
					}
				}else{
					this.setRespuesta("la clave no puede estar vacia");
					return SUCCESS;
				}
			}else{
				this.setRespuesta("la clave no puede estar vacia");
				return SUCCESS;
			}
		}
		return "sinPermisos";
	}
	/*
	private boolean validarPreferencias(){
		List<String> pref= new ArrayList<String>();
		pref.add("HABLAR");
		pref.add("COMER");
		pref.add("FUMAR");
		if(this.getPrefUsuario()!=null){		
			for(int i = 0; i < this.getPrefUsuario().length; i++){
				if(!pref.contains(this.getPrefUsuario()[i])){
					return false;
				}else{
					this.lasPreferencias.add(PreferenciasViaje.valueOf(this.getPrefUsuario()[i]));
				}
			}
		}		
		return true;
	}
	
	private boolean validarAuto() throws ParseException{
		lasMarcas.add("FORD");
		lasMarcas.add("CITROEN");
		lasMarcas.add("CHEVROLET");
		lasMarcas.add("DODGE");
		lasMarcas.add("FIAT");
		lasMarcas.add("HONDA");
		lasMarcas.add("HYUNDAI");
		lasMarcas.add("PEUGEOT");
		lasMarcas.add("RENAULT");
		lasMarcas.add("SEAT");
		lasMarcas.add("SUZUKI");
		lasMarcas.add("TOYOTA");
		lasMarcas.add("VOLKSWAGEN");
		losTipos.add("AUTO_3_PUERTAS");
		losTipos.add("AUTO_5_PUERTAS");
		losTipos.add("CAMIONETA");
		losCombustibles.add("NAFTA");
		losCombustibles.add("GASOIL");
		losCombustibles.add("GAS");
		losCombustibles.add("ELECTRICO");
		java.util.Date fecha = new Date();
		System.out.println(Calendar.getInstance().get(Calendar.YEAR));
		if(lasMarcas.contains(this.getVistaMarca()) && losTipos.contains(this.getVistaTipo()) && losCombustibles.contains(this.getVistaCombustible()) && this.getModelo().length()>0 && Integer.parseInt(this.getModelo())<= Calendar.getInstance().get(Calendar.YEAR) ){
				this.setMarca(Marca.valueOf(this.getVistaMarca()));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
				Date fechaModelo=sdf.parse(this.getModelo());
				this.setTipo(TipoVehiculo.valueOf(this.getVistaTipo()));
				this.setCombustible(Combustible.valueOf(this.getVistaCombustible()));
				this.setAuto(new Auto(this.getMarca(), fechaModelo, this.getCombustible(), this.getTipo()));
		}else{
			this.setAuto(null);
		}
		
		return true;
	}
	public String editarUsuario() throws ParseException {	
		Map<String, Object> session = ActionContext.getContext().getSession();		
		setUsrlogueado((Viajero) session.get("usrLogin"));		
		if (this.usrlogueado != null) {
			if ((this.getApellidoUsuario().length() > 0) && (this.getNombreUsuario().length() > 0) && (this.getFechaUsuario().length() > 0) && (this.getTelefonoUsuario().length() > 0) && (this.getMailUsuario().length() > 0)  ){
					if(this.validarPreferencias()){						
						Viajero u = (Viajero) this.usrlogueado;
						if (u != null) {
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							Date fechanac1 = sdf.parse(this.getFechaUsuario());
							u.setNombre(this.getNombreUsuario());
							u.setApellido(this.getApellidoUsuario());
							u.setTelefono(this.telefonoUsuario);
							u.setMail(this.getMailUsuario());
							u.setFechaNacimiento(fechanac1);
							u.setPreferenciasViaje(this.lasPreferencias);
							usuarioDAO.modificar(u);
							return SUCCESS;
						} else {
							addFieldError("loginError", "El usuario no existe");
							return INPUT;
						}
					}else{
						addFieldError("loginError", "Las preferencias elegidas son incorrectas");
						return INPUT;
					}
			}
			else{
				addFieldError("loginError", "Debe completar todos los campos!");
				return INPUT;
			}
		} else {
			return "login";
		}	
	}*/
}
