package implementacionesDAO;

import interfacesDAO.ForoMensajesDAO;
import model.ForoMensajes;

public class ForoMensajesDAOjpa extends GenericDAOjpa<ForoMensajes> implements ForoMensajesDAO {

	public ForoMensajesDAOjpa(){
		super(ForoMensajes.class);
	}

}
