package util;

import actionsGeneric.GenericLoginAction;
import actionsGeneric.GenericSolicitudAction;
import actionsGeneric.GenericViajeAction;

public class Generics {

	private static GenericViajeAction genericViaje;
	private static GenericSolicitudAction genericSolicitud;
	private static GenericLoginAction genericLogin;
	
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
	
	
}
