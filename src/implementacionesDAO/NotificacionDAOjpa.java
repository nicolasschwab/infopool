package implementacionesDAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.HibernateException;

import interfacesDAO.NotificacionDAO;
import model.Notificacion;
import model.Usuario;
import model.Viaje;
import model.Viajero;
import util.EntityFactoryUtil;

public class NotificacionDAOjpa extends GenericDAOjpa<Notificacion> implements NotificacionDAO {

	public NotificacionDAOjpa(){
		super(Notificacion.class);
	}	
	
	public List<Notificacion> ListarPorUsuario(Usuario usr){
		String qString = "select e from "+ this.persistentClass.getSimpleName() +" e where e.receptor= :usr ";
		List<Notificacion> resultados=null;
		EntityManager em = EntityFactoryUtil.getEm().createEntityManager();		
		try{			
			Query consulta = em.createQuery(qString);
			consulta.setParameter("usr", usr );
			resultados = (List<Notificacion>) consulta.getResultList();
			for(Notificacion notificacion: resultados){
				notificacion.getEmisor().getId();
				notificacion.getReceptor().getId();
			}
		}catch(HibernateException e){
			e.printStackTrace();			
		}finally{
			em.close();
		}
		return resultados;	
	}

	@Override
	public Notificacion encontrarPorId(String id) {		
		String qString = "select e from "+ this.persistentClass.getSimpleName() +" e where e.id= :id ";
		List<Notificacion> resultados=null;
		EntityManager em = EntityFactoryUtil.getEm().createEntityManager();		
		try{			
			Query consulta = em.createQuery(qString);
			consulta.setParameter("id", id );
			resultados = (List<Notificacion>) consulta.getResultList();
			if(!resultados.isEmpty()){				
				resultados.get(0).getEmisor().getId();
				resultados.get(0).getReceptor().getId();				
			}
		}catch(HibernateException e){
			e.printStackTrace();			
		}finally{
			em.close();
		}
		if(resultados.isEmpty()){
			return null;
		}
		return resultados.get(0);	
	}
}
