package implementacionesDAO;

import interfacesDAO.ViajeDAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Evento;
import model.ForoMensajes;
import model.SolicitudViaje;
import model.Viaje;
import model.Viajero;

import org.hibernate.HibernateException;

import util.EntityFactoryUtil;

public class ViajeDAOjpa extends GenericDAOjpa<Viaje> implements ViajeDAO {

	public ViajeDAOjpa(){
		super(Viaje.class);
	}
	
	public <T> List<Viaje> buscarPorDireccion(T dirOrigen, T dirDestino){
		String qString = "select e from "+ this.persistentClass.getSimpleName() +" e where e.direccionOrigen like :origen and e.direccionDestino like :destino";
		List<Viaje> resultado=null;
		EntityManager em = EntityFactoryUtil.getEm().createEntityManager();		
		try{			
			Query consulta = em.createQuery(qString);
			consulta.setParameter("origen", "%" + dirOrigen + "%");
			consulta.setParameter("destino", "%" + dirDestino + "%");
			resultado = (List<Viaje>) consulta.getResultList();			
		}catch(HibernateException e){
			e.printStackTrace();			
		}finally{
			em.close();
		}
		return resultado;		
	}
		
	public <T> List<Viaje> buscarPorFecha(T id){
		String qString = "select e from "+ this.persistentClass.getSimpleName() +" e where e.fechaInicio = :id ";
		return listadoGenerico(qString,id);
	}
	
	public <T> List<Viaje> listarViajesConductor(T id){
		String qString = "select e from "+ this.persistentClass.getSimpleName() +" e where e.conductor = :id ";
		return listadoGenerico(qString,id);
	}
	
	public <T> List<Viaje> listarViajesPasajero(T id){
		String qString = "select e from "+ this.persistentClass.getSimpleName() +" e  where :id in elements(e.pasajeros)  ";
		return listadoGenerico(qString,id);
	}
	
	public <T> List<Viaje> listarViajesNoAsociados(T id,Evento evento){
		String qString = "select e from "+ this.persistentClass.getSimpleName() +" e  where :id not in elements(e.pasajeros) and e.conductor != :id and e.evento = :evento";
		return listadoGenerico2(qString,id,evento);
	}
	
	private <T> List<Viaje> listadoGenerico(String laConsulta,T id){
		List<Viaje> resultado=null;
		EntityManager em = EntityFactoryUtil.getEm().createEntityManager();		
		try{
			String qString = laConsulta;
			Query consulta = em.createQuery(qString);
			consulta.setParameter("id", id);
			resultado = (List<Viaje>) consulta.getResultList();			
		}catch(HibernateException e){
			e.printStackTrace();			
		}finally{
			em.close();
		}
		return resultado;
	}
	
	private <T> List<Viaje> listadoGenerico2(String laConsulta,T id,T evento){
		List<Viaje> resultado=null;
		EntityManager em = EntityFactoryUtil.getEm().createEntityManager();		
		try{
			String qString = laConsulta;
			Query consulta = em.createQuery(qString);
			consulta.setParameter("id", id);
			consulta.setParameter("evento", evento);
			resultado = (List<Viaje>) consulta.getResultList();			
		}catch(HibernateException e){
			e.printStackTrace();			
		}finally{
			em.close();
		}
		return resultado;
	}
	
	public Viaje encontrar(Integer id){
		Viaje resultado = null;
		EntityManager em = EntityFactoryUtil.getEm().createEntityManager();		
		try{			
			resultado = (Viaje) em.find(this.persistentClass, id);			
			for (Viajero pasajero : resultado.getPasajeros()){
				pasajero.getPerfil();
			}
			for (SolicitudViaje solicitud : resultado.getSolicitudes()) {
				solicitud.getEstado();
			}
			if(resultado.getDiasSemana().size() > 0){
				resultado.misDias();
			}
			for (ForoMensajes mensaje : resultado.getMensajes()) {
				mensaje.getFechaPublicacion();
			}
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			em.close();
		}
		return resultado;
	}
	
	
}
