package implementacionesDAO;

import interfacesDAO.DenunciaDAO;
import model.Denuncia;

import org.springframework.stereotype.Component;

@Component
public class DenunciaDAOjpa extends GenericDAOjpa<Denuncia> implements DenunciaDAO {

	public DenunciaDAOjpa(){
		super(Denuncia.class);
	}
}
