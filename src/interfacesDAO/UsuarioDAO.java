package interfacesDAO;

import model.Usuario;

public interface UsuarioDAO extends GenericDAO<Usuario> {
	
	public Usuario existe(String usuario, String Clave);
	public Usuario encontrarPorId(String participante);
	public Usuario existeNombreUsuario(String usuario);
	
}