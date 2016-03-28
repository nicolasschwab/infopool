package actions;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

import com.opensymphony.xwork2.ActionSupport;

import implementacionesDAO.FactoryDAO;
import model.DiasSemana;
import model.HuellaCarbono;
import model.Viaje;
import model.Viajero;

public class ActualizarHuellaAction  extends ActionSupport implements Job{
	
	private static final long serialVersionUID = 1L;
	
	public String inicializarActualizarHuella(){
		try {
            // Grab the Scheduler instance from the Factory 
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.clear();
            // and start it off
           
            JobDetail job = newJob(ActualizarHuellaAction.class)
            	    .withIdentity("job1", "group1")
            	    .build();

        	CronTrigger trigger = newTrigger()
        	    .withIdentity("trigger1", "group1")
        	    .withSchedule(cronSchedule("0 0 1 * * ?"))
        	    //.withSchedule(cronSchedule("0/5 * * * * ?"))
        	    .build();

        	scheduler.scheduleJob(job, trigger);
        	scheduler.start();
            

        } catch (SchedulerException se) {
            se.printStackTrace();
        }
		return SUCCESS;
	}
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException{
		try{
			//List<Viajero> viajeros=(List) FactoryDAO.getViajeroDAO().listar();
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	        Calendar cal = Calendar.getInstance();
	        cal.add(Calendar.DAY_OF_MONTH,-1);
	        List<Viaje> viajesAyer=FactoryDAO.getViajeDAO().getViajesAyer(DiasSemana.values()[cal.get(Calendar.DAY_OF_WEEK)-2],(dateFormat.format(cal.getTime())+" 00:00:00"));
			//recorro todos los viajes
	        for(Viaje viaje : viajesAyer){
				List<Viajero> pasajeros= (List<Viajero>) viaje.getPasajeros();
				pasajeros.add(viaje.getConductor());
				//recorro todos los pasajeros del viaje
				//incluyendo al conductor
				for(Viajero viajero : pasajeros){
					List<HuellaCarbono> huellas=(List<HuellaCarbono>) viajero.getMiHuellaCarbono();
					HuellaCarbono huella=null;
					if(!huellas.isEmpty()){
						//traer la huella del a�o corriente, si no hay crea una nueva
						for(HuellaCarbono unaHuella: huellas){
							if(unaHuella.esDeEsteAnio()){
								huella=unaHuella;
							}
						}
						if(huella==null){
							huella = new HuellaCarbono();
							huella.setViajeroHuella(viajero);
							huellas.add(huella);
						}
						
					}else{
						huella= new HuellaCarbono();
						huella.setViajeroHuella(viajero);
						huellas.add(huella);
					}
					huella.aumentarCantViajes();
					// LOS KILOMETROS NO DEBERIAN ESTAR EN LAS FRECUENCIAS ? 
					huella.sumarKm(viaje.getKilometros());
					huella.sumarEmision(viaje.getConductor().getAuto().calcularEmision(viaje.getKilometros()));
					FactoryDAO.getViajeroDAO().modificar(viajero);
				}				
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
}
