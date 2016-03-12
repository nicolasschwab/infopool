package implementacionesDAO;

import interfacesDAO.ViajeroDAO;

import javax.persistence.EntityManager;

import model.Viajero;

import org.hibernate.HibernateException;

import util.EntityFactoryUtil;

public class ViajeroDAOjpa extends GenericDAOjpa<Viajero> implements ViajeroDAO {
	
	public ViajeroDAOjpa(){
		super(Viajero.class);
	}
	
	public Viajero encontrar(Integer id){
		Viajero resultado = null;
		EntityManager em = EntityFactoryUtil.getEm().createEntityManager();		
		try{			
			resultado = (Viajero) em.find(this.persistentClass, id);
			resultado.getPerfil();			
			resultado.getMisViajesPasajero().size();
			resultado.getMisFrecuenciasPasajero().size();
			resultado.getMiHistorialFrecuencias().size();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			em.close();
		}
		return resultado;
	}
	
}
