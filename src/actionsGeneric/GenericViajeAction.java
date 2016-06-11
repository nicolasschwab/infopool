package actionsGeneric;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import implementacionesDAO.FactoryDAO;
import interfacesDAO.ViajeDAO;
import model.Viaje;
import util.SessionUtil;
import util.Validacion;

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


	public List<Viaje> busquedaViaje() throws ParseException {
		if (SessionUtil.checkLogin()){
			return this.busquedaViaje(null,null, null);
		}
		return null;
	}
	
	public List<Viaje> busquedaViaje(String dirOrigen, String dirDestino, String fecha) throws ParseException{
		if (SessionUtil.checkLogin()){
			if(!Validacion.stringNoVacio(fecha)){
				fecha = "2016-01-01";
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date fechaViaje = sdf.parse(fecha);
			return this.getViajeDAO().obtenerViajesBusquedaParametrizada(dirOrigen, dirDestino, fechaViaje);
		}
		return null;
	}
	
	public Viaje detalleViaje(String id){
		return viajeDAO.encontrarPorId(Integer.parseInt(id));
	}
	
	
	
}
