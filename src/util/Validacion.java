package util;

public class Validacion {

	public static boolean stringNoVacio(String string){
		if(string!=null && string !=""){
			return true;
		}
		return false;
	}
}
