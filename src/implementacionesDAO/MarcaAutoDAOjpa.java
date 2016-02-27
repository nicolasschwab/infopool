package implementacionesDAO;

import interfacesDAO.MarcaAutoDAO;
import model.MarcaAuto;

public class MarcaAutoDAOjpa extends GenericDAOjpa<MarcaAuto> implements MarcaAutoDAO {

	public MarcaAutoDAOjpa() {
		super(MarcaAuto.class);
	}

}