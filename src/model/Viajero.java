package model;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Viajero extends Usuario implements Serializable{	
	
	private static final long serialVersionUID = 1L;	
	
	@Column(nullable=false)
	private String nombre;
	
	@Column(nullable=false)
	private String apellido;
	
	@Column
	private String telefono;
	
	@Column(nullable=false,unique=true)
	private String mail;
	
	@Column
	private Date fechaNacimiento;
	
	@Lob
	@Column(length=16777215)
	private byte[] fotoPerfil;
	
	@Column(nullable=false)
	private Date fechaIngresoSistema;
	
	@ElementCollection(targetClass=PreferenciasViaje.class)
	@Enumerated(EnumType.STRING)
	private List<PreferenciasViaje> preferenciasViaje = new ArrayList<PreferenciasViaje>();
	
	@OneToMany(mappedBy="denunciado")
	private Collection<Denuncia> misDenuncias = new ArrayList<Denuncia>();
	
	@OneToMany(mappedBy="denunciante")
	private Collection<Denuncia> misDenunciasRealizadas = new ArrayList<Denuncia>();
	
	@OneToMany(mappedBy="viajero")	
	private Collection<SolicitudViaje> misSolicitudes = new ArrayList<SolicitudViaje>();	
	
	@OneToMany(mappedBy="conductor")
	private Collection<Viaje> misViajesConductor = new ArrayList<Viaje>();
	
	@ManyToMany
    @JoinTable( name="Viajero_Frecuencia_Actual",
				joinColumns={@JoinColumn(name="viajero_id", nullable=false)},
				inverseJoinColumns={@JoinColumn(name="frecuencia_id", nullable=false)})
    private Collection<FrecuenciaViaje> misFrecuenciasPasajero = new ArrayList<FrecuenciaViaje>();
	
	@ManyToMany
    @JoinTable( name="Viajero_Frecuencia_Historial",
				joinColumns={@JoinColumn(name="viajero_id", nullable=false)},
				inverseJoinColumns={@JoinColumn(name="frecuencia_id", nullable=false)})
    private Collection<FrecuenciaViaje> miHistorialFrecuencias = new ArrayList<FrecuenciaViaje>();
	
	@ManyToMany
	@JoinTable( name="Viajero_Viaje_Pasajero",
				joinColumns={@JoinColumn(name="viajero_id", nullable=false)},
				inverseJoinColumns={@JoinColumn(name="viaje_id", nullable=false)})
	private Collection<Viaje> misViajesPasajero = new ArrayList<Viaje>();
	
	@OneToMany(mappedBy="viajeroHuella")
	private Collection<HuellaCarbono> miHuellaCarbono = new ArrayList<HuellaCarbono>();
	
	@OneToMany(mappedBy = "calificado")
    private List<Calificacion> misCalificaciones = new ArrayList<Calificacion>();
    
    @OneToMany(mappedBy = "calificador")
    private List<Calificacion> misCalificacionesRealizadas = new ArrayList<Calificacion>();
    
    @Column
	private Auto auto;
    
    @Column(nullable=false)
	private float calificacion;
    
    @Column(nullable=false)
	private boolean activo;		

    
	public Viajero() {	
		super();
	}
	
	public Viajero(String nombre, String apellido, String telefono,
			String mail, Date fechaNacimiento, byte[] fotoPerfil, String usuario, 
			String clave, Date fechaIngresoSistema, boolean activo) {
		super(usuario,clave);
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.mail = mail;
		this.fechaNacimiento = fechaNacimiento;
		this.fotoPerfil = fotoPerfil;
		this.fechaIngresoSistema = fechaIngresoSistema;
		this.activo = activo;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public byte[] getFotoPerfil() {
		return fotoPerfil;
	}
	public void setFotoPerfil(byte[] fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}
	public Date getFechaIngresoSistema() {
		return fechaIngresoSistema;
	}
	public void setFechaIngresoSistema(Date fechaIngresoSistema) {
		this.fechaIngresoSistema = fechaIngresoSistema;
	}
	public List<PreferenciasViaje> getPreferenciasViaje() {
		return preferenciasViaje;
	}
	public void setPreferenciasViaje(List<PreferenciasViaje> preferenciasViaje) {
		this.preferenciasViaje = preferenciasViaje;
	}
	public Collection<Denuncia> getMisDenuncias() {
		return misDenuncias;
	}
	public void setMisDenuncias(Collection<Denuncia> misDenuncias) {
		this.misDenuncias = misDenuncias;
	}
	public Collection<Denuncia> getMisDenunciasRealizadas() {
		return misDenunciasRealizadas;
	}
	public void setMisDenunciasRealizadas(
			Collection<Denuncia> misDenunciasRealizadas) {
		this.misDenunciasRealizadas = misDenunciasRealizadas;
	}
	public Collection<SolicitudViaje> getMisSolicitudes() {
		return misSolicitudes;
	}
	public void setMisSolicitudes(Collection<SolicitudViaje> misSolicitudes) {
		this.misSolicitudes = misSolicitudes;
	}
	public Collection<Viaje> getMisViajesConductor() {
		return misViajesConductor;
	}
	public void setMisViajesConductor(Collection<Viaje> misViajesConductor) {
		this.misViajesConductor = misViajesConductor;
	}
	public Collection<FrecuenciaViaje> getMisFrecuenciasPasajero() {
		return misFrecuenciasPasajero;
	}
	public void setMisFrecuenciasPasajero(
			Collection<FrecuenciaViaje> misFrecuenciasPasajero) {
		this.misFrecuenciasPasajero = misFrecuenciasPasajero;
	}
	public Collection<FrecuenciaViaje> getMiHistorialFrecuencias() {
		return miHistorialFrecuencias;
	}
	public void setMiHistorialFrecuencias(
			Collection<FrecuenciaViaje> miHistorialFrecuencias) {
		this.miHistorialFrecuencias = miHistorialFrecuencias;
	}
	public Collection<Viaje> getMisViajesPasajero() {
		return misViajesPasajero;
	}
	public void setMisViajesPasajero(Collection<Viaje> misViajesPasajero) {
		this.misViajesPasajero = misViajesPasajero;
	}
	public Collection<HuellaCarbono> getMiHuellaCarbono() {
		return miHuellaCarbono;
	}
	public void setMiHuellaCarbono(Collection<HuellaCarbono> miHuellaCarbono) {
		this.miHuellaCarbono = miHuellaCarbono;
	}
	public List<Calificacion> getMisCalificaciones() {
		return misCalificaciones;
	}
	public void setMisCalificaciones(List<Calificacion> misCalificaciones) {
		this.misCalificaciones = misCalificaciones;
	}
	public List<Calificacion> getMisCalificacionesRealizadas() {
		return misCalificacionesRealizadas;
	}
	public void setMisCalificacionesRealizadas(
			List<Calificacion> misCalificacionesRealizadas) {
		this.misCalificacionesRealizadas = misCalificacionesRealizadas;
	}
	public Auto getAuto() {
		return auto;
	}
	public void setAuto(Auto auto) {
		this.auto = auto;
	}
	public float getCalificacion() {
		return calificacion;
	}
	public void setCalificacion(float calificacion) {
		this.calificacion = calificacion;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}	
	@Override
	public String getPerfil() {
		return "viajero";
	}	
	@Override
	public boolean getActivo() {
		return activo;
	}
	@Override
	public boolean soyAdministrador(){
		return false;
	}
	
	public String obtenerNombre(){
		return this.nombre + " "+ this.apellido;
	}
	public ByteArrayOutputStream getFotoIMG(){
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		InputStream in = new ByteArrayInputStream(this.fotoPerfil);
		BufferedImage bImageFromConvert = null;
		try {
			bImageFromConvert = ImageIO.read(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			ImageIO.write(bImageFromConvert, "jpg", out);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return out;
	}
	public void agregarViajePasajero(Viaje viaje){
		this.misViajesPasajero.add(viaje);
	}	
	public void quitarViajePasajero(Viaje viaje){		
		for (Viaje v : misViajesPasajero) {
			if (v.getId()==viaje.getId()){				
				this.misViajesPasajero.remove(v);
				break;
			}
		}
	}
	public void agregarFrecuenciaPasajero(FrecuenciaViaje frecuenciaViaje){
		this.misFrecuenciasPasajero.add(frecuenciaViaje);
	}
	public void quitarFrecuenciaPasajero(FrecuenciaViaje frecuenciaViaje){
		for (FrecuenciaViaje f : this.misFrecuenciasPasajero) {
			if (f.getId()==frecuenciaViaje.getId()){				
				this.misFrecuenciasPasajero.remove(f);
				this.miHistorialFrecuencias.add(frecuenciaViaje);
				break;
			}
		}
	}
}