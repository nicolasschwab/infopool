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
	
	public Mensaje encontrar(Integer id){
		Mensaje resultado = null;
		EntityManager em = EntityFactoryUtil.getEm().createEntityManager();		
		try{			
			resultado = (Mensaje) em.find(this.persistentClass, id);
			resultado.getEmisor().calificacionActual();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			em.close();
		}
		return resultado;
	}
	
	

}
