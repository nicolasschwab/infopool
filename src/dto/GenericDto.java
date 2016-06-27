package dto;

import java.util.ArrayList;
import java.util.List;

public class GenericDto<T> {

	private String estado;
	
	private String mensaje;
	
	private List<T> resultado;

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public List<T> getResultado() {
		if(resultado==null){
			resultado= new ArrayList<T>();
		}
		return resultado;
	}

	public void setResultado(List<T> resultado) {
		this.resultado = resultado;
	}
	
	public void agregarListaResutado(List<T> resultado){
		this.getResultado().addAll(resultado);
	}
	public void agregarUnicoResutado(T resultado){
		this.getResultado().add(resultado);
	}
	
	
	
}
