package actionsGeneric;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;

import com.opensymphony.xwork2.ActionContext;

import actions.ConversacionAction;
import implementacionesDAO.FactoryDAO;
import implementacionesDAO.ViajeDAOjpa;
import model.DiasSemana;
import model.EstadoFrecuencia;
import model.FrecuenciaViaje;
import model.TipoViaje;
import model.TramoViaje;
import model.Viaje;
import model.Viajero;
import util.SessionUtil;

public class GenericDatosViajeAction {

	

	
	public Viaje alta(String direccionOrigen, String direccionDestino, String puntosTrayecto, String fechaInicio, String fechaFin, String descripcion, float kilometros, String horaPartida, String horaRegreso, int asientosDisponibles, String tramoViaje, String tipoViaje, String[] diaPeriodico) throws Exception{		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");			
		Date fInicio = null;
		Date fFin = null;
		Time hPartida = null;
		Time hRegreso = null;
		Viaje viaje = new Viaje();
		EstadoFrecuencia estadoFrecuencia = EstadoFrecuencia.ACTIVA;
		ArrayList<FrecuenciaViaje> frecuencias = new ArrayList<FrecuenciaViaje>();
		Viajero conductor = (Viajero) SessionUtil.getUsuario();
		
		if (tramoViaje.equals("IDAVUELTA")){
			hRegreso = Time.valueOf(horaRegreso+":00");
		}
		hPartida = Time.valueOf(horaPartida+":00");
		
		if (tipoViaje.equals("PERIODICO")){				
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);		
			diaPeriodico = request.getParameterValues("diaPeriodico");
			if (diaPeriodico != null && diaPeriodico.length > 0) {
				for (int i = 0; i < diaPeriodico.length; i++) {						
					frecuencias.add(new FrecuenciaViaje(DiasSemana.valueOf(diaPeriodico[i]), estadoFrecuencia, hPartida, hRegreso, asientosDisponibles, viaje, puntosTrayecto, kilometros, TramoViaje.valueOf(tramoViaje)));						
				}
			}
			fFin = sdf.parse(fechaFin);
		}
		else{				
			frecuencias.add(new FrecuenciaViaje(null, estadoFrecuencia, hPartida, hRegreso, asientosDisponibles, viaje, puntosTrayecto, kilometros, TramoViaje.valueOf(tramoViaje)));
		}
		fInicio = sdf.parse(fechaInicio);			
		viaje.setFechaPublicacion(new Date());
		viaje.setDireccionOrigen(direccionOrigen);
		viaje.setDireccionDestino(direccionDestino);
		viaje.setPuntosTrayecto(puntosTrayecto);
		viaje.setFechaInicio(fInicio);
		viaje.setFechaFin(fFin);
		viaje.setDescripcion(descripcion);
		viaje.setKilometros(kilometros);
		viaje.setConductor(conductor);
		viaje.setFrecuencias(frecuencias);			
		viaje.setTipoViaje(TipoViaje.valueOf(tipoViaje));
		viaje.setActivo(true);
		//creo el foro de pasajeros, cuando se acepte una solicitud el ususario debe ser agregado a este foro			
		viaje.setForoViaje(ConversacionAction.crearForo("Foro de pasajeros", viaje, conductor));			
		((ViajeDAOjpa)FactoryDAO.getViajeDAO()).modificar(viaje);
		
		return viaje;
	}
}