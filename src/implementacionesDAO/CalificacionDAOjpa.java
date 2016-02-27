package implementacionesDAO;

import interfacesDAO.CalificacionDAO;
import model.Calificacion;

public class CalificacionDAOjpa extends GenericDAOjpa<Calificacion> implements CalificacionDAO {
	
	public CalificacionDAOjpa(){
		super(Calificacion.class);
	}
}
