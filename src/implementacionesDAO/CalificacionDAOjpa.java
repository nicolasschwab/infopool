package implementacionesDAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.HibernateException;

import interfacesDAO.CalificacionDAO;
import model.Calificacion;
import model.FrecuenciaViaje;
import model.Viaje;
import model.Viajero;
import util.EntityFactoryUtil;

public class CalificacionDAOjpa extends GenericDAOjpa<Calificacion> implements CalificacionDAO {
	
	public CalificacionDAOjpa(){
		super(Calificacion.class);
	}

	public Calificacion encontrarCalificacion(Viajero calificador, Viajero calificado, Viaje viaje) {
		List<Calificacion>listadoCalificaciones=null;
		EntityManager em = EntityFactoryUtil.getEm().createEntityManager();		
		try{
			String qstr = "select e from "+ this.persistentClass.getSimpleName() +" e where e.calificador.id= :calificador and e.calificado.id = :calificado and viaje.id= :viaje ";
			Query q = em.createQuery(qstr);
			q.setParameter("calificador", calificador.getId());			
			q.setParameter("calificado", calificado.getId());
			q.setParameter("viaje", viaje.getId());
			listadoCalificaciones = (List<Calificacion>) q.getResultList();
			}catch(HibernateException e){
				e.printStackTrace();			
			}finally{
				em.close();
			}		
		if(listadoCalificaciones.isEmpty()){
			return null;
		}else{
			return listadoCalificaciones.get(0);
		}
	}

	public List<Calificacion> listarCalificaciones(int id) {
		List<Calificacion>listadoCalificaciones=null;
		EntityManager em = EntityFactoryUtil.getEm().createEntityManager();		
		try{
			String qstr = "select e from "+ this.persistentClass.getSimpleName() +" e where e.calificado.id = :id ";
			Query q = em.createQuery(qstr);
			q.setParameter("id", id);			
			listadoCalificaciones = (List<Calificacion>) q.getResultList();
			}catch(HibernateException e){
				e.printStackTrace();			
			}finally{
				em.close();
			}		
		if(listadoCalificaciones.isEmpty()){
			return null;
		}else{
			return listadoCalificaciones;
		}
	}
}
