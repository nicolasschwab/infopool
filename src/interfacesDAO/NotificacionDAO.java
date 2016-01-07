package interfacesDAO;

import java.util.List;

import model.EstadoNotificacion;
import model.Notificacion;
import model.Usuario;
import model.Viajero;

public interface NotificacionDAO extends GenericDAO<Notificacion>{
	public List<Notificacion> ListarPorUsuario(Usuario usr);
	public Notificacion encontrarPorId(String id);
}
