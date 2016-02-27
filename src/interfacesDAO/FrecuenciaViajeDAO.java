package interfacesDAO;

import model.FrecuenciaViaje;

public interface FrecuenciaViajeDAO extends GenericDAO<FrecuenciaViaje>{

	public <T> FrecuenciaViaje buscarFrecuenciaViajeDia(T viaje, T diaSemana);
}
