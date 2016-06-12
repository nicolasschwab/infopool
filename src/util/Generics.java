package util;

import actionsGeneric.GenericSolicitudAction;
import actionsGeneric.GenericViajeAction;

public class Generics {

	private static GenericViajeAction genericViaje;
	private static GenericSolicitudAction genericSolicitud;
	
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
	
	
}
