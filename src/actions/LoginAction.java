package actions;

import implementacionesDAO.FactoryDAO;
import implementacionesDAO.UsuarioDAOjpa;
import interfacesDAO.AutoDAO;
import interfacesDAO.UsuarioDAO;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import model.Usuario;
import model.Viajero;
import model.Auto;
import model.Combustible;
import model.Marca;
import model.Preferencias;
import model.TipoVehiculo;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	public int id;
	public String usuario;
	public String clave;
	public String rClave;
	public String telefonoUsuario;
	public String apellidoUsuario;
	public String nombreUsuario;
	public String mailUsuario;
	public String[] prefUsuario;
	public String fechaUsuario;
	public String vista;
	public Boolean fumar;
	public Boolean hablar;
	public Boolean comer;
	public File fperfilUsuario;
	public String vistaMarca;
	private Marca marca;
	public String modelo;
	public String vistaCombustible;
	private Combustible combustible;
	public String vistaTipo;
	private TipoVehiculo tipo;
	private Auto auto;
	private String fperfilUsuarioContentType;
	private String fperfilUsuarioFileName;
	private HttpServletRequest servletRequest;
	private List<String> lasMarcas=new ArrayList<String>();
	private List<String> losTipos=new ArrayList<String>();
	private List<String> losCombustibles=new ArrayList<String>();
	private List<Preferencias> lasPreferencias=new ArrayList<Preferencias>();
	private String notif="";

	

	public UsuarioDAOjpa usuarioDAO;
	private Viajero usrlogueado;
	

	public String getNotif() {
		return notif;
	}
	public void setNotif(String notif) {
		this.notif = notif;
	}
	public Boolean getFumar() {
		return fumar;
	}
	public void setFumar(Boolean fumar) {
		this.fumar = fumar;
	}
	public Boolean getHablar() {
		return hablar;
	}
	public void setHablar(Boolean hablar) {
		this.hablar = hablar;
	}
	public Boolean getComer() {
		return comer;
	}
	public void setComer(Boolean comer) {
		this.comer = comer;
	}
	public String getVista() {
		return vista;
	}
	public void setVista(String vista) {
		this.vista = vista;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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

	public String getrClave() {
		return rClave;
	}

	public void setrClave(String rClave) {
		this.rClave = rClave;
	}

	public String getTelefonoUsuario() {
		return telefonoUsuario;
	}

	public void setTelefonoUsuario(String telefonoUsuario) {
		this.telefonoUsuario = telefonoUsuario;
	}

	public String getApellidoUsuario() {
		return apellidoUsuario;
	}

	public void setApellidoUsuario(String apellidoUsuario) {
		apellidoUsuario = apellidoUsuario.trim();
		this.apellidoUsuario = apellidoUsuario;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		nombreUsuario = nombreUsuario.trim();
		this.nombreUsuario = nombreUsuario;
	}

	public String getMailUsuario() {
		return mailUsuario;
	}

	public void setMailUsuario(String mailUsuario) {

		this.mailUsuario = mailUsuario;
	}

	public String[] getPrefUsuario() {
		return prefUsuario;
	}

	public void setPrefUsuario(String[] prefUsuario) {
		this.prefUsuario = prefUsuario;
	}

	public String getFechaUsuario() {
		return fechaUsuario;
	}

	public void setFechaUsuario(String fechaUsuario) {
		this.fechaUsuario = fechaUsuario;
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

	public UsuarioDAOjpa getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(UsuarioDAOjpa usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}
	public String getVistaMarca() {
		return vistaMarca;
	}
	public void setVistaMarca(String vistaMarca) {
		this.vistaMarca = vistaMarca;
	}
	public Marca getMarca() {
		return marca;
	}
	public void setMarca(Marca marca) {
		this.marca = marca;
	}
	public String getVistaCombustible() {
		return vistaCombustible;
	}
	public void setVistaCombustible(String vistaCombustible) {
		this.vistaCombustible = vistaCombustible;
	}
	public Combustible getCombustible() {
		return combustible;
	}
	public void setCombustible(Combustible combustible) {
		this.combustible = combustible;
	}
	public String getVistaTipo() {
		return vistaTipo;
	}
	public void setVistaTipo(String vistaTipo) {
		this.vistaTipo = vistaTipo;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public TipoVehiculo getTipo() {
		return tipo;
	}
	public void setTipo(TipoVehiculo tipo) {
		this.tipo = tipo;
	}
	public Auto getAuto() {
		return auto;
	}
	public void setAuto(Auto auto) {
		this.auto = auto;
	}
	private boolean estaLogeuado(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		String user = (String) session.get("perfil");
		if (user == null) {
			return false;
		}
		return true;
	}

	public String validarLogin() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		String user = (String) session.get("perfil");
		if (user == null) {
			if ((this.getUsuario().length() > 0) && (this.getClave().length() > 0)){
				Usuario u = usuarioDAO.existe(this.getUsuario(), this.getClave());
				if (u != null) {
					if (u.getActivo()){
						session.put("usrLogin", u);
						session.put("perfil", u.getPerfil());
						return SUCCESS;
					}
					else{
						addFieldError("loginError", "La cuenta a la que desea ingresa se encuentra desactivada temporalmente!");
						return INPUT;
					}
				} else {
					addFieldError("loginError", "Los datos ingresado son incorrectos!");
					return INPUT;
				}
			}
			else{
				addFieldError("loginError", "Debe ingresar el usuario y la clave para acceder!");
				return INPUT;
			}
		} else {
			return "conectado";
		}
	}
	public String registrarUsuario() throws ParseException{
		if (!this.estaLogeuado()) {
			if ((this.getApellidoUsuario().length() > 0) && (this.getNombreUsuario().length() > 0) && (this.getFechaUsuario().length() > 0) && (this.getTelefonoUsuario().length() > 0) && (this.getMailUsuario().length() > 0) && (this.getUsuario().length() >0) && (this.getClave().length() > 0) && (this.getrClave().length() > 0) && (this.getFperfilUsuario() != null)){
				if (this.getrClave().equals(this.getClave())){
					Usuario u = usuarioDAO.existeNombreUsuario(this.getUsuario());
					if (u == null) {
						if(this.validarAuto()){
							if(this.validarPreferencias()){								
								UsuarioDAO usuario = FactoryDAO.getUsuarioDAO();							
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
								Date fechanac1 = sdf.parse(this.getFechaUsuario());
								Usuario usuario1 = new Viajero(this.getUsuario(),
										this.getClave(), this.getNombreUsuario(),
										this.getApellidoUsuario(), this.getTelefonoUsuario(),
										this.getMailUsuario(), fechanac1, this.lasPreferencias);							
								if (this.fperfilUsuario != null){
									byte[] b = new byte[(int) this.fperfilUsuario.length()];
									try {
										FileInputStream fileInputStream = new FileInputStream(
												this.fperfilUsuario);
										fileInputStream.read(b);
									} catch (IOException e1) {
										System.out.println("Error Reading The File.");
										e1.printStackTrace();
									}
									((Viajero) usuario1).setFotoPerfil(b);
								}
								if(this.getAuto()!=null){
									AutoDAO auto=FactoryDAO.getAutoDAO();
									auto.registrar(this.getAuto());
								}
								((Viajero)usuario1).setAuto(this.getAuto());
								usuario.registrar(usuario1);
								return SUCCESS;
							}else{
								addFieldError("loginError", "Las preferencias elegidas no existen");
								return INPUT;
							}
						}else
						{
							addFieldError("loginError", "Los datos del auto no son correctos");
							return INPUT;
						}						
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
					this.lasPreferencias.add(Preferencias.valueOf(this.getPrefUsuario()[i]));
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

	public String miPerfil() throws IOException {
		Map<String, Object> session = ActionContext.getContext().getSession();
		setUsrlogueado((Viajero) session.get("usrLogin"));
		if(this.getNotif()!=""){
			new NotificacionAction().cambiarEstadoAVisitado(this.notif);
		}
		return SUCCESS;
	}

	public String cerrarSesion() {
		ActionContext.getContext().getSession().clear();
		return SUCCESS;
	}

	public HttpServletRequest getServletRequest() {
		return servletRequest;
	}

	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;
	}

	public Viajero getUsrlogueado() {
		return usrlogueado;
	}

	public void setUsrlogueado(Viajero usrlogueado) {
		this.usrlogueado = usrlogueado;
	}
	
	public String edicionUsuario(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		setUsrlogueado((Viajero) session.get("usrLogin"));		
		return SUCCESS;
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
	}
}
