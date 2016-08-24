package actionsGeneric;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import actions.MensajeAction;
import implementacionesDAO.FactoryDAO;
import model.Conversacion;
import model.Mensaje;
import model.Viaje;
import model.Viajero;
import util.SessionUtil;

public class GenericConversacionAction {

	
	public  Conversacion crearConversacion(String receptorID,int viajeId, Viajero user, String detalle, String asunto) throws NumberFormatException, Exception{
		Viajero receptor=FactoryDAO.getViajeroDAO().encontrar(Integer.parseInt(receptorID));
		Conversacion laConversacion=FactoryDAO.getConversacionDAO().encontrarPorViajeEIntegrantes(viajeId,user,receptor);
		MensajeAction mensajeAction=new MensajeAction();
		Mensaje mensaje=mensajeAction.crearMensaje(detalle);// creo y persisto el mensaje
		if(laConversacion==null){
			laConversacion=new Conversacion();// en este paso se setea por defecto la fecha de la ultima modificacion
			laConversacion.setAsunto(asunto);
			laConversacion.setViaje(FactoryDAO.getViajeDAO().encontrar(viajeId));
			laConversacion.agregarParticipantes(user,receptor);
			laConversacion.agregarMensajes(mensaje);
			FactoryDAO.getConversacionDAO().registrar(laConversacion); // persisto la nueva conversacion
		}else{ //ya existe una conversacion entre estos usuario para este viaje, se agrega el mensaje a la conversacion
			laConversacion.agregarMensajes(mensaje);
			laConversacion.setFechaUltimaModificacion(new Date());
			FactoryDAO.getConversacionDAO().modificar(laConversacion); //modifico la conversacion
		}
		return laConversacion;
	}
	
	public Conversacion detalle(int id){
		Conversacion conversacion=FactoryDAO.getConversacionDAO().encontrarPorId(id);
		if(conversacion!=null){
			boolean pertenece=false;
			for(Viajero viajero :conversacion.getParticipantesConversacion()){
				if(viajero.getId()==SessionUtil.getUsuario().getId()){
					pertenece=true;
				}
			}
			if(!pertenece){
				conversacion=null;
			}
		}
		return conversacion;
	}
	
	public Conversacion responderMensaje(int id, String detalle) throws Exception{
		Conversacion conversacion=FactoryDAO.getConversacionDAO().encontrarPorId(id);
		for(Viajero viajero :conversacion.getParticipantesConversacion()){
			if(viajero.getId()==SessionUtil.getUsuario().getId()){				
				Mensaje mensaje=new Mensaje(new Date(),detalle,"pendiente",viajero);
				FactoryDAO.getMensajeDAO().registrar(mensaje);
				conversacion.getMensajes().add(mensaje);
				FactoryDAO.getViajeDAO().modificar(SessionUtil.getUsuario());
				FactoryDAO.getViajeDAO().modificar(viajero);
				conversacion.setFechaUltimaModificacion(new Date());
				FactoryDAO.getConversacionDAO().modificar(conversacion);
			}
		}
		return conversacion;
	}
}
