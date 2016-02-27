package implementacionesDAO;

import interfacesDAO.FrecuenciaViajeDAO;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.FrecuenciaViaje;

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
}