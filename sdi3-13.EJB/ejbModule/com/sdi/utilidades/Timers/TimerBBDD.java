package com.sdi.utilidades.Timers;

import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.ScheduleExpression;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import com.sdi.business.ApplicationService;
import com.sdi.business.SeatService;
import com.sdi.business.TripService;
import com.sdi.infrastructure.Factories;
import com.sdi.model.Application;
import com.sdi.model.Trip;
import com.sdi.persistence.exception.PersistenceException;

@Singleton
@Startup
public class TimerBBDD {

	@Resource
	private TimerService timerService;

	@PostConstruct
	private void init() {
		ScheduleExpression se = new ScheduleExpression();
        // Set schedule to every 20 seconds (starting at second 0 of every minute).
        se.hour("*").minute("*").second("0/20");
        timerService.createCalendarTimer( se, new TimerConfig("EJB timer service timeout at ", false) );	
        }

	@PreDestroy
    public void stop() {    
        System.out.println("EJB Timer: Stop timers.");
        for (Timer timer : timerService.getTimers()) {
            System.out.println("Stopping timer: " + timer.getInfo());
            timer.cancel();
        }
    }
	@Timeout
	public void execute(Timer timer) {
		System.out.println("Inicio tarea de mantenimiento de BBDD");
		
		System.out.println("Timer Service : " + timer.getInfo());
		System.out.println("Current Time : " + new Date());
		System.out.println("Next Timeout : " + timer.getNextTimeout());

		TripService serviceT = Factories.services.getTripService();
		ApplicationService serviceA = Factories.services
				.getApplicationService();
		SeatService serviceS = Factories.services.getSeatService();

		List<Trip> viajes = serviceT.viajesFechaCierrePasada();

		for (Trip viaje : viajes) {
			
			List<Application> solicitudes = serviceA
					.getSolicitudesViaje(viaje.getId());
			serviceT.cerrarViaje(viaje);

			System.out.println("Se pasarán a 'SIN PLAZA' "
					+ solicitudes.size() + " solicitudes");

			for (Application app : solicitudes) {
				serviceA.delete(app.getUserId(), app.getTripId());

				try {
					serviceS.insertSinPlazas(app.getUserId(),
							app.getTripId());
				} catch (PersistenceException e) {
					System.out
							.println("El usuario ya ha sido insertado en "
									+ "TSeats para ese viaje");
				}
			}
		}

		System.out.println("Fin tarea de mantenimiento de BBDD");
		System.out.println("____________________________________________");
	}
	
}