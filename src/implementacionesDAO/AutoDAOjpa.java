package implementacionesDAO;

import interfacesDAO.AutoDAO;
import model.Auto;

public class AutoDAOjpa extends GenericDAOjpa<Auto> implements AutoDAO {

	public AutoDAOjpa() {
		super(Auto.class);
	}

}
