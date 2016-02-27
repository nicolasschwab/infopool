package implementacionesDAO;

import interfacesDAO.AdministradorDAO;
import model.Administrador;

public class AdministradorDAOjpa extends GenericDAOjpa<Administrador> implements AdministradorDAO {
	
	public AdministradorDAOjpa(){
		super(Administrador.class);
	}

}
