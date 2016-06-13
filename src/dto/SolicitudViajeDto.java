package dto;

import java.util.Date;

import model.EstadoSolicitud;

public class SolicitudViajeDto {

	
	private int id;
	
	private Date fechaInicioSolicitud;
	
	private Date fechaFinSolicitud;
	
	private EstadoSolicitud estadoSolicitud;
	
	private ViajeroDto viajero;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFechaInicioSolicitud() {
		return fechaInicioSolicitud;
	}

	public void setFechaInicioSolicitud(Date fechaInicioSolicitud) {
		this.fechaInicioSolicitud = fechaInicioSolicitud;
	}

	public Date getFechaFinSolicitud() {
		return fechaFinSolicitud;
	}

	public void setFechaFinSolicitud(Date fechaFinSolicitud) {
		this.fechaFinSolicitud = fechaFinSolicitud;
	}

	public EstadoSolicitud getEstadoSolicitud() {
		return estadoSolicitud;
	}

	public void setEstadoSolicitud(EstadoSolicitud estadoSolicitud) {
		this.estadoSolicitud = estadoSolicitud;
	}

	public ViajeroDto getViajero() {
		return viajero;
	}

	public void setViajero(ViajeroDto viajero) {
		this.viajero = viajero;
	}
	
	
	
}
