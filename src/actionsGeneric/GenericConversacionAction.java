package actionsGeneric;

import java.util.Date;

import actions.MensajeAction;
import implementacionesDAO.FactoryDAO;
import model.Conversacion;
import model.Mensaje;
import model.Viajero;

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
}
