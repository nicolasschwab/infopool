package implementacionesDAO;

import interfacesDAO.HuellaCarbonoDAO;
import model.HuellaCarbono;

public class HuellaCarbonoDAOjpa extends GenericDAOjpa<HuellaCarbono> implements HuellaCarbonoDAO{

	public HuellaCarbonoDAOjpa(){
		super(HuellaCarbono.class);
	}
}