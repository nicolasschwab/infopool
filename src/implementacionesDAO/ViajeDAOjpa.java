package implementacionesDAO;

import interfacesDAO.ViajeDAO;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import model.Conversacion;
import model.DiasSemana;
import model.Evento;
import model.FrecuenciaViaje;
import model.HuellaCarbono;
import model.Mensaje;
import model.SolicitudViaje;
import model.Usuario;
import model.Viaje;
import model.Viajero;

import org.hibernate.HibernateException;

import dto.SolicitudViajeDto;
import util.Dozer;
import util.EntityFactoryUtil;

public class ViajeDAOjpa extends GenericDAOjpa<Viaje> implements ViajeDAO {

	public ViajeDAOjpa(){
		super(Viaje.class);
	}
		
	public <T> void registrar(Viaje viaje) {
		EntityManager em = EntityFactoryUtil.getEm().createEntityManager();
		EntityTransaction etx = em.getTransaction();
		try{			
			etx.begin();			
			em.persist(viaje);
			for (FrecuenciaViaje f : viaje.getFrecuencias()){				
				em.persist(f);				
			}			
			em.flush();
			etx.commit();
		}catch(PersistenceException e){
			System.out.println("Error al registrar el objeto");
			etx.rollback();
		}finally{
			em.close();
		}
	}
	
	public <T> List<Viaje> obtenerViajesConductorEstado(T conductor, boolean estadoActivo){		
		List<Viaje> listadoViajes = null;		
		EntityManager em = EntityFactoryUtil.getEm().createEntityManager();		
		try{
			String qstr = "select e from "+ this.persistentClass.getSimpleName() +" e where e.conductor = :conductor and e.activo = :estado";
			Query q = em.createQuery(qstr);
			q.setParameter("conductor", conductor);			
			q.setParameter("estado", estadoActivo);
			listadoViajes = (List<Viaje>) q.getResultList();
			for(Viaje v : listadoViajes){
				for(FrecuenciaViaje f : v.getFrecuencias()){
					f.getPasajeros();
				}
			}
		}catch(HibernateException e){
			e.printStackTrace();			
		}finally{
			em.close();
		}		
		return listadoViajes;
	}
	
	public <T> List<Viaje> obtenerViajesPasajeroEstado(T pasajero, boolean estadoActivo){		
		List<Viaje> listadoViajes = null;		
		EntityManager em = EntityFactoryUtil.getEm().createEntityManager();		
		try{
			String qstr = "select e from "+ this.persistentClass.getSimpleName() +" e where :pasajero in elements(e.pasajeros) and e.activo = :estado";
			Query q = em.createQuery(qstr);
			q.setParameter("pasajero", pasajero);			
			q.setParameter("estado", estadoActivo);
			listadoViajes = (List<Viaje>) q.getResultList();
			for(Viaje v : listadoViajes){
				for(FrecuenciaViaje f : v.getFrecuencias()){
					f.getPasajeros();
				}
			}
		}catch(HibernateException e){
			e.printStackTrace();			
		}finally{
			em.close();
		}		
		return listadoViajes;
	}
	
	public <T> List<Viaje> obtenerHistorialViajesEstado(T viajero){
		List<Viaje> listadoViajes = null;		
		EntityManager em = EntityFactoryUtil.getEm().createEntityManager();		
		try{
			String qstr = "select e from "+ this.persistentClass.getSimpleName() +" e where (:viajero in elements(e.pasajeros) or e.conductor = :viajero) and e.activo = 0";
			Query q = em.createQuery(qstr);
			q.setParameter("viajero", viajero);
			listadoViajes = (List<Viaje>) q.getResultList();	
			for(Viaje v : listadoViajes){
				for(FrecuenciaViaje f : v.getFrecuencias()){
					f.getPasajeros();
				}
			}
		}catch(HibernateException e){
			e.printStackTrace();			
		}finally{
			em.close();
		}		
		return listadoViajes;
	}
	
	public <T> List<Viaje> obtenerUltimosViajesBusqueda(){
		List<Viaje> listadoViajes = null;		
		EntityManager em = EntityFactoryUtil.getEm().createEntityManager();		
		try{
			String qstr = "select e from "+ this.persistentClass.getSimpleName() +" e where e.activo = 1";
			Query q = em.createQuery(qstr);
			q.setFirstResult(0);
			q.setMaxResults(10);
			listadoViajes = (List<Viaje>) q.getResultList();			
			for(Viaje v : listadoViajes){				
				for(FrecuenciaViaje f : v.getFrecuencias()){					
					f.getPasajeros();
				}
			}
		}catch(HibernateException e){
			e.printStackTrace();			
		}finally{
			em.close();
		}		
		return listadoViajes;
	}
	
	public Viaje encontrarPorId(int id){
		Viaje viaje = null;
		EntityManager em = EntityFactoryUtil.getEm().createEntityManager();		
		try{			
			viaje = (Viaje) em.find(this.persistentClass, id);			
			for (Viajero pasajero : viaje.obtenerPasajeros()){				
				pasajero.getId();				
			}
			for(Viajero usr: viaje.getForoViaje().getParticipantesConversacion()){
				usr.getApellido();
			}
			for(Mensaje men: viaje.getForoViaje().getMensajes()){
				men.getDetalle();
			}
			for(FrecuenciaViaje f : viaje.getFrecuencias()){
				for(Usuario pasajero: f.getPasajeros()){
					pasajero.getId();
				}
				for (SolicitudViaje solicitud : f.getSolicitudesViaje()) {
					solicitud.getEstadoSolicitud();
				}
			}
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			em.close();
		}
		return viaje;
	}
		
	public <T> List<Viaje> obtenerViajesBusquedaParametrizada(T dirOrigen, T dirDestino, T fechaViaje){
		List<Viaje> listadoViajes = null;		
		EntityManager em = EntityFactoryUtil.getEm().createEntityManager();		
		try{
			String qstr = "select e from "+ this.persistentClass.getSimpleName() +" e where e.direccionOrigen like :origen and e.direccionDestino like :destino and DATE(e.fechaInicio) >= DATE(:fecha) and e.activo = 1";
			Query q = em.createQuery(qstr);
			q.setParameter("origen", "%" + dirOrigen + "%");
			q.setParameter("destino", "%" + dirDestino + "%");
			q.setParameter("fecha", fechaViaje);
			listadoViajes = (List<Viaje>) q.getResultList();			
			for(Viaje v : listadoViajes){
				for(FrecuenciaViaje f : v.getFrecuencias()){					
					f.getPasajeros();
				}
			}
		}catch(HibernateException e){
			e.printStackTrace();			
		}finally{
			em.close();
		}		
		return listadoViajes;
	}
	
	/* --- METODOS PARA REVISAR --- */
	public <T> List<Viaje> listarViajesConductor(T id){
		String qString = "select e from "+ this.persistentClass.getSimpleName() +" e where e.conductor = :id ";
		return listadoGenerico(qString,id);
	}	
	
	public <T> List<Viaje> listarViajesPasajero(T id){
		String qString = "select e from "+ this.persistentClass.getSimpleName() +" e  where :id in elements(e.pasajeros) and e.activo = 1";
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
			for(Viaje viaje: resultado){
				for(FrecuenciaViaje frecuencia: viaje.getFrecuencias()){
					for(SolicitudViaje solicitud: frecuencia.getSolicitudesViaje()){
						solicitud.getFechaInicioSolicitud();
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
	
	//Obtengo los ultimos 10 viajes creados
	public <T> List<Viaje> obtenerUltimosViajes() {
		List<Viaje> listadoViajes = null;		
		EntityManager em = EntityFactoryUtil.getEm().createEntityManager();		
		try{
			String qstr = "select e from "+ this.persistentClass.getSimpleName() +" e where e.activo = true ORDER BY e.fechaPublicacion ";
			Query q = em.createQuery(qstr);
			q.setMaxResults(10);
			listadoViajes = (List<Viaje>) q.getResultList();
			for(Viaje v : listadoViajes){
				for(FrecuenciaViaje f : v.getFrecuencias()){
					f.getPasajeros();
				}
			}
		}catch(HibernateException e){
			e.printStackTrace();			
		}finally{
			em.close();
		}		
		return listadoViajes;
	}

	@Override
	public <T> List<Viaje> getViajesAyer(DiasSemana diaAyer,String ayer) {
		
		List<Viaje> listadoViajes = null;		
		EntityManager em = EntityFactoryUtil.getEm().createEntityManager();		
		try{
			String qstr = "select e from "+ this.persistentClass.getSimpleName() +" e "
					+ "where e.activo = true and ((e.fechaFin!=null"
					+ " and (DATE(:ayer) between e.fechaInicio AND e.fechaFin)"
					+ " and (select fv from FrecuenciaViaje fv where fv.diaFrecuencia = :dia) in elements(e.frecuencias)))"
					+ " or ( DATE(:ayer)= e.fechaInicio))  ORDER BY e.fechaPublicacion ";
			Query q = em.createQuery(qstr);
			q.setParameter("ayer",ayer);
			q.setParameter("dia",diaAyer);
			listadoViajes = (List<Viaje>) q.getResultList();
			for(Viaje v : listadoViajes){
				for(Viajero viajero:v.getPasajeros()){
					for(HuellaCarbono huella:viajero.getMiHuellaCarbono()){
						huella.getEmisionesAcumuladas();
					}
				}
				for(HuellaCarbono huella:v.getConductor().getMiHuellaCarbono()){
					huella.getEmisionesAcumuladas();
				}
				v.getKilometros();
				v.getConductor().getAuto().getModelo().getEmisionGases();
				for(FrecuenciaViaje f : v.getFrecuencias()){
					f.getPasajeros();
				}
			}
		}catch(HibernateException e){
			e.printStackTrace();			
		}finally{
			em.close();
		}		
		return listadoViajes;
	}
	
	
	
	
}
