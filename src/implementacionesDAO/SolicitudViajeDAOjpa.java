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