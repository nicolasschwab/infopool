package dto;

import java.util.Collection;
import java.util.Date;

import model.TipoConversacion;

public class ConversacionDto {

	
	private int id;
	private Collection<MensajeDto> mensajes;
	private Collection<ViajeroDto> participantesConversacion;
	private ViajeDto viaje;
	private String asunto;
	private Date fechaUltimaModificacion;
	private TipoConversacion tipoConversacion;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Collection<MensajeDto> getMensajes() {
		return mensajes;
	}
	public void setMensajes(Collection<MensajeDto> mensajes) {
		this.mensajes = mensajes;
	}
	public Collection<ViajeroDto> getParticipantesConversacion() {
		return participantesConversacion;
	}
	public void setParticipantesConversacion(Collection<ViajeroDto> participantesConversacion) {
		this.participantesConversacion = participantesConversacion;
	}
	public ViajeDto getViaje() {
		return viaje;
	}
	public void setViaje(ViajeDto viaje) {
		this.viaje = viaje;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public Date getFechaUltimaModificacion() {
		return fechaUltimaModificacion;
	}
	public void setFechaUltimaModificacion(Date fechaUltimaModificacion) {
		this.fechaUltimaModificacion = fechaUltimaModificacion;
	}
	public TipoConversacion getTipoConversacion() {
		return tipoConversacion;
	}
	public void setTipoConversacion(TipoConversacion tipoConversacion) {
		this.tipoConversacion = tipoConversacion;
	}
	
}
