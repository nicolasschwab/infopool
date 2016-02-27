package actions;

public final class Validador {

	public static boolean validarMail(String palabra){
		return palabra.matches("^[\\w-]+(\\.[\\w-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
	}
	
	public static boolean validarNumeros(String palabra){
		return palabra.matches("\\d+$");
	}
	public static boolean validarLetrasEspecial(String palabra){
		return palabra.matches("([a-z0-9]|[A-Z0-9]|-|,|.\\s)+");
	}
	
	public static boolean validarLetras(String palabra){
		
		return palabra.matches("([a-z]|[A-Z]|\\s)+");
		
	}
	
	public static boolean validarLetrasYNumeros(String palabra){		
		return palabra.matches("([a-z0-9]|[A-Z0-9])+");
	}
}
