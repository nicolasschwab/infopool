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

	public <T> void registrar(T objectT) {
		EntityManager em = EntityFactoryUtil.getEm().createEntityManager();
		EntityTransaction etx = em.getTransaction();
		try{			
			etx.begin();
			em.persist(objectT);
			em.flush();
			etx.commit();
		}catch(PersistenceException e){
			System.out.println("Error al registrar el objeto");
			etx.rollback();
		}finally{
			em.close();
		}
	}

	public <T> void eliminar(T objectT) {
		EntityManager em = EntityFactoryUtil.getEm().createEntityManager();
		EntityTransaction etx = em.getTransaction();
		try{			
			etx.begin();
			T objectToBeRemoved = (T) em.getReference(persistentClass,objectT);
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

	public <T> void modificar(T objectT) {
		EntityManager em = EntityFactoryUtil.getEm().createEntityManager();
		EntityTransaction etx = em.getTransaction();
		try{			
			etx.begin();
			em.merge(objectT);
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
		List<T> result = null;
		EntityManager em = EntityFactoryUtil.getEm().createEntityManager();		
		try{			
			Query q = em.createQuery("select e from " + persistentClass.getSimpleName() + " e");
			result = (List<T>) q.getResultList();
		}catch(HibernateException e){
			e.printStackTrace();			
		}finally{
			em.close();
		}
		return result;
	}
	
	public <T> T encontrar(Serializable object){
		T result = null;
		EntityManager em = EntityFactoryUtil.getEm().createEntityManager();		
		try{			
			result = (T) em.find(persistentClass, object);
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			em.close();
		}
		return result;
	}
}