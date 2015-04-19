package interfacesDAO;

import model.Denuncia;

public interface DenunciaDAO extends GenericDAO<Denuncia>{	
	public void registrar(Denuncia d);
}
