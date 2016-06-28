package actionsRest;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

import dto.GenericDto;
import implementacionesDAO.FactoryDAO;
import model.Usuario;
import util.Csrf;
import util.SessionUtil;

public class MobileModificarPerfilAction implements ModelDriven<GenericDto>{

	private String claveActualEdicion;
	private String claveNuevaEdicion;
	private String repetirClaveEdicion;
	private GenericDto respuesta;
	private String uuid;
	
	
	
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
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

	@Action("/usuario/editar/contrasenia")
	public void actualizarContrasena() throws Exception{
		
		
	}
	
	@Action("/perfil/modificar/clave")
	public void prueba() throws Exception{
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		String csrfId=Csrf.getTokenId();
		String csrfValue=request.getParameter(csrfId);
		
		if(SessionUtil.checkLoginMobile(this.getUuid()) || SessionUtil.checkLogin()){		
			if(csrfId!=null){
				if(csrfValue!=null){
					if(Csrf.checkValues(csrfId, csrfValue)){
						if(this.getClaveActualEdicion()!=null & this.getClaveActualEdicion()!=""){
							if(this.getClaveNuevaEdicion()!=null & this.getClaveNuevaEdicion()!=""){
								if(this.getRepetirClaveEdicion()!=null & this.getRepetirClaveEdicion()!=""){
									if(this.getClaveNuevaEdicion().equals(this.getRepetirClaveEdicion())){										
										Usuario user=FactoryDAO.getUsuarioDAO().encontrarPorId(String.valueOf(SessionUtil.getUsuario().getId()));
										if(user.getClave().equals(this.getClaveActualEdicion())){
											user.setClave(this.getClaveNuevaEdicion());
											FactoryDAO.getUsuarioDAO().modificar(user);
											this.success("modificado");
		
										}else{
											this.fail("la clave actual no es la correcta");
		
										}										
									}else{
										this.fail("las claves no coinciden");
		
									}
								}else{
									this.fail("la clave no puede estar vacia");
		
								}
							}else{
								this.fail("la clave no puede estar vacia");
		
							}
						}else{
							this.fail("la clave no puede estar vacia");
		
						}
					}else{
						this.fail("la clave no puede estar vacia");
		
					}
				}else{
					this.fail("la clave no puede estar vacia");
		
				}
			}else{
				this.fail("la clave no puede estar vacia");
		
			}
		}
	}
	
	private void success(String mensaje){
		this.getModel().setEstado("1");
		this.getModel().setMensaje(mensaje);
		this.getModel().setResultado(null);
	}
	
	private void fail(String mensaje){
		this.getModel().setEstado("2");
		this.getModel().setMensaje(mensaje);
		this.getModel().setResultado(null);
	}

	@Override
	public GenericDto getModel() {
		if(this.respuesta==null){
			this.respuesta= new GenericDto();
		}
		return respuesta;
	}

	public void setModel(GenericDto respuesta) {		
		this.respuesta=respuesta;
	}
	
}
