package interfacesDAO;

import java.util.List;

import model.FrecuenciaViaje;
import model.Viaje;

public interface FrecuenciaViajeDAO extends GenericDAO<FrecuenciaViaje>{

	public <T> FrecuenciaViaje buscarFrecuenciaViajeDia(T viaje, T diaSemana);
	public <T> List<FrecuenciaViaje> obtenerFrecuenciasViaje(Integer idViaje);
}
