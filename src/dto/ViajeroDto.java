package dto;

import java.util.Date;

public class ViajeroDto {

	private int id;
	
	private String usuario;
	
	private String nombre;	

	private String apellido;
	
	private float calificacion;
	
	private String mail;
	
	private Date fechaIngresoSistema;
	
	private Date fechaNacimiento;	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFechaIngresoSistema() {
		return fechaIngresoSistema;
	}

	public void setFechaIngresoSistema(Date fechaIngresoSistema) {
		this.fechaIngresoSistema = fechaIngresoSistema;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
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

	public float getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(float calificacion) {
		this.calificacion = calificacion;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}	
	
	
	
}
