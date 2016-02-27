package interfacesDAO;

import java.util.List;

import model.Conversacion;
import model.Usuario;

public interface ConversacionDAO extends GenericDAO<Conversacion>{

	Conversacion encontrarPorViajeEIntegrantes(int viajeId, Usuario user, Usuario encontrarPorId);
	List<Conversacion> listar(Usuario user);
	Conversacion encontrarPorId(int id);

}