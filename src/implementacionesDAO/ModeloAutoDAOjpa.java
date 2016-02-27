package implementacionesDAO;

import interfacesDAO.ModeloAutoDAO;
import model.ModeloAuto;

public class ModeloAutoDAOjpa extends GenericDAOjpa<ModeloAuto> implements ModeloAutoDAO {

	public ModeloAutoDAOjpa(){
		super(ModeloAuto.class);
	}
}