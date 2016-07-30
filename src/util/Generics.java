package util;

import actionsGeneric.GenericConversacionAction;
import actionsGeneric.GenericDatosViajeAction;
import actionsGeneric.GenericLoginAction;
import actionsGeneric.GenericSolicitudAction;
import actionsGeneric.GenericViajeAction;
import actionsGeneric.GenericViajeroAction;

public class Generics {

	private static GenericViajeAction genericViaje;
	private static GenericSolicitudAction genericSolicitud;
	private static GenericLoginAction genericLogin;
	private static GenericDatosViajeAction genericDatosViaje;
	private static GenericConversacionAction genericConversacion;
	private static GenericViajeroAction genericViajero;
	
	public static GenericViajeAction getGenericViajeAction(){
		if(genericViaje==null){
			genericViaje=new GenericViajeAction();
		}
		return genericViaje;
	}
	
	public static GenericSolicitudAction getGenericSolicitudAction(){
		if(genericSolicitud==null){
			genericSolicitud=new GenericSolicitudAction();
		}
		return genericSolicitud;
	}
	
	public static GenericLoginAction getGenericLoginAction(){
		if(genericLogin==null){
			genericLogin=new GenericLoginAction();
		}
		return genericLogin;
	}
	
	public static GenericDatosViajeAction getGenericDatosViajeAction(){
		if(genericDatosViaje==null){
			genericDatosViaje=new GenericDatosViajeAction();
		}
		return genericDatosViaje;
	}
	
	public static GenericConversacionAction getGenericConversacionAction(){
		if(genericConversacion==null){
			genericConversacion=new GenericConversacionAction();
		}
		return genericConversacion;
	}
	
	public static GenericViajeroAction getGenericViajeroAction(){
		if(genericViajero==null){
			genericViajero=new GenericViajeroAction();
		}
		return genericViajero;
	}
	
}
