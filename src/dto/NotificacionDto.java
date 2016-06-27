package dto;

import model.EstadoNotificacion;

public class NotificacionDto {

	
	private String mensaje;
	
	private int emisorId;
	
	private String link;
	
	private String linkRest;
	
	private EstadoNotificacion estado;
	
	private String tipo;
	
	private String fecha;

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public int getEmisorId() {
		return emisorId;
	}

	public void setEmisorId(int emisorId) {
		this.emisorId = emisorId;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public EstadoNotificacion getEstado() {
		return estado;
	}

	public void setEstado(EstadoNotificacion estado) {
		this.estado = estado;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getLinkRest() {
		return linkRest;
	}

	public void setLinkRest(String linkRest) {
		this.linkRest = linkRest;
	}
	
	
}
