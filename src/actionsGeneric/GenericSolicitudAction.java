package actionsGeneric;


import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import actions.NotificacionAction;
import actions.ViajeAction;
import implementacionesDAO.FactoryDAO;
import implementacionesDAO.FrecuenciaViajeDAOjpa;
import implementacionesDAO.SolicitudViajeDAOjpa;
import implementacionesDAO.ViajeDAOjpa;
import implementacionesDAO.ViajeroDAOjpa;
import model.EstadoSolicitud;
import model.FrecuenciaViaje;
import model.SolicitudViaje;
import model.Viaje;
import model.Viajero;
import util.SessionUtil;

public class GenericSolicitudAction {

	public String RegistroSolicitudViaje(String idFrecuencia, String puntoEncuentro) throws Exception{
		Viajero viajero = (Viajero)SessionUtil.getUsuario();
		viajero = (Viajero)SessionUtil.getUsuario();
		FrecuenciaViaje frecuenciaViaje = FactoryDAO.getFrecuenciaViajeDAO().encontrar(Integer.parseInt(idFrecuencia));
		Viaje viaje = frecuenciaViaje.getViaje();			
		if (!viaje.esConductor(viajero) || !viaje.esPasajero(viajero)){				
			boolean tieneSolicitudPendiente = FactoryDAO.getSolicitudViajeDAO().tieneSolicitudEstado(viajero,frecuenciaViaje);
			if(!tieneSolicitudPendiente){					
				SolicitudViaje solicitudViaje = new SolicitudViaje(new Date(),null,EstadoSolicitud.PENDIENTE,viajero,frecuenciaViaje,puntoEncuentro);
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
	
	public List<SolicitudViaje> SolicitudesFrecuenciaViaje(int idFrecuencia) throws Exception{
		FrecuenciaViaje frecuenciaViaje = FactoryDAO.getFrecuenciaViajeDAO().encontrar(idFrecuencia);
		if(frecuenciaViaje.getViaje().esConductor(SessionUtil.getUsuario())){
			return ((SolicitudViajeDAOjpa) FactoryDAO.getSolicitudViajeDAO()).buscarSolicitudesFrecuencia(frecuenciaViaje);
		}
		return null;
				
	}
	
	
	public String AceptarSolicitudViaje(SolicitudViaje solicitudViaje) throws Exception{	
					
		FrecuenciaViaje frecuenciaViaje = ((FrecuenciaViajeDAOjpa)FactoryDAO.getFrecuenciaViajeDAO()).encontrar(solicitudViaje.getFrecuenciaViaje().getId());
		Viaje viaje = frecuenciaViaje.getViaje();
		int idViaje = viaje.getId();
		 
		if (frecuenciaViaje.getAsientosDisponibles() > 0 ){
			if(viaje.esConductor(SessionUtil.getUsuario())){
				
			
			solicitudViaje.setEstadoSolicitud(EstadoSolicitud.ACEPTADA);
			solicitudViaje.setFechaFinSolicitud(new Date());
			FactoryDAO.getSolicitudViajeDAO().modificar(solicitudViaje);
			
			Viajero viajero = ((ViajeroDAOjpa)FactoryDAO.getViajeroDAO()).encontrar(solicitudViaje.getViajero().getId());
			if (((FrecuenciaViajeDAOjpa)FactoryDAO.getFrecuenciaViajeDAO()).cantidadFrecuenciasEnViaje(viajero,viaje) == 0){
				ViajeAction.agregarUsuarioAForo(idViaje,viajero.getId());
				viajero.agregarViajePasajero(viaje);
			}
			viajero.agregarFrecuenciaPasajero(frecuenciaViaje);
			FactoryDAO.getViajeroDAO().modificar(viajero);
			
			frecuenciaViaje.agregarViajeroFrecuencia(viajero);				
			frecuenciaViaje.setAsientosDisponibles(frecuenciaViaje.getAsientosDisponibles()-1);				
			FactoryDAO.getFrecuenciaViajeDAO().modificar(frecuenciaViaje);
			
			new NotificacionAction().crearNotificacionSolicitudAceptar(viajero, viaje);
			return "SUCCESS";
			}else{
				return "sinPermisos";
			}
		}
		else{
			return "INPUT";
		}
	}
	
	public String RechazarSolicitudViaje(SolicitudViaje solicitudViaje) throws Exception{
	
		FrecuenciaViaje frecuenciaViaje = ((FrecuenciaViajeDAOjpa)FactoryDAO.getFrecuenciaViajeDAO()).encontrar(solicitudViaje.getFrecuenciaViaje().getId());
		Viaje viaje = frecuenciaViaje.getViaje();
		if(viaje.esConductor(SessionUtil.getUsuario())){
			solicitudViaje.setEstadoSolicitud(EstadoSolicitud.RECHAZADA);
			solicitudViaje.setFechaFinSolicitud(new Date());
			FactoryDAO.getSolicitudViajeDAO().modificar(solicitudViaje);
		
			new NotificacionAction().crearNotificacionRechazoSolicitud(solicitudViaje.getViajero(), viaje);
			return "SUCCESS";
		}
		return "INPUT";
	}
	
	public String CancelarSolicitudViaje(SolicitudViaje solicitudViaje) throws Exception{
		FrecuenciaViaje frecuenciaViaje = ((FrecuenciaViajeDAOjpa)FactoryDAO.getFrecuenciaViajeDAO()).encontrar(solicitudViaje.getFrecuenciaViaje().getId());
		Viaje viaje = ((ViajeDAOjpa)FactoryDAO.getViajeDAO()).encontrarPorId(frecuenciaViaje.getViaje().getId());
		
		if(viaje.esConductor(SessionUtil.getUsuario())){	
						
			solicitudViaje.setEstadoSolicitud(EstadoSolicitud.CANCELADA);
			solicitudViaje.setFechaFinSolicitud(new Date());
			FactoryDAO.getSolicitudViajeDAO().modificar(solicitudViaje);
			
			Viajero viajero = ((ViajeroDAOjpa)FactoryDAO.getViajeroDAO()).encontrar(solicitudViaje.getViajero().getId());
			if (((FrecuenciaViajeDAOjpa)FactoryDAO.getFrecuenciaViajeDAO()).cantidadFrecuenciasEnViaje(viajero,viaje) == 1){				
				viajero.quitarViajePasajero(viaje);				
				viaje.quitarPasajero(viajero);
			}			
			viajero.quitarFrecuenciaPasajero(frecuenciaViaje);
			FactoryDAO.getViajeroDAO().modificar(viajero);			
			FactoryDAO.getViajeDAO().modificar(viaje);
			
			frecuenciaViaje.agregarPasajeroHistorial(viajero);
			frecuenciaViaje.setAsientosDisponibles(frecuenciaViaje.getAsientosDisponibles()+1);			
			FactoryDAO.getFrecuenciaViajeDAO().modificar(frecuenciaViaje);			
			
			new NotificacionAction().crearNotificacionSolicitudCancelada(viaje);			
			return "SUCCESS";
		}
		return "sinPermisos";
	}
}
