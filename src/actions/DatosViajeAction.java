package actions;

import implementacionesDAO.FactoryDAO;
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

import model.Conversacion;
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
	private int idFrecuencia;
	
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

	private ViajeDAO viajeDAO=FactoryDAO.getViajeDAO();
	private ViajeroDAO viajeroDAO=FactoryDAO.getViajeroDAO();
	private FrecuenciaViajeDAO frecuenciaViajeDAO=FactoryDAO.getFrecuenciaViajeDAO();
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
	public int getIdFrecuencia() {
		return idFrecuencia;
	}
	public void setIdFrecuencia(int idFrecuencia) {
		this.idFrecuencia = idFrecuencia;
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
						frecuencias.add(new FrecuenciaViaje(DiasSemana.valueOf(diaPeriodico[i]), estadoFrecuencia, hPartida, hRegreso, this.getAsientosDisponibles(), viaje, this.getPuntosTrayecto(), this.getKilometros(), TramoViaje.valueOf(this.tramoViaje)));						
					}
				}
				fFin = sdf.parse(this.getFechaFin());
			}
			else{				
				frecuencias.add(new FrecuenciaViaje(null, estadoFrecuencia, hPartida, hRegreso, this.getAsientosDisponibles(), viaje, this.getPuntosTrayecto(), this.getKilometros(), TramoViaje.valueOf(this.tramoViaje)));
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
			viaje.setTipoViaje(TipoViaje.valueOf(this.getTipoViaje()));
			viaje.setActivo(true);
			//creo el foro de pasajeros, cuando se acepte una solicitud el ususario debe ser agregado a este foro			
			viaje.setForoViaje(ConversacionAction.crearForo("Foro de pasajeros", viaje, conductor));
			
			((ViajeDAOjpa)viajeDAO).modificar(viaje);			
			
			return SUCCESS;			
		}
		else{			
			return "sinPermisos";
		}
	}
	
	public String EdicionViaje() throws Exception{
		if (SessionUtil.checkLogin()){
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);		
			id = Integer.parseInt(request.getParameter("id"));
			viaje = viajeDAO.encontrarPorId(id);
			return SUCCESS;
		}
		return "sinPermisos";
	}
	
	public String EditarViaje() throws Exception{
		if (SessionUtil.checkLogin()){
			ViajeDAO viajeDAO = FactoryDAO.getViajeDAO();
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);		
			id = Integer.parseInt(request.getParameter("id"));
			viaje = viajeDAO.encontrarPorId(id);			
			if(this.getDescripcion()!=null){
				if(!(this.getDescripcion().equals(viaje.getDescripcion()))){
					viaje.setDescripcion(this.getDescripcion());
				}
			}
			if(this.getFechaInicio()!=null){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date fini = sdf.parse(this.getFechaInicio());
				if(!(viaje.getFechaInicio().equals(fini))){
					viaje.setFechaInicio(fini);
				}
			}
			if(this.getFechaFin()!=null){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date ffin = sdf.parse(this.getFechaFin());
				if(!(viaje.getFechaFin().equals(ffin))){
					viaje.setFechaFin(ffin);
				}
			}
			viajeDAO.modificar(viaje);			
			return SUCCESS;			
		}
		else{			
			return "sinPermisos";
		}
	}
	
	public String EditarFrecuencia() throws Exception{
		if (SessionUtil.checkLogin()){
			if(this.getIdFrecuencia()>0){
				FrecuenciaViaje frecuencia = frecuenciaViajeDAO.encontrar(this.getIdFrecuencia());				
				if(this.getAsientosDisponibles()>0){
					if(!(frecuencia.getAsientosDisponibles()==this.getAsientosDisponibles())){
						frecuencia.setAsientosDisponibles(this.getAsientosDisponibles());
					}
				}				
				if(this.getHoraPartida()!=null){
					Time hPartida = Time.valueOf(this.getHoraPartida()+":00");
					if(!(frecuencia.getHoraPartida().equals(hPartida)))
					frecuencia.setHoraPartida(hPartida);
				}				
				if(this.getHoraRegreso()!=null){
					if(!(this.getHoraRegreso().equals("undefined"))){
						Time hRegreso = Time.valueOf(this.getHoraRegreso()+":00");
						if(!(frecuencia.getHoraRegreso().equals(hRegreso))){
							frecuencia.setHoraRegreso(hRegreso);
						}
					}
				}				
				if(this.getKilometros()!=0.0){					
					if(!(this.getKilometros()==frecuencia.getKilometros())){					
						frecuencia.setKilometros(this.getKilometros());						
					}					
					frecuencia.setPuntosTrayecto(this.getPuntosTrayecto());					
				}
				frecuenciaViajeDAO.modificar(frecuencia);
				id = frecuencia.getViaje().getId();
				return SUCCESS;
			}			
			else{
				System.out.println("Id Frecuencia invalido "+this.getIdFrecuencia());
				return INPUT;
			}
		}
		else{
			return "sinPermisos";
		}
	}
	
}
