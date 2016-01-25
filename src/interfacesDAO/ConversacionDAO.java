package interfacesDAO;

import java.util.Collection;

import model.Conversacion;
import model.Usuario;

public interface ConversacionDAO extends GenericDAO<Conversacion>{

	Conversacion encontrarPorViajeEIntegrantes(int viajeId, Usuario user, Usuario encontrarPorId);

}
