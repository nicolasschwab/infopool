package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Denuncia implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue
	private int id;
	private Date fecha;
	private String motivo;
	private String mensaje;
	private String respuesta;
	@ManyToOne
	private Viajero denunciado;
	@ManyToOne
	private Viajero denunciante;
	
	public Denuncia() {
		super();
	}
	
	public Denuncia(Date fecha, String motivo, String mensaje,String respuesta, Viajero denunciado, Viajero denunciante) {
		super();		
		this.fecha = fecha;
		this.motivo = motivo;
		this.mensaje = mensaje;
		this.respuesta = respuesta;
		this.denunciado = denunciado;
		this.denunciante = denunciante;
	}
	
	public Denuncia(int id, Date fecha, String motivo, String mensaje,String respuesta, Viajero denunciado, Viajero denunciante) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.motivo = motivo;
		this.mensaje = mensaje;
		this.respuesta = respuesta;
		this.denunciado = denunciado;
		this.denunciante = denunciante;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public String getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	public Viajero getDenunciado() {
		return denunciado;
	}
	public void setDenunciado(Viajero denunciado) {
		this.denunciado = denunciado;
	}
	public Viajero getDenunciante() {
		return denunciante;
	}
	public void setDenunciante(Viajero denunciante) {
		this.denunciante = denunciante;
	}
	

}
