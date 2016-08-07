package implementacionesDAO;

import java.util.List;

import interfacesDAO.FrecuenciaViajeDAO;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.FrecuenciaViaje;
import model.SolicitudViaje;
import model.Viaje;
import model.Viajero;

import org.hibernate.HibernateException;

import util.EntityFactoryUtil;

public class FrecuenciaViajeDAOjpa extends GenericDAOjpa<FrecuenciaViaje> implements FrecuenciaViajeDAO {

	public FrecuenciaViajeDAOjpa(){
		super(FrecuenciaViaje.class);
	}

	public <T> FrecuenciaViaje buscarFrecuenciaViajeDia(T viaje, T diaSemana) {
		FrecuenciaViaje frecuencia = null;
		EntityManager em = EntityFactoryUtil.getEm().createEntityManager();		
		try{
			String qstr = "select f from "+ this.persistentClass.getSimpleName() +" f where f.viaje = :viaje and f.diaFrecuencia = :diaFrecuencia";
			Query q = em.createQuery(qstr);
			q.setParameter("viaje", viaje);
			q.setParameter("diaFrecuencia", diaSemana);
			frecuencia = (FrecuenciaViaje) q.getSingleResult();			
		}catch(HibernateException e){
			e.printStackTrace();			
		}finally{
			em.close();
		}	
		return frecuencia;
	}
	
	public FrecuenciaViaje encontrar(Integer id){
		FrecuenciaViaje resultado = null;
		EntityManager em = EntityFactoryUtil.getEm().createEntityManager();		
		try{			
			resultado = (FrecuenciaViaje) em.find(this.persistentClass, id);
			resultado.getPasajeros().size();
			resultado.getPasajerosHistorial().size();
			resultado.getSolicitudesViaje().size();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			em.close();
		}
		return resultado;
	}
	
	public <T> int cantidadFrecuenciasEnViaje(T viajero, T viaje) {
		int unicaFrecuencia = 0;
		EntityManager em = EntityFactoryUtil.getEm().createEntityManager();
		try{			
			String qstr = "select f from "+ this.persistentClass.getSimpleName() +" f where f.viaje = :viaje and :viajero in elements(f.pasajeros)";
			Query q = em.createQuery(qstr);
			q.setParameter("viaje", viaje);
			q.setParameter("viajero", viajero);
			unicaFrecuencia = q.getResultList().size();
		}catch(HibernateException e){
			e.printStackTrace();			
		}finally{
			em.close();
		}
		return unicaFrecuencia;
	}
	
	public <T> List<FrecuenciaViaje> obtenerFrecuenciasViaje(Integer idViaje){
		List<FrecuenciaViaje> listadoFrecuencias = null;
		EntityManager em = EntityFactoryUtil.getEm().createEntityManager();
		try{
			String qstr = "select f from "+ this.persistentClass.getSimpleName() +" f where f.viaje.id = :viaje";
			Query q = em.createQuery(qstr);
			q.setParameter("viaje", idViaje);
			listadoFrecuencias = (List<FrecuenciaViaje>) q.getResultList();
		}catch(HibernateException e){
			e.printStackTrace();			
		}finally{
			em.close();
		}
		return listadoFrecuencias;
	}
}