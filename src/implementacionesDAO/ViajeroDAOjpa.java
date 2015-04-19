package implementacionesDAO;

import interfacesDAO.ViajeroDAO;
import model.Viajero;

public class ViajeroDAOjpa extends GenericDAOjpa<Viajero> implements ViajeroDAO {
	
	public ViajeroDAOjpa(){
		super(Viajero.class);
	}
}
