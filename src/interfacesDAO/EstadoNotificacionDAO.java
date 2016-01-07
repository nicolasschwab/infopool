package interfacesDAO;

import model.EstadoNotificacion;

public interface EstadoNotificacionDAO extends GenericDAO<EstadoNotificacion>{

	public EstadoNotificacion traerNoVisto();
	public EstadoNotificacion traerVisto();
	public EstadoNotificacion traerVisitado();
}
