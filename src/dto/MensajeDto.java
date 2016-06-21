package dto;

import java.util.Date;

import model.Usuario;

public class MensajeDto {

	
	private int id;
	private Date fechaPublicacion;
	private String detalle;
	private String estado;	
	private ViajeroDto emisor;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}
	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
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
	public ViajeroDto getEmisor() {
		return emisor;
	}
	public void setEmisor(ViajeroDto emisor) {
		this.emisor = emisor;
	}
	
	
}
