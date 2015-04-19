package actions;

import implementacionesDAO.FactoryDAO;
import implementacionesDAO.UsuarioDAOjpa;
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
import java.util.Date;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import model.Usuario;
import model.Viajero;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	public String usuario;
	public String clave;
	public String rClave;
	public String telefonoUsuario;
	public String apellidoUsuario;
	public String nombreUsuario;
	public String mailUsuario;
	public String prefUsuario;
	public String fechaUsuario;

	public File fperfilUsuario;
	private String fperfilUsuarioContentType;
	private String fperfilUsuarioFileName;
	private HttpServletRequest servletRequest;

	public UsuarioDAOjpa usuarioDAO;
	private Viajero usrlogueado;

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

	public String getPrefUsuario() {
		return prefUsuario;
	}

	public void setPrefUsuario(String prefUsuario) {
		prefUsuario = prefUsuario.trim();
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

	public String validarLogin() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		String user = (String) session.get("perfil");
		if (user == null) {
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
		} else {
			return "conectado";
		}
	}

	public String registrarUsuario() throws ParseException {
		Map<String, Object> session = ActionContext.getContext().getSession();
		String user = (String) session.get("perfil");
		if (user == null) {
			Usuario u = usuarioDAO.existeNombreUsuario(this.getUsuario());
			if (u == null) {
				UsuarioDAO usuario = FactoryDAO.getUsuarioDAO();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date fechanac1 = sdf.parse(this.getFechaUsuario());
				Usuario usuario1 = new Viajero(this.getUsuario(),
						this.getClave(), this.getNombreUsuario(),
						this.getApellidoUsuario(), this.getTelefonoUsuario(),
						this.getMailUsuario(), fechanac1, this.getPrefUsuario());
				
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

				usuario.registrar(usuario1);
				return SUCCESS;
			} else {
				addFieldError("loginError", "El nombre de usuario ya existe");
				return INPUT;
			}
		} else {
			return "conectado";
		}

	}

	public String miPerfil() throws IOException {
		Map<String, Object> session = ActionContext.getContext().getSession();
		setUsrlogueado((Viajero) session.get("usrLogin"));		
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

}
