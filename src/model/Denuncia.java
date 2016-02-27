package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Denuncia implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue
	private int id;
	
	@Column(nullable = false)
	private Date fechaHora;
	
	@Column(nullable = false)
	private String motivo;
	
	@Column(nullable = false)
	private String mensaje;
	
	@Column
	private String respuesta;
	
	@ManyToOne(optional=false)
	private Viajero denunciado;
	
	@ManyToOne(optional=false)
	private Viajero denunciante;
	
	public Denuncia() {
		super();
	}
	
	public Denuncia(Date fechaHora, String motivo, String mensaje,String respuesta, Viajero denunciado, Viajero denunciante) {
		super();		
		this.fechaHora = fechaHora;
		this.motivo = motivo;
		this.mensaje = mensaje;
		this.respuesta = respuesta;
		this.denunciado = denunciado;
		this.denunciante = denunciante;
	}
		
	public int getId() {
		return id;
	}	
	public Date getFechaHora() {
		return fechaHora;
	}
	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
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