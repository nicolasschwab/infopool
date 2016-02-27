package implementacionesDAO;

import interfacesDAO.AdministradorDAO;
import interfacesDAO.AutoDAO;
import interfacesDAO.CalificacionDAO;
import interfacesDAO.ConversacionDAO;
import interfacesDAO.DenunciaDAO;
import interfacesDAO.EstadoNotificacionDAO;
import interfacesDAO.EventoDAO;
import interfacesDAO.FrecuenciaViajeDAO;
import interfacesDAO.HuellaCarbonoDAO;
import interfacesDAO.MarcaAutoDAO;
import interfacesDAO.MensajeDAO;
import interfacesDAO.ModeloAutoDAO;
import interfacesDAO.NotificacionDAO;
import interfacesDAO.SolicitudViajeDAO;
import interfacesDAO.UsuarioDAO;
import interfacesDAO.ViajeDAO;
import interfacesDAO.ViajeroDAO;

public class FactoryDAO {
				
	public static AdministradorDAO getAdministradorDAO() {
		return new AdministradorDAOjpa();
	}
	public static AutoDAO getAutoDAO(){
		return new AutoDAOjpa();
	}
	public static CalificacionDAO getCalificacionDAO() {
		return new CalificacionDAOjpa();
	}	
	public static ConversacionDAO getConversacionDAO(){
		return new ConversacionDAOjpa();
	}
	public static DenunciaDAO getDenunciaDAO() {
		return new DenunciaDAOjpa();
	}
	public static EventoDAO getEventoDAO() {
		return new EventoDAOjpa();
	}
	public static FrecuenciaViajeDAO getFrecuenciaViajeDAO() {
		return new FrecuenciaViajeDAOjpa();
	}
	public static HuellaCarbonoDAO getHuellaCarbonoDAO(){
		return new HuellaCarbonoDAOjpa();
	}
	public static MarcaAutoDAO getMarcaAutoDAO(){
		return new MarcaAutoDAOjpa();
	}
	public static MensajeDAO getMensajeDAO() {
		return new MensajeDAOjpa();
	}
	public static ModeloAutoDAO getModeloAutoDAO(){
		return new ModeloAutoDAOjpa();
	}
	public static NotificacionDAO getNotificacionDAO(){
		return new NotificacionDAOjpa();
	}
	public static SolicitudViajeDAO getSolicitudViajeDAO() {
		return new SolicitudViajeDAOjpa();
	}		
	public static UsuarioDAO getUsuarioDAO(){
		return new UsuarioDAOjpa();
	}	
	public static ViajeDAO getViajeDAO(){
		return new ViajeDAOjpa();
	}
	public static ViajeroDAO getViajeroDAO() {
		return new ViajeroDAOjpa();
	}	
		
	public static EstadoNotificacionDAO getEstadoNotificacionDAO(){
		return new EstadoNotificacionDAOjpa();
	}	
}