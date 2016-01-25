package implementacionesDAO;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.HibernateException;

import interfacesDAO.ConversacionDAO;
import model.Conversacion;
import model.Mensaje;
import model.Usuario;
import model.Viajero;
import util.EntityFactoryUtil;

public class ConversacionDAOjpa extends GenericDAOjpa<Conversacion> implements ConversacionDAO {

	public ConversacionDAOjpa() {
		super(Conversacion.class);
	}

	@Override
	public Conversacion encontrarPorViajeEIntegrantes(int viajeId, Usuario emisor, Usuario receptor) {
		Conversacion conver=null;
		EntityManager em = EntityFactoryUtil.getEm().createEntityManager();		
		try{			
			String qString = "select u from "+ this.persistentClass.getSimpleName() +" u where :emisor IN elements(u.participantes) and :receptor IN elements(u.participantes) and u.viaje.id= :viajeId ";
			Query consulta = em.createQuery(qString);
			consulta.setParameter("emisor",emisor);
			consulta.setParameter("receptor",receptor);
			consulta.setParameter("viajeId", viajeId);
			List<Conversacion> resultado = (List<Conversacion>) consulta.getResultList();
			if (resultado.size() > 0){
				conver = (Conversacion) resultado.get(0);
				conver.getMensajes();
				for(Mensaje men : conver.getMensajes()){
					men.getDetalle();
				}
				for(Viajero usr : conver.getParticipantes()){
					usr.getNombre();
				}
			}
		}catch(HibernateException e){
			e.printStackTrace();			
		}finally{
			em.close();
		}
		return conver;
	}

	public List<Conversacion> listar(Usuario user) {		
		List<Conversacion> resultado = null;
		EntityManager em = EntityFactoryUtil.getEm().createEntityManager();		
		try{			
			Query consulta = em.createQuery("select e from " + persistentClass.getSimpleName() + " e where :user in elements(e.participantes) ORDER BY fechaUltimaModificacion DESC");		
			consulta.setParameter("user",  user );
			resultado = (List<Conversacion>) consulta.getResultList();
			if (resultado.size() > 0){
				for(Conversacion conver :resultado){
					for(Mensaje men : conver.getMensajes()){
						men.getDetalle();
					}
					for(Viajero usr : conver.getParticipantes()){
						usr.getNombre();
					}
				}
				
			}
		}catch(HibernateException e){
			e.printStackTrace();			
		}finally{
			em.close();
		}
		return resultado;
		
	}
	public Conversacion encontrarPorId(int id){
		Conversacion conver=null;
		EntityManager em = EntityFactoryUtil.getEm().createEntityManager();		
		try{			
			String qString = "select u from "+ this.persistentClass.getSimpleName() +" u where :id = u.id";
			Query consulta = em.createQuery(qString);
			consulta.setParameter("id",id);
			List<Conversacion> resultado = (List<Conversacion>) consulta.getResultList();
			if (resultado.size() > 0){
				conver = (Conversacion) resultado.get(0);
				conver.getMensajes();
				for(Mensaje men : conver.getMensajes()){
					men.getDetalle();
				}
				for(Viajero usr : conver.getParticipantes()){
					usr.getNombre();
				}
				conver.getViaje().getDireccionDestino();
				for(Viajero viajero :conver.getParticipantes()){
					viajero.getNombre();
					viajero.calificacionActual();
				}
			}
		}catch(HibernateException e){
			e.printStackTrace();			
		}finally{
			em.close();
		}
		return conver;
	}
	

}

