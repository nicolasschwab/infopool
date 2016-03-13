package interfacesDAO;

import java.util.List;

import model.Viaje;
import model.Viajero;

public interface ViajeroDAO extends GenericDAO<Viajero>{

	public Viajero encontrarUsuarioSistema();
}