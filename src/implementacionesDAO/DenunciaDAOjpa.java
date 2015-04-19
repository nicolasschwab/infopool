package implementacionesDAO;

import interfacesDAO.DenunciaDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

import org.springframework.stereotype.Component;

import model.Denuncia;
import util.EntityFactoryUtil;

@Component
public class DenunciaDAOjpa extends GenericDAOjpa<Denuncia> implements DenunciaDAO {

	public DenunciaDAOjpa(){
		super(Denuncia.class);
	}
	
	public void registrar(Denuncia d){
		EntityManager em = EntityFactoryUtil.getEm().createEntityManager();
		EntityTransaction etx = em.getTransaction();
		try{			
			etx.begin();
			em.persist(d);
			em.flush();
			etx.commit();
		}catch(PersistenceException e){
			System.out.println("Error al registrar el objeto");
			etx.rollback();
		}finally{
			em.close();
		}		
	}
}
