package actionsRest;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;

import com.opensymphony.xwork2.ModelDriven;

import dto.GenericDto;
import dto.NotificacionDto;
import implementacionesDAO.FactoryDAO;
import model.EstadoNotificacion;
import model.Notificacion;
import util.Dozer;
import util.SessionUtil;

public class MobileNotificacionAction implements ModelDriven<GenericDto>{

	private int id;
	private GenericDto respuesta;
	private List<Notificacion> notificaciones;
	private String uuid;
	
	@Action("/notificacion/listar")
	public void listar() throws Exception{
		if(SessionUtil.checkLoginMobile(this.getUuid()) || SessionUtil.checkLogin() ){
			notificaciones=FactoryDAO.getNotificacionDAO().listarPorUsuario(SessionUtil.getUsuario());
			for(Notificacion notificacion: notificaciones){
				this.success("", Dozer.getMapper().map(notificacion, NotificacionDto.class));
			}
			for (Notificacion notificacion: notificaciones ) {
				if(notificacion.getEstado()==EstadoNotificacion.NOVISTO){
					notificacion.setEstado(EstadoNotificacion.VISTO);
					FactoryDAO.getNotificacionDAO().modificar(notificacion);
				}
			}
			if(this.getModel().getResultado().isEmpty()){
				this.fail("No tenes notificaciones");
			}
		}
	}
	@Action("/notificacion/cantidad")
	public void cantidad(){
		if(SessionUtil.checkLoginMobile(this.getUuid()) || SessionUtil.checkLogin() ){
			notificaciones=FactoryDAO.getNotificacionDAO().cantidadNoVistas(SessionUtil.getUsuario());
			this.fail(String.valueOf(notificaciones.size()));
		}
	}
	
	private void success(String mensaje, NotificacionDto notificacion){
		this.getModel().setEstado("1");
		this.getModel().setMensaje(mensaje);
		this.getModel().agregarUnicoResutado(notificacion);
	}
	private void fail(String mensaje){
		this.getModel().setEstado("2");
		this.getModel().setMensaje(mensaje);
		this.getModel().setResultado(null);
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Override
	public GenericDto getModel() {
		if(respuesta==null){
			respuesta= new GenericDto<NotificacionDto>();
		}
		return respuesta;
	}
	
}
