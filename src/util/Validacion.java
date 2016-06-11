package util;

public class Validacion {	
	
	/**
	 * Devuelve true si el string no esta vacio
	 * @param string
	 * 
	 */
	public static boolean stringNoVacio(String string){
		if(string!=null && !string.equals("")){
			return true;
		}
		return false;
	}
}
