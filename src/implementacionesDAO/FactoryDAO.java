package implementacionesDAO;

import interfacesDAO.*;

public class FactoryDAO {
	
	public static UsuarioDAO getUsuarioDAO(){
		return new UsuarioDAOjpa();
	}
	
	public static ViajeDAO getViajeDAO(){
		return new ViajeDAOjpa();
	}

	public static EventoDAO getEventoDAO() {
		return new EventoDAOjpa();
	}
	
	public static AdministradorDAO getAdministradorDAO() {
		return new AdministradorDAOjpa();
	}
	
	public static CalificacionDAO getCalificacionDAO() {
		return new CalificacionDAOjpa();
	}
	
	public static DenunciaDAO getDenunciaDAO() {
		return new DenunciaDAOjpa();
	}
	
	public static ForoMensajesDAO getForoMensajesDAO() {
		return new ForoMensajesDAOjpa();
	}
	
	public static MensajeDAO getMensajeDAO() {
		return new MensajeDAOjpa();
	}
	
	public static SolicitudViajeDAO getSolicitudViajeDAO() {
		return new SolicitudViajeDAOjpa();
	}
	
	public static ViajeroDAO getViajeroDAO() {
		return new ViajeroDAOjpa();
	}
	public static AutoDAOjpa getAutoDAO(){
		return new AutoDAOjpa();
	}
	public static NotificacionDAOjpa getNotificacionDAO(){
		return new NotificacionDAOjpa();
	}
	public static EstadoNotificacionDAOjpa getEstadoNotificacionDAO(){
		return new EstadoNotificacionDAOjpa();
	}
}
