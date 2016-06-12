package actionsGeneric;

import java.util.Date;

import actions.NotificacionAction;
import implementacionesDAO.FactoryDAO;
import implementacionesDAO.FrecuenciaViajeDAOjpa;
import model.EstadoSolicitud;
import model.FrecuenciaViaje;
import model.SolicitudViaje;
import model.Viaje;
import model.Viajero;
import util.SessionUtil;

public class GenericSolicitudAction {

	public String RegistroSolicitudViaje(String idFrecuencia) throws Exception{
		Viajero viajero = (Viajero)SessionUtil.getUsuario();
		viajero = (Viajero)SessionUtil.getUsuario();
		FrecuenciaViaje frecuenciaViaje = FactoryDAO.getFrecuenciaViajeDAO().encontrar(Integer.parseInt(idFrecuencia));
		Viaje viaje = frecuenciaViaje.getViaje();			
		if (!viaje.esConductor(viajero) || !viaje.esPasajero(viajero)){				
			boolean tieneSolicitudPendiente = FactoryDAO.getSolicitudViajeDAO().tieneSolicitudEstado(viajero,frecuenciaViaje,EstadoSolicitud.PENDIENTE);
			if(!tieneSolicitudPendiente){					
				SolicitudViaje solicitudViaje = new SolicitudViaje(new Date(),null,EstadoSolicitud.PENDIENTE,viajero,frecuenciaViaje,null);					
				FactoryDAO.getSolicitudViajeDAO().registrar(solicitudViaje);
				new NotificacionAction().crearNotificacionSolicitudNueva(viaje);
				return "SUCCESS";				
			}else{
				return "INPUT";
			}
		}
		else{
			return "sinPermisos";
		}
	}
	
}
