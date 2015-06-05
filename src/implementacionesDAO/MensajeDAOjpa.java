package implementacionesDAO;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.HibernateException;

import util.EntityFactoryUtil;
import interfacesDAO.MensajeDAO;
import model.Mensaje;
import model.Usuario;


public class MensajeDAOjpa extends GenericDAOjpa<Mensaje> implements MensajeDAO {

	public MensajeDAOjpa(){
		super(Mensaje.class);
	}
	
	public <T> List<T> listar(Usuario user) {
		List<T> resultado = null;
		EntityManager em = EntityFactoryUtil.getEm().createEntityManager();		
		try{			
			Query consulta = em.createQuery("select e from " + persistentClass.getSimpleName() + " e where e.emisor= :usuario or e.receptor= :usuario");		
			consulta.setParameter("usuario",  user );
			resultado = (List<T>) consulta.getResultList();
		}catch(HibernateException e){
			e.printStackTrace();			
		}finally{
			em.close();
		}
		return resultado;
	}
	
	public Mensaje encontrar(Integer id){
		Mensaje resultado = null;
		EntityManager em = EntityFactoryUtil.getEm().createEntityManager();		
		try{			
			resultado = (Mensaje) em.find(this.persistentClass, id);
			resultado.getEmisor().calificacionActual();
			resultado.getReceptor().calificacionActual();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			em.close();
		}
		return resultado;
	}
	
	

}
