package interfacesDAO;

import java.util.List;

import model.Calificacion;
import model.Viaje;
import model.Viajero;

public interface CalificacionDAO extends GenericDAO<Calificacion>{

	public Calificacion encontrarCalificacion(Viajero calificador,Viajero calificado, Viaje viaje);
	public List<Calificacion> listarCalificaciones(int id);
}
