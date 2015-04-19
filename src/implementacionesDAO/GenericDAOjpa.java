package implementacionesDAO;

import interfacesDAO.GenericDAO;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import org.hibernate.HibernateException;
import util.EntityFactoryUtil;

public class GenericDAOjpa<T> implements GenericDAO<T> {
	
	protected Class<T> persistentClass;
	
	public GenericDAOjpa(Class<T> persistentClass) {
		super();
		this.persistentClass = persistentClass;
	}

	public <T> void registrar(T objetoT) {
		EntityManager em = EntityFactoryUtil.getEm().createEntityManager();
		EntityTransaction etx = em.getTransaction();
		try{			
			etx.begin();
			em.persist(objetoT);
			em.flush();
			etx.commit();
		}catch(PersistenceException e){
			System.out.println("Error al registrar el objeto");
			etx.rollback();
		}finally{
			em.close();
		}
	}

	public <T> void eliminar(T objetoT) {
		EntityManager em = EntityFactoryUtil.getEm().createEntityManager();
		EntityTransaction etx = em.getTransaction();
		try{			
			etx.begin();
			T objectToBeRemoved = (T) em.getReference(persistentClass,objetoT);
			em.remove(objectToBeRemoved);
			em.flush();
			etx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
			etx.rollback();
		}finally{
			em.close();
		}
	}

	public <T> void modificar(T objetoT) {
		EntityManager em = EntityFactoryUtil.getEm().createEntityManager();
		EntityTransaction etx = em.getTransaction();
		try{			
			etx.begin();
			em.merge(objetoT);
			em.flush();
			etx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
			etx.rollback();
		}finally{
			em.close();
		}
	}

	public <T> List<T> listar() {
		List<T> resultado = null;
		EntityManager em = EntityFactoryUtil.getEm().createEntityManager();		
		try{			
			Query consulta = em.createQuery("select e from " + persistentClass.getSimpleName() + " e");
			resultado = (List<T>) consulta.getResultList();
		}catch(HibernateException e){
			e.printStackTrace();			
		}finally{
			em.close();
		}
		return resultado;
	}
	
	public <T> T encontrar(Serializable objeto){
		T resultado = null;
		EntityManager em = EntityFactoryUtil.getEm().createEntityManager();		
		try{			
			resultado = (T) em.find(persistentClass, objeto);
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			em.close();
		}
		return resultado;
	}
}
