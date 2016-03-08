package interfacesDAO;

import java.util.List;

import model.Evento;
import model.Viaje;

public interface ViajeDAO extends GenericDAO<Viaje>{

	public <T> void registrar(T viaje);
	public <T> List<Viaje> obtenerViajesConductorEstado(T conductor, boolean estadoActivo);
	public <T> List<Viaje> obtenerViajesPasajeroEstado(T pasajero, boolean estadoActivo);
	public <T> List<Viaje> obtenerHistorialViajesEstado(T viajero);
	public <T> List<Viaje> obtenerUltimosViajesBusqueda();
	public Viaje encontrarPorId(int viajeId);
	public <T> List<Viaje> obtenerUltimosViajes();
	public <T> List<Viaje> obtenerViajesBusquedaParametrizada(T dirOrigen, T dirDestino, T fechaViaje);
	
	/* Metodos a revisar */	
	public <T> List<Viaje> listarViajesConductor(T id);
	public <T> List<Viaje> listarViajesPasajero(T id);
	public <T> List<Viaje> listarViajesNoAsociados(T id,Evento evento);
}
