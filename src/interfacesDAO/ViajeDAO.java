package interfacesDAO;

import model.Viaje;

public interface ViajeDAO extends GenericDAO<Viaje>{

	public Viaje encontrarPorId(int viajeId);
	
}
