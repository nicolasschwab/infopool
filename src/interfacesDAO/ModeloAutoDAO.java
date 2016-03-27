package interfacesDAO;

import java.util.List;

import model.ModeloAuto;

public interface ModeloAutoDAO extends GenericDAO<ModeloAuto> {

	List<ModeloAuto> encontrarPorMarca(String marcaSeleccionada);

}
