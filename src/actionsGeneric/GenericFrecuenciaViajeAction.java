package actionsGeneric;

import java.util.List;

import implementacionesDAO.FactoryDAO;
import interfacesDAO.FrecuenciaViajeDAO;
import interfacesDAO.ViajeroDAO;
import model.FrecuenciaViaje;
import model.Viajero;

public class GenericFrecuenciaViajeAction {
	private FrecuenciaViajeDAO frecuenciaviajeDAO;
	
	public FrecuenciaViajeDAO getFrecuenciaViajeDAO(){
		if(frecuenciaviajeDAO==null){
			frecuenciaviajeDAO=FactoryDAO.getFrecuenciaViajeDAO();
		}
		return frecuenciaviajeDAO;
	}
	
	public void setFrecuenciaViajeDAO(FrecuenciaViajeDAO frecuenciaviajeDAO){
		this.frecuenciaviajeDAO = frecuenciaviajeDAO;
	}
	
	public FrecuenciaViaje detalleFrecuenciaViaje(int id) throws Exception{
		return this.getFrecuenciaViajeDAO().encontrar(id);
	}
	
	public List<FrecuenciaViaje> listarFrecuenciasViaje(int idViaje){
		return this.getFrecuenciaViajeDAO().obtenerFrecuenciasViaje(idViaje);
	}
}
