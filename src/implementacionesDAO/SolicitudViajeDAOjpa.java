package implementacionesDAO;

import interfacesDAO.SolicitudViajeDAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.FrecuenciaViaje;
import model.SolicitudViaje;
import model.Viaje;
import model.Viajero;

import org.hibernate.HibernateException;

import util.EntityFactoryUtil;

public class SolicitudViajeDAOjpa extends GenericDAOjpa<SolicitudViaje> implements SolicitudViajeDAO {

	public SolicitudViajeDAOjpa(){
		super(SolicitudViaje.class);
	}	
	
	public <T> boolean tieneSolicitudEstado(T viajero, T frecuenciaViaje, T estadoSolicitud){		
		boolean tieneSolicitud = false;
		EntityManager em = EntityFactoryUtil.getEm().createEntityManager();
		try{
			String qstr = "select s from "+this.persistentClass.getSimpleName()+" s where s.frecuenciaViaje = :frecuenciaViaje and s.viajero = :viajero and estadoSolicitud = :estadoSolicitud";
			Query q = em.createQuery(qstr);
			q.setParameter("viajero", viajero);
			q.setParameter("frecuenciaViaje", frecuenciaViaje);
			q.setParameter("estadoSolicitud", estadoSolicitud);			
			tieneSolicitud = (!q.getResultList().isEmpty());			
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			em.close();
		}
		return tieneSolicitud;
	}
	
	public <T> boolean tieneSolicitudEstado(T viajero, T frecuenciaViaje){		
		boolean tieneSolicitud = false;
		EntityManager em = EntityFactoryUtil.getEm().createEntityManager();
		try{
			String qstr = "select s from "+this.persistentClass.getSimpleName()+" s where s.frecuenciaViaje = :frecuenciaViaje and s.viajero = :viajero";
			Query q = em.createQuery(qstr);
			q.setParameter("viajero", viajero);
			q.setParameter("frecuenciaViaje", frecuenciaViaje);
			tieneSolicitud = (!q.getResultList().isEmpty());			
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			em.close();
		}
		return tieneSolicitud;
	}
	
	public <T> List<SolicitudViaje> obtenerUltimasSolicitudes(T viajero){
		List<SolicitudViaje> listadoSolicitudes = null;
		EntityManager em = EntityFactoryUtil.getEm().createEntityManager();
		try{
			String qstr = "select s from "+this.persistentClass.getSimpleName()+" s where s.viajero = :viajero or s.frecuenciaViaje.viaje.conductor = :viajero";
			Query q = em.createQuery(qstr);
			q.setParameter("viajero", viajero);
			listadoSolicitudes = (List<SolicitudViaje>) q.getResultList();
		}catch(HibernateException e){
			e.printStackTrace();			
		}finally{
			em.close();
		}
		return listadoSolicitudes;		
	}
	
	public <T> List<SolicitudViaje> buscarSolicitudesFrecuencia(T frecuenciaViaje){
		List<SolicitudViaje> listadoSolicitudes = null;
		EntityManager em = EntityFactoryUtil.getEm().createEntityManager();
		try{
			String qstr = "select s from "+this.persistentClass.getSimpleName()+" s where s.frecuenciaViaje = :frecuenciaViaje";
			Query q = em.createQuery(qstr);
			q.setParameter("frecuenciaViaje", frecuenciaViaje);
			listadoSolicitudes = (List<SolicitudViaje>) q.getResultList();
		}catch(HibernateException e){
			e.printStackTrace();			
		}finally{
			em.close();
		}
		return listadoSolicitudes;
	}
	
	/* Revisar estos metodos */
	public List<SolicitudViaje> yaSolicito(Viaje viaje,Viajero viajero){
		String qString = "select s from "+ this.persistentClass.getSimpleName() +" s where s.viaje = :viaje and s.viajero=:viajero ";
		List<SolicitudViaje> resultado = null;
		EntityManager em = EntityFactoryUtil.getEm().createEntityManager();		
		try{			
			Query consulta = em.createQuery(qString);
			consulta.setParameter("viaje", viaje);
			consulta.setParameter("viajero", viajero);
			resultado = (List<SolicitudViaje>) consulta.getResultList();			
		}catch(HibernateException e){
			e.printStackTrace();			
		}finally{
			em.close();
		}
		return resultado;
	}

	public List<SolicitudViaje> listarSolicitudesViaje(Viaje viaje) {
		String qString = "select s from "+ this.persistentClass.getSimpleName() +" s where s.viaje = :id ";
		List<SolicitudViaje> resultado = null;
		EntityManager em = EntityFactoryUtil.getEm().createEntityManager();		
		try{			
			Query consulta = em.createQuery(qString);
			consulta.setParameter("id", viaje);
			resultado = (List<SolicitudViaje>) consulta.getResultList();			
		}catch(HibernateException e){
			e.printStackTrace();			
		}finally{
			em.close();
		}
		return resultado;		
	}



}