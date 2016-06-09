package actionsGeneric;

import java.util.List;

import implementacionesDAO.FactoryDAO;
import interfacesDAO.ViajeDAO;
import model.Viaje;
import util.SessionUtil;

public class GenericViajeAction {

private ViajeDAO viajeDAO;
	
	
	public ViajeDAO getViajeDAO() {
		if(viajeDAO==null){
			viajeDAO=FactoryDAO.getViajeDAO();
		}
		return viajeDAO;
	}

	public void setViajeDAO(ViajeDAO viajeDAO) {
		this.viajeDAO = viajeDAO;
	}


	public List<Viaje> busquedaViaje() {
		if (SessionUtil.checkLogin()){
			return this.getViajeDAO().obtenerUltimosViajesBusqueda();
		}
		return null;
	}
	
}
