package actions;

import implementacionesDAO.ViajeDAOjpa;
import interfacesDAO.FrecuenciaViajeDAO;
import interfacesDAO.ViajeDAO;
import interfacesDAO.ViajeroDAO;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import model.DiasSemana;
import model.EstadoFrecuencia;
import model.FrecuenciaViaje;
import model.TipoViaje;
import model.TramoViaje;
import model.Usuario;
import model.Viaje;
import model.Viajero;

import org.apache.struts2.ServletActionContext;

import util.SessionUtil;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class DatosViajeAction extends ActionSupport{
	
	private static final long serialVersionUID = 1L;
	public int id;
	
	//DATOS FORMULARIO
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

	private ViajeDAO viajeDAO;
	private ViajeroDAO viajeroDAO;
	private FrecuenciaViajeDAO frecuenciaViajeDAO;
	private Viaje viaje;	
	public Usuario user;
	private Collection<FrecuenciaViaje> frecuencias;
	
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
	public ViajeDAO getViajeDAO() {
		return viajeDAO;
	}
	public void setViajeDAO(ViajeDAO viajeDAO) {
		this.viajeDAO = viajeDAO;
	}
	public ViajeroDAO getViajeroDAO() {
		return viajeroDAO;
	}
	public void setViajeroDAO(ViajeroDAO viajeroDAO) {
		this.viajeroDAO = viajeroDAO;
	}
	public FrecuenciaViajeDAO getFrecuenciaViajeDAO() {
		return frecuenciaViajeDAO;
	}
	public void setFrecuenciaViajeDAO(FrecuenciaViajeDAO frecuenciaViajeDAO) {
		this.frecuenciaViajeDAO = frecuenciaViajeDAO;
	}
	public Viaje getViaje() {
		return viaje;
	}
	public void setViaje(Viaje viaje) {
		this.viaje = viaje;
	}
	public Usuario getUser(){
		return user;
	}
	public void setUser(Usuario user){
		this.user = user;
	}	
	public Collection<FrecuenciaViaje> getFrecuencias() {
		return frecuencias;
	}
	public void setFrecuencias(Collection<FrecuenciaViaje> frecuencias) {
		this.frecuencias = frecuencias;
	}
	
	//FALTAN VALIDACIONES
	public String RegistrarViaje() throws Exception {
		if (SessionUtil.checkLogin()){			
			if (this.getDireccionOrigen() == null){
				return INPUT;
			}
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");			
			Date fInicio = null;
			Date fFin = null;
			Time hPartida = null;
			Time hRegreso = null;
			viaje = new Viaje();
			EstadoFrecuencia estadoFrecuencia = EstadoFrecuencia.ACTIVA;
			frecuencias = new ArrayList<FrecuenciaViaje>();
			Viajero conductor = (Viajero) SessionUtil.getUsuario();
			
			if (this.getTramoViaje().equals("IDAVUELTA")){
				hRegreso = Time.valueOf(this.getHoraRegreso()+":00");
			}
			hPartida = Time.valueOf(this.getHoraPartida()+":00");
			
			if (this.getTipoViaje().equals("PERIODICO")){				
				HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);		
				diaPeriodico = request.getParameterValues("diaPeriodico");
				if (diaPeriodico != null && diaPeriodico.length > 0) {
					for (int i = 0; i < diaPeriodico.length; i++) {						
						frecuencias.add(new FrecuenciaViaje(DiasSemana.valueOf(diaPeriodico[i]), estadoFrecuencia, hPartida, hRegreso, this.getAsientosDisponibles(), viaje));						
					}
				}
				fFin = sdf.parse(this.getFechaFin());
			}
			else{				
				frecuencias.add(new FrecuenciaViaje(null, estadoFrecuencia, hPartida, hRegreso, this.getAsientosDisponibles(), viaje));
			}
			fInicio = sdf.parse(this.getFechaInicio());
			
			viaje.setFechaPublicacion(new Date());
			viaje.setDireccionOrigen(this.getDireccionOrigen());
			viaje.setDireccionDestino(this.getDireccionDestino());
			viaje.setPuntosTrayecto(this.getPuntosTrayecto());
			viaje.setFechaInicio(fInicio);
			viaje.setFechaFin(fFin);
			viaje.setDescripcion(this.getDescripcion());
			viaje.setKilometros(this.getKilometros());
			viaje.setConductor(conductor);
			viaje.setFrecuencias(frecuencias);
			viaje.setTramoViaje(TramoViaje.valueOf(this.getTramoViaje()));
			viaje.setTipoViaje(TipoViaje.valueOf(this.getTipoViaje()));
			viaje.setActivo(true);
			
			((ViajeDAOjpa)viajeDAO).registrar(viaje);			
			
			return SUCCESS;			
		}
		else{			
			return "sinPermisos";
		}
	}
	
}
