package interfacesDAO;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO<T> {
	public <T> void registrar(T objetoT);
	public <T> void eliminar(T objetoT);
	public <T> void modificar(T objetoT);
	public <T> List<T> listar();
	public <T> T encontrar(Serializable objeto);

}
