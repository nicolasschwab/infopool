package implementacionesDAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.HibernateException;

import interfacesDAO.EstadoNotificacionDAO;
import model.EstadoNotificacion;
import util.EntityFactoryUtil;

public class EstadoNotificacionDAOjpa extends GenericDAOjpa<EstadoNotificacion> implements EstadoNotificacionDAO {

	public EstadoNotificacionDAOjpa(){
		super(EstadoNotificacion.class);
	}
	public EstadoNotificacion traerNoVisto(){
		String qString = "select e from "+ this.persistentClass.getSimpleName() +" e where e.id=1 ";
		return this.ejecutarConsulta(qString);
	}
	public EstadoNotificacion traerVisto(){
		String qString = "select e from "+ this.persistentClass.getSimpleName() +" e where e.id=2 ";
		return this.ejecutarConsulta(qString);
	}
	public EstadoNotificacion traerVisitado(){
		String qString = "select e from "+ this.persistentClass.getSimpleName() +" e where e.id=3 ";
		return this.ejecutarConsulta(qString);
	}
	private EstadoNotificacion ejecutarConsulta(String qString){
		List<EstadoNotificacion> resultado=null;
		EntityManager em = EntityFactoryUtil.getEm().createEntityManager();		
		try{			
			Query consulta = em.createQuery(qString);
			resultado = (List<EstadoNotificacion>)consulta.getResultList();
		}catch(HibernateException e){
			e.printStackTrace();			
		}finally{
			em.close();
		}
		return resultado.get(0);
	}
}
