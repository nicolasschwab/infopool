package implementacionesDAO;

import interfacesDAO.ViajeroDAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Usuario;
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
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			em.close();
		}
		return resultado;
	}
	
	@Override
	public Viajero encontrarUsuarioSistema() {
		Viajero usr=null;
		EntityManager em = EntityFactoryUtil.getEm().createEntityManager();		
		try{			
			String qString = "select u from "+ this.persistentClass.getSimpleName() +" u where u.usuario ='usuarioSistemaInfopool' ";
			Query consulta = em.createQuery(qString);	
			List<Viajero> resultado = (List<Viajero>) consulta.getResultList();
			if (resultado.size() > 0){
				usr = resultado.get(0);		
			}
		}catch(HibernateException e){
			e.printStackTrace();			
		}finally{
			em.close();
		}
		return usr;
	}
}
