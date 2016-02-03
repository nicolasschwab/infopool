package interfacesDAO;

import java.util.List;

import model.Evento;
import model.Viaje;

public interface ViajeDAO extends GenericDAO<Viaje>{

	public Viaje encontrarPorId(int viajeId);
	public <T> List<Viaje> buscarPorDireccion(T dirOrigen, T dirDestino);
	public <T> List<Viaje> buscarPorFecha(T id);
	public <T> List<Viaje> listarViajesConductor(T id);
	public <T> List<Viaje> listarViajesPasajero(T id);
	public <T> List<Viaje> listarViajesNoAsociados(T id,Evento evento);
}
