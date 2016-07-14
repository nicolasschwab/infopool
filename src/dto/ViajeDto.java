package dto;

import java.util.Date;

import model.Conversacion;
import model.Evento;
import model.Viajero;

public class ViajeDto {

	private int id;
	
	private String direccionOrigen;
	
	private String direccionDestino;
	
    private String puntosTrayecto;
	
	private Date fechaPublicacion;
	
	private Date fechaInicio;
	
	private Date fechaFin;		
		
	private Evento eventoAsociado;	
	
	private String descripcion;
	
	private float kilometros;
	
	private ViajeroDto conductor;
	
	private String calificacionConductor;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDireccionOrigen() {
		return direccionOrigen;
	}

	public void setDireccionOrigen(String direccionOrigen) {
		this.direccionOrigen = direccionOrigen;
	}

	public String getDireccionDestino() {
		return direccionDestino;
	}

	public void setDireccionDestino(String direccionDestino) {
		this.direccionDestino = direccionDestino;
	}

	public String getPuntosTrayecto() {
		return puntosTrayecto;
	}

	public void setPuntosTrayecto(String puntosTrayecto) {
		this.puntosTrayecto = puntosTrayecto;
	}

	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Evento getEventoAsociado() {
		return eventoAsociado;
	}

	public void setEventoAsociado(Evento eventoAsociado) {
		this.eventoAsociado = eventoAsociado;
	}

	public ViajeroDto getConductor() {
		return conductor;
	}

	public void setConductor(ViajeroDto conductor) {
		this.conductor = conductor;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public float getKilometros() {
		return kilometros;
	}

	public void setKilometros(float kilometros) {
		this.kilometros = kilometros;
	}

	public String getCalificacionConductor() {
		return calificacionConductor;
	}

	public void setCalificacionConductor(String calificacionConductor) {
		this.calificacionConductor = calificacionConductor;
	}
	
	
	
	
}
