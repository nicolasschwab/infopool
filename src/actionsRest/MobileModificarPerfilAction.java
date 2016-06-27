package actionsRest;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

import implementacionesDAO.FactoryDAO;
import model.Usuario;
import util.Csrf;
import util.SessionUtil;

public class MobileModificarPerfilAction implements ModelDriven<clasePrueba>{

	
	clasePrueba respuesta;
	public String claveActualEdicion;
	public String claveNuevaEdicion;
	public String repetirClaveEdicion;
	
	
	
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

	@Action("/usuario/editar")
	public void actualizarContrasena() throws Exception{
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
										this.setModel("modificado");
//										Usuario user=FactoryDAO.getUsuarioDAO().encontrarPorId(String.valueOf(SessionUtil.getUsuario().getId()));
//										if(user.getClave().equals(this.getClaveActualEdicion())){
//											user.setClave(this.getClaveNuevaEdicion());
//											FactoryDAO.getUsuarioDAO().modificar(user);
//											this.setModel("modificado");
//		
//										}else{
//											this.setModel("la clave actual no es la correcta");
//		
//										}										
									}else{
										this.setModel("las claves no coinciden");
		
									}
								}else{
									this.setModel("la clave no puede estar vacia");
		
								}
							}else{
								this.setModel("la clave no puede estar vacia");
		
							}
						}else{
							this.setModel("la clave no puede estar vacia");
		
						}
					}else{
						this.setModel("la clave no puede estar vacia");
		
					}
				}else{
					this.setModel("la clave no puede estar vacia");
		
				}
			}else{
				this.setModel("la clave no puede estar vacia");
		
			}
		}
		
	}
	@Action("/usuario/prueba")
	public void prueba(){
		this.setModel("prueba");
	}

	@Override
	public clasePrueba getModel() {
		return respuesta;
	}

	public void setModel(String respuesta) {
		if(this.respuesta==null){
			this.respuesta= new clasePrueba();
		}
		this.respuesta.setNombre( respuesta);
	}
	
}
