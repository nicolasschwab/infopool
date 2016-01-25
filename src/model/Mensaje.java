package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Mensaje implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue
	private int id;
	private Date fecha;
	private String detalle;
	private String estado;	
	@ManyToOne
	private Usuario emisor;
	private boolean mensaje_sistema;	

	public Mensaje() {		
	}

	public Mensaje( Date fecha, String detalle, String estado, Usuario emisor) {
		super();
		this.fecha = fecha;
		this.detalle = detalle;
		this.estado = estado;
		this.emisor = emisor;
	}
	
	public Mensaje( Date fecha, String detalle, String estado, Usuario emisor, boolean mensaje_sistema) {
		super();
		this.fecha = fecha;
		this.detalle = detalle;
		this.estado = estado;
		this.emisor = emisor;
		this.mensaje_sistema=mensaje_sistema;
	}

	public int getId() {
		return id;
	}
	
	public boolean isMensaje_sistema() {
		return mensaje_sistema;
	}

	public void setMensaje_sistema(boolean mensaje_sistema) {
		this.mensaje_sistema = mensaje_sistema;
	}
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Usuario getEmisor() {
		return emisor;
	}
	public void setEmisor(Usuario emisor) {
		this.emisor = emisor;
	}
	
}