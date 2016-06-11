package util;

import actionsGeneric.GenericViajeAction;

public class Generics {

	private static GenericViajeAction genericViaje;
	
	public static GenericViajeAction getGenericViajeAction(){
		if(genericViaje!=null){
			genericViaje=new GenericViajeAction();
		}
		return genericViaje;
	}
}
