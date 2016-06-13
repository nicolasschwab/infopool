package actionsRest;

import org.apache.struts2.convention.annotation.Action;

import com.opensymphony.xwork2.ModelDriven;

import dto.ViajeDto;
import util.Dozer;
import util.Generics;
import util.SessionUtil;

@Action("/viaje")
public class MobileDatosViajeAction implements ModelDriven<ViajeDto>{

	ViajeDto viajeDto;
	
	private String direccionOrigen;
	private String direccionDestino;
	private String puntosTrayecto;
	private String fechaInicio;
	private String fechaFin;
	private String descripcion;
	private float kilometros;	
	private String horaPartida;
	private String horaRegreso;
	private int asientosDisponibles;	
	private String tramoViaje;
	private String tipoViaje;
	private String[] diaPeriodico;
	
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

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
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

	public String getTramoViaje() {
		return tramoViaje;
	}

	public void setTramoViaje(String tramoViaje) {
		this.tramoViaje = tramoViaje;
	}

	public String getTipoViaje() {
		return tipoViaje;
	}

	public void setTipoViaje(String tipoViaje) {
		this.tipoViaje = tipoViaje;
	}

	public String[] getDiaPeriodico() {
		return diaPeriodico;
	}

	public void setDiaPeriodico(String[] diaPeriodico) {
		this.diaPeriodico = diaPeriodico;
	}
	
	@Action("/alta")
	public void alta() throws Exception{
		if(SessionUtil.checkLogin()){
			this.setModel(Dozer.getMapper().map(Generics.getGenericDatosViajeAction().alta(direccionOrigen, direccionDestino, puntosTrayecto, fechaInicio, fechaFin, descripcion, kilometros, horaPartida, horaRegreso, asientosDisponibles, tramoViaje, tipoViaje, diaPeriodico), ViajeDto.class));			
		}
	}

	@Override
	public ViajeDto getModel() {
		return viajeDto;
	}
	public void setModel(ViajeDto viaje){
		viajeDto=viaje;
	}
}
