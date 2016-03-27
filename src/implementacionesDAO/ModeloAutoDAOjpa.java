package implementacionesDAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.HibernateException;

import interfacesDAO.ModeloAutoDAO;
import model.FrecuenciaViaje;
import model.ModeloAuto;
import model.Viaje;
import util.EntityFactoryUtil;

public class ModeloAutoDAOjpa extends GenericDAOjpa<ModeloAuto> implements ModeloAutoDAO {

	public ModeloAutoDAOjpa(){
		super(ModeloAuto.class);
	}

	public List<ModeloAuto> encontrarPorMarca(String marcaSeleccionada) {
		List<ModeloAuto> listadoModelos = null;		
		EntityManager em = EntityFactoryUtil.getEm().createEntityManager();		
		try{
			String qstr = "select e from "+ this.persistentClass.getSimpleName() +" e where e.marcaAuto.id = :marca";
			Query q = em.createQuery(qstr);
			q.setParameter("marca", Integer.parseInt(marcaSeleccionada));
			listadoModelos = (List<ModeloAuto>) q.getResultList();
			for(ModeloAuto v : listadoModelos){
				v.getNombre();
			}
		}catch(HibernateException e){
			e.printStackTrace();			
		}finally{
			em.close();
		}		
		return listadoModelos;
	}
}