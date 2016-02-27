package interfacesDAO;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO<T> {
	
	public <T> void registrar(T objectT) throws Exception;
	public <T> void eliminar(T objectT) throws Exception;
	public <T> void modificar(T objectT) throws Exception;
	public <T> List<T> listar() throws Exception;
	public <T> T encontrar(Serializable object) throws Exception;

}