package interfacesDAO;

import java.util.List;

import model.Conversacion;
import model.TipoConversacion;
import model.Usuario;
import model.Viaje;

public interface ConversacionDAO extends GenericDAO<Conversacion>{

	Conversacion encontrarPorViajeEIntegrantes(int viajeId, Usuario user, Usuario encontrarPorId);
	List<Conversacion> listar(Usuario user);
	Conversacion encontrarPorId(int id);
	Conversacion encontrarPorViaje(Viaje viaje);
	Conversacion encontrarPorIntegrantes(Usuario emisor, Usuario receptor, TipoConversacion tipoConversacion);
}