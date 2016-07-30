package actionsGeneric;

import model.Viajero;
import implementacionesDAO.FactoryDAO;
import interfacesDAO.ViajeroDAO;

public class GenericViajeroAction {
	
	private ViajeroDAO viajeroDAO;
	
	public ViajeroDAO getViajeroDAO(){
		if(viajeroDAO==null){
			viajeroDAO=FactoryDAO.getViajeroDAO();
		}
		return viajeroDAO;
	}
	
	public void setViajeroDAO(ViajeroDAO viajeroDAO){
		this.viajeroDAO = viajeroDAO;
	}
	
	public Viajero detalleViajero(String id){
		return this.getViajeroDAO().encontrar(Integer.parseInt(id));
	}

}
