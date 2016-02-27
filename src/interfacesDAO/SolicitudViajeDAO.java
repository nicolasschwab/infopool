package interfacesDAO;

import java.util.List;

import model.SolicitudViaje;
import model.Viaje;
import model.Viajero;

public interface SolicitudViajeDAO extends GenericDAO<SolicitudViaje>{
	
	public <T> boolean tieneSolicitudEstado(T viajero, T viaje, T estadoSolicitud, T diaSemana);
	public <T> List<SolicitudViaje> obtenerUltimasSolicitudes(T viajero);
	
	/* Revisar estos metodos */
	List<SolicitudViaje> listarSolicitudesViaje(Viaje viaje);
	List<SolicitudViaje> yaSolicito(Viaje viaje, Viajero viajero);
	

}