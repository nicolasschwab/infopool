package interfacesDAO;

import java.util.List;

import model.FrecuenciaViaje;
import model.SolicitudViaje;
import model.Usuario;
import model.Viaje;
import model.Viajero;

public interface SolicitudViajeDAO extends GenericDAO<SolicitudViaje>{
	
	public <T> boolean tieneSolicitudEstado(T viajero, T frecuenciaViaje, T estadoSolicitud);
	public <T> List<SolicitudViaje> obtenerUltimasSolicitudes(T viajero);
	
	/* Revisar estos metodos */
	List<SolicitudViaje> listarSolicitudesViaje(Viaje viaje);
	List<SolicitudViaje> yaSolicito(Viaje viaje, Viajero viajero);
	public <T> boolean tieneSolicitudEstado(T viajero, T frecuenciaViaje);
	public List<SolicitudViaje> encontrarPorSolicitante(Viajero usuario);
	

}