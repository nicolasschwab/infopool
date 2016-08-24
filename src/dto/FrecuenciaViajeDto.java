package dto;

import model.DiasSemana;
import model.EstadoFrecuencia;
import model.TramoViaje;

public class FrecuenciaViajeDto {
	
	private int id;	
	private DiasSemana diaFrecuencia;
	private EstadoFrecuencia estadoFrecuencia;
	private String horaPartida;
	private String horaRegreso;
	private int asientosDisponibles;
	private TramoViaje tramoViaje;
    private String puntosTrayecto;
	private float kilometros;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public DiasSemana getDiaFrecuencia() {
		return diaFrecuencia;
	}
	public void setDiaFrecuencia(DiasSemana diaFrecuencia) {
		this.diaFrecuencia = diaFrecuencia;
	}
	public EstadoFrecuencia getEstadoFrecuencia() {
		return estadoFrecuencia;
	}
	public void setEstadoFrecuencia(EstadoFrecuencia estadoFrecuencia) {
		this.estadoFrecuencia = estadoFrecuencia;
	}
	public String getHoraPartida() {
		return horaPartida;
	}
	public void setHoraPartida(String horaPartida) {
		this.horaPartida = horaPartida;
	}
	public String getHoraRegreso() {
		return horaRegreso;
	}
	public void setHoraRegreso(String horaRegreso) {
		this.horaRegreso = horaRegreso;
	}
	public int getAsientosDisponibles() {
		return asientosDisponibles;
	}
	public void setAsientosDisponibles(int asientosDisponibles) {
		this.asientosDisponibles = asientosDisponibles;
	}
	public TramoViaje getTramoViaje() {
		return tramoViaje;
	}
	public void setTramoViaje(TramoViaje tramoViaje) {
		this.tramoViaje = tramoViaje;
	}
	public String getPuntosTrayecto() {
		return puntosTrayecto;
	}
	public void setPuntosTrayecto(String puntosTrayecto) {
		this.puntosTrayecto = puntosTrayecto;
	}
	public float getKilometros() {
		return kilometros;
	}
	public void setKilometros(float kilometros) {
		this.kilometros = kilometros;
	}
	
}
