package actionsGeneric;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.util.SystemPropertyUtils;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.grum.geocalc.Coordinate;
import com.grum.geocalc.DegreeCoordinate;
import com.grum.geocalc.EarthCalc;
import com.grum.geocalc.Point;

import implementacionesDAO.FactoryDAO;
import interfacesDAO.ViajeDAO;
import model.Viaje;
import model.Viajero;
import util.SessionUtil;
import util.Validacion;

public class GenericViajeAction {

private ViajeDAO viajeDAO;
	
	private final static GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyC4ONLIO2speSB3enNoqAvrzgWePwLGee0");
	private Point puntoOrigen;
	private Point puntoDestino;
	
	public ViajeDAO getViajeDAO() {
		if(viajeDAO==null){
			viajeDAO=FactoryDAO.getViajeDAO();
		}
		return viajeDAO;
	}

	public void setViajeDAO(ViajeDAO viajeDAO) {
		this.viajeDAO = viajeDAO;
	}


	public List<Viaje> busquedaViaje() throws Exception {		
		return this.busquedaViaje(null,null, null,"100");		
	}
	
	public List<Viaje> busquedaViaje(String dirOrigen, String dirDestino, String fecha, String rango) throws Exception{
			this.crearPuntoOrigen(dirOrigen);
			this.crearPuntoDestino(dirDestino);
			List<Viaje> viajesRetornar= new ArrayList<Viaje>();
			if(!Validacion.stringNoVacio(fecha)){
				fecha = "2016-01-01";
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date fechaViaje = sdf.parse(fecha);
			List<Viaje> viajes=this.getViajeDAO().obtenerViajesBusquedaParametrizada("", "", fechaViaje);
			for(Viaje viaje : viajes){
				if(this.validarViajeRango(viaje,rango)){
					viajesRetornar.add(viaje);
				}
			}
			return viajesRetornar;
	}
	
	private void crearPuntoOrigen(String origen){
		this.puntoOrigen=crearPunto(origen);
	}
	private void crearPuntoDestino(String destino){
		this.puntoDestino=crearPunto(destino);
	}
	private Point crearPunto(String dir){
		try{
			LatLng result =  GeocodingApi.geocode(context,dir).await()[0].geometry.location;
			Coordinate lat=new DegreeCoordinate(result.lat);
			Coordinate lng=new DegreeCoordinate(result.lng);
			return new Point(lat,lng);
		}catch (Exception e){
			System.out.println(e.getMessage());
			System.out.println(e);
		}
		return null;
	}
	private boolean validarViajeRango(Viaje viaje, String rango){
		if(this.validarOrigenRango(viaje.getDireccionOrigen(),rango) && this.validarDestinoRango(viaje.getDireccionDestino(),rango)){
			return true;
		}
		return false;
	}
	private boolean validarOrigenRango(String origen,String rango){
		if(this.puntoOrigen==null){
			return true;
		}
		if(this.validarPuntoRango(this.crearPunto(origen),this.puntoOrigen,rango)){
			return true;
		}
		return false;
	}
	private boolean validarDestinoRango(String destino,String rango){
		if(this.puntoDestino==null){
			return true;
		}
		if(this.validarPuntoRango(this.crearPunto(destino),this.puntoDestino,rango)){
			return true;
		}
		return false;
	}
	private boolean validarPuntoRango(Point direccion, Point direccion2,String rango){
		if( (EarthCalc.getHarvesineDistance(direccion, direccion2)) <= Double.parseDouble(rango) ){
			return true;
		}
		return false;
	}
	public Viaje detalleViaje(String id){
		return this.getViajeDAO().encontrarPorId(Integer.parseInt(id));
	}
	
	public List<Viaje> viajesUsuarioConductor(){
		Viajero viajero = (Viajero)SessionUtil.getUsuario();			
		return FactoryDAO.getViajeDAO().obtenerViajesConductorEstado(viajero, true);		
	}
	
	public List<Viaje> viajesUsuarioViajero(){
		Viajero viajero = (Viajero)SessionUtil.getUsuario();
		return FactoryDAO.getViajeDAO().obtenerViajesPasajeroEstado(viajero, true);
	}
	
	
	
}
