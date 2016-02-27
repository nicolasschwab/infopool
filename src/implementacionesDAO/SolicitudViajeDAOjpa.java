package implementacionesDAO;

import interfacesDAO.SolicitudViajeDAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.SolicitudViaje;
import model.Viaje;
import model.Viajero;

import org.hibernate.HibernateException;

import util.EntityFactoryUtil;

public class SolicitudViajeDAOjpa extends GenericDAOjpa<SolicitudViaje> implements SolicitudViajeDAO {

	public SolicitudViajeDAOjpa(){
		super(SolicitudViaje.class);
	}	
	
	public <T> boolean tieneSolicitudEstado(T viajero, T viaje, T estadoSolicitud, T diaSemana){		
		boolean tieneSolicitud = false;
		EntityManager em = EntityFactoryUtil.getEm().createEntityManager();
		try{
			String qstr = "select s from "+this.persistentClass.getSimpleName()+" s where s.viaje = :viaje and s.viajero = :viajero and estadoSolicitud = :estadoSolicitud and diaSolicitud = :diaSemana";
			Query q = em.createQuery(qstr);
			q.setParameter("viajero", viajero);
			q.setParameter("viaje", viaje);
			q.setParameter("estadoSolicitud", estadoSolicitud);
			q.setParameter("diaSemana", diaSemana);
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
			String qstr = "select s from "+this.persistentClass.getSimpleName()+" s where s.viajero = :viajero or s.viaje.conductor = :viajero";
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