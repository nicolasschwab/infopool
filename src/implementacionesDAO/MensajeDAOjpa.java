package implementacionesDAO;

import interfacesDAO.MensajeDAO;

import javax.persistence.EntityManager;

import model.Mensaje;

import org.hibernate.HibernateException;

import util.EntityFactoryUtil;


public class MensajeDAOjpa extends GenericDAOjpa<Mensaje> implements MensajeDAO {

	public MensajeDAOjpa(){
		super(Mensaje.class);
	}
	
	public Mensaje encontrar(Integer id){
		Mensaje resultado = null;
		EntityManager em = EntityFactoryUtil.getEm().createEntityManager();		
		try{			
			resultado = (Mensaje) em.find(this.persistentClass, id);			
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			em.close();
		}
		return resultado;
	}
	
	

}
