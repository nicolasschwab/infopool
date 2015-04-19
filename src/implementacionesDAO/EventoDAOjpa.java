package implementacionesDAO;

import interfacesDAO.EventoDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.Evento;
import org.hibernate.HibernateException;
import util.EntityFactoryUtil;

public class EventoDAOjpa extends GenericDAOjpa<Evento> implements EventoDAO {

	public EventoDAOjpa(){
		super(Evento.class);
	}

	public boolean existeNombreEvento(String nombre) {
		boolean existe = false;
		EntityManager em = EntityFactoryUtil.getEm().createEntityManager();		
		try{
			String qString = "select e from "+ this.persistentClass.getSimpleName() +" e where e.nombre = :ev ";
			Query consulta = em.createQuery(qString);
			consulta.setParameter("ev", nombre);
			List<Evento> resultado = (List<Evento>) consulta.getResultList();
			if (resultado.size() > 0){				
				existe = true;
			}
		}catch(HibernateException e){
			e.printStackTrace();			
		}finally{
			em.close();
		}
		return existe;
	}
	
	public boolean existeNombreEvento(String nombre, int idev) {
		boolean existe = false;
		EntityManager em = EntityFactoryUtil.getEm().createEntityManager();		
		try{
			String qString = "select e from "+ this.persistentClass.getSimpleName() +" e where e.nombre = :ev and e.id != :ide ";
			Query consulta = em.createQuery(qString);
			consulta.setParameter("ev", nombre);
			consulta.setParameter("ide", idev);
			List<Evento> resultado = (List<Evento>) consulta.getResultList();
			if (resultado.size() > 0){				
				existe = true;
			}
		}catch(HibernateException e){
			e.printStackTrace();			
		}finally{
			em.close();
		}
		return existe;
	}
	
	public Evento encontrarObjeto(int id){
		Evento existe = null;
		EntityManager em = EntityFactoryUtil.getEm().createEntityManager();		
		try{
			String qString = "select e from "+ this.persistentClass.getSimpleName() +" e where e.id = :ev ";
			Query consulta = em.createQuery(qString);
			consulta.setParameter("ev", id);
			List<EventoDAO> resultado = (List<EventoDAO>) consulta.getResultList();
			if (resultado.size() > 0){				
				existe = (Evento) resultado.get(0);
			}
		}catch(HibernateException e){
			e.printStackTrace();			
		}finally{
			em.close();
		}
		return existe;
	}
}
