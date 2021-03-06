package implementacionesDAO;

import interfacesDAO.UsuarioDAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Notificacion;
import model.Usuario;

import org.hibernate.HibernateException;

import util.EntityFactoryUtil;

public class UsuarioDAOjpa extends GenericDAOjpa<Usuario> implements UsuarioDAO {
	
	public UsuarioDAOjpa() {
		super(Usuario.class);
	}

	public Usuario existeNombreUsuario(String nombreUsuario){
		Usuario usr = null;
		EntityManager em = EntityFactoryUtil.getEm().createEntityManager();		
		try{
			String qString = "select u from "+ this.persistentClass.getSimpleName() +" u where u.usuario = :usr ";
			Query consulta = em.createQuery(qString);
			consulta.setParameter("usr", nombreUsuario);
			List<Usuario> resultado = (List<Usuario>) consulta.getResultList();
			if (resultado.size() > 0){
				usr = (Usuario) resultado.get(0);
			}
		}catch(HibernateException e){
			e.printStackTrace();			
		}finally{
			em.close();
		}
		return usr;		
	}
	
	public Usuario existe(String usuario, String clave) {
		Usuario usr = null;
		EntityManager em = EntityFactoryUtil.getEm().createEntityManager();		
		try{			
			String qString = "select u from "+ this.persistentClass.getSimpleName() +" u where u.usuario = :usr and u.clave = :clave";
			Query consulta = em.createQuery(qString);
			consulta.setParameter("usr", usuario);
			consulta.setParameter("clave", clave);			
			List<Usuario> resultado = (List<Usuario>) consulta.getResultList();
			if (resultado.size() > 0){
				usr = (Usuario) resultado.get(0);								
			}
		}catch(HibernateException e){
			e.printStackTrace();			
		}finally{
			em.close();
		}		
		return usr;		
	}

	

	@Override
	public Usuario encontrarPorId(String participante) {		
		Usuario usr=null;
		EntityManager em = EntityFactoryUtil.getEm().createEntityManager();		
		try{			
			String qString = "select u from "+ this.persistentClass.getSimpleName() +" u where u.id= :id ";
			Query consulta = em.createQuery(qString);
			consulta.setParameter("id", Integer.parseInt(participante));
			List<Usuario> resultado = (List<Usuario>) consulta.getResultList();
			if (resultado.size() > 0){
				usr = (Usuario) resultado.get(0);			
			}
		}catch(HibernateException e){
			e.printStackTrace();			
		}finally{
			em.close();
		}
		return usr;
	}

	@Override
	public Usuario encontrarPorUUID(String uuid) {
		Usuario usr=null;
		EntityManager em = EntityFactoryUtil.getEm().createEntityManager();		
		try{			
			String qString = "select u from "+ this.persistentClass.getSimpleName() +" u where u.uuid= :uuid ";
			Query consulta = em.createQuery(qString);
			consulta.setParameter("uuid", uuid);
			List<Usuario> resultado = (List<Usuario>) consulta.getResultList();
			if (resultado.size() > 0){
				usr = (Usuario) resultado.get(0);			
			}
		}catch(HibernateException e){
			e.printStackTrace();			
		}finally{
			em.close();
		}
		return usr;
	}
}
