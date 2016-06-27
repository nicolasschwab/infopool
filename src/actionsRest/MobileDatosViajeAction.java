package actionsRest;

import org.apache.struts2.convention.annotation.Action;

import com.opensymphony.xwork2.ModelDriven;

import dto.GenericDto;
import dto.ViajeDto;
import util.Dozer;
import util.Generics;
import util.SessionUtil;
import util.Validacion;

public class MobileDatosViajeAction implements ModelDriven<GenericDto>{

	private ViajeDto viajeDto;
	private GenericDto respuesta;
	
	
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
	private String uuid;
	
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
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
	
	@Action("/viaje/alta")
	public void alta() throws Exception{
		if(SessionUtil.checkLoginMobile(this.getUuid())){
			if(Validacion.stringNoVacio(direccionDestino) && Validacion.stringNoVacio(direccionOrigen) && Validacion.stringNoVacio(fechaInicio) && Validacion.intNoCeroPositivo(asientosDisponibles) && Validacion.stringNoVacio(horaPartida)){
				this.success("Se creo el viaje!",Dozer.getMapper().map(Generics.getGenericDatosViajeAction().alta(direccionOrigen, direccionDestino, puntosTrayecto, fechaInicio, fechaFin, descripcion, kilometros, horaPartida, horaRegreso, asientosDisponibles, tramoViaje, tipoViaje, diaPeriodico), ViajeDto.class));
			}else{
				this.fail("Complete todos los campos!");
			}
		}
	}

	private void success(String mensaje, ViajeDto viaje){
		this.getModel().agregarUnicoResutado(viaje);
		this.getModel().setEstado("1");
		this.getModel().setMensaje(mensaje);
		
	}
	private void fail(String mensaje){
		this.getModel().setResultado(null);
		this.getModel().setEstado("2");
		this.getModel().setMensaje(mensaje);
		
	}
	@Override
	public GenericDto getModel() {
		if(respuesta==null){
			respuesta= new GenericDto<ViajeDto>();
		}
		return respuesta;
	}
	public void setModel(GenericDto respuesta){
		respuesta=respuesta;
	}
}
