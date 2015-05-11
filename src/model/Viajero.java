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

import javax.imageio.ImageIO;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Viajero extends Usuario implements Serializable{	
	private static final long serialVersionUID = 1L;	
	private String nombre;
	private String apellido;
	private String telefono;
	private String mail;
	private Date fechaNacimiento;
	private String preferenciasViaje;
	private Date fechaIngresoSistema;	
	private byte[] fotoPerfil;
	private boolean activo;	
	@OneToMany(mappedBy = "denunciante", cascade={CascadeType.PERSIST, CascadeType.REMOVE})
	private Collection<Denuncia> misDenunciasEmitidas;	
	@OneToMany(mappedBy = "denunciado", cascade={CascadeType.PERSIST, CascadeType.REMOVE})
	private Collection<Denuncia> misDenunciasRecibidas;	
	@OneToMany(mappedBy = "conductor", cascade={CascadeType.PERSIST, CascadeType.REMOVE})
	private Collection<Viaje> misViajesConductor;	
	@ManyToMany(cascade = {CascadeType.ALL},fetch=FetchType.EAGER)
    @JoinTable(name="pasajeros_viaje",
                joinColumns={@JoinColumn(name="PASAJERO_ID")},
                inverseJoinColumns={@JoinColumn(name="VIAJE_ID")})
	private Collection<Viaje> misViajesPasajero;	
	@OneToMany(mappedBy = "viajero", cascade={CascadeType.PERSIST, CascadeType.REMOVE})	
	private Collection<SolicitudViaje> misSolicitudes;	
	@OneToMany(mappedBy = "calificador", cascade={CascadeType.PERSIST, CascadeType.REMOVE})
	private Collection<Calificacion> misCalificacionesHechas;	
	@OneToMany(mappedBy = "calificado", cascade={CascadeType.PERSIST, CascadeType.REMOVE})
	private Collection<Calificacion> misCalificacionesRecibidas;
	
	public Viajero() {		
	}
	
	public Viajero(String usuario, String clave, String nombre, String apellido, String telefono, String mail, Date fechaNacimiento, String preferenciasViaje) {
		super(usuario,clave);		
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.mail = mail;
		this.fechaNacimiento = fechaNacimiento;
		this.preferenciasViaje = preferenciasViaje;
		this.fechaIngresoSistema = new Date();
		this.activo = true;
		this.misDenunciasEmitidas = new ArrayList<Denuncia>();
		this.misDenunciasRecibidas = new ArrayList<Denuncia>();
		this.misViajesConductor = new ArrayList<Viaje>();
		this.misViajesPasajero = new ArrayList<Viaje>();
		this.misSolicitudes = new ArrayList<SolicitudViaje>();
		this.misCalificacionesHechas = new ArrayList<Calificacion>();
		this.misCalificacionesRecibidas = new ArrayList<Calificacion>();		
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
	public String getPreferenciasViaje() {
		return preferenciasViaje;
	}
	public void setPreferenciasViaje(String preferenciasViaje) {
		this.preferenciasViaje = preferenciasViaje;
	}
	public Date getFechaIngresoSistema() {
		return fechaIngresoSistema;
	}
	public void setFechaIngresoSistema(Date fechaIngresoSistema) {
		this.fechaIngresoSistema = fechaIngresoSistema;
	}
	public boolean getActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	public Collection<Denuncia> getMisDenunciasEmitidas() {
		return misDenunciasEmitidas;
	}
	public void setMisDenunciasEmitidas(Collection<Denuncia> misDenunciasEmitidas) {
		this.misDenunciasEmitidas = misDenunciasEmitidas;
	}
	public Collection<Denuncia> getMisDenunciasRecibidas() {
		return misDenunciasRecibidas;
	}
	public void setMisDenunciasRecibidas(Collection<Denuncia> misDenunciasRecibidas) {
		this.misDenunciasRecibidas = misDenunciasRecibidas;
	}
	public Collection<Viaje> getMisViajesConductor() {
		return misViajesConductor;
	}
	public void setMisViajesConductor(Collection<Viaje> misViajesConductor) {
		this.misViajesConductor = misViajesConductor;
	}
	public Collection<Viaje> getMisViajesPasajero() {
		return misViajesPasajero;
	}
	public void setMisViajesPasajero(Collection<Viaje> misViajesPasajero) {
		this.misViajesPasajero = misViajesPasajero;
	}
	public Collection<SolicitudViaje> getMisSolicitudes() {
		return misSolicitudes;
	}
	public void setMisSolicitudes(Collection<SolicitudViaje> misSolicitudes) {
		this.misSolicitudes = misSolicitudes;
	}
	public Collection<Calificacion> getMisCalificacionesHechas() {
		return misCalificacionesHechas;
	}
	public void setMisCalificacionesHechas(
			Collection<Calificacion> misCalificacionesHechas) {
		this.misCalificacionesHechas = misCalificacionesHechas;
	}
	public Collection<Calificacion> getMisCalificacionesRecibidas() {
		return misCalificacionesRecibidas;
	}
	public void setMisCalificacionesRecibidas(
			Collection<Calificacion> misCalificacionesRecibidas) {
		this.misCalificacionesRecibidas = misCalificacionesRecibidas;
	}	
	public byte[] getFotoPerfil() {
		return fotoPerfil;
	}
	public void setFotoPerfil(byte[] fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}
	
	public String getPerfil(){
		return "viajero";
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
	
	public float calificacionActual(){		
		int total=0;
		for(Calificacion unaCalificacion : this.misCalificacionesRecibidas){
				total+=unaCalificacion.getCalificacion();
		}
		if(total==0){
			return (float) 0;
		}else{
			return (float) Math.round((((float) total) / ((float) this.misCalificacionesRecibidas.size())) * 100) / 100;
		}
	}
		
}
