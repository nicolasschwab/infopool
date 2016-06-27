package interfacesDAO;

import java.util.List;

import model.Notificacion;
import model.Usuario;

public interface NotificacionDAO extends GenericDAO<Notificacion>{
	
	public List<Notificacion> listarPorUsuario(Usuario usr);
	public Notificacion encontrarPorId(String id);
	public List<Notificacion> cantidadNoVistas(Usuario usr);
	
}