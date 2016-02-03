package interfacesDAO;

import java.util.List;

import model.SolicitudViaje;
import model.Viaje;
import model.Viajero;

public interface SolicitudViajeDAO extends GenericDAO<SolicitudViaje>{

	List<SolicitudViaje> listarSolicitudesViaje(Viaje viaje);

	List<SolicitudViaje> yaSolicito(Viaje viaje, Viajero viajero);

}
