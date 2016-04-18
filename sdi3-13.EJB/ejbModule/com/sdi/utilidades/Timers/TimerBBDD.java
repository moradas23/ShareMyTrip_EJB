package com.sdi.utilidades.Timers;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.sdi.business.ApplicationService;
import com.sdi.business.SeatService;
import com.sdi.business.TripsService;
import com.sdi.infrastructure.Factories;
import com.sdi.model.Application;
import com.sdi.model.Trip;
import com.sdi.persistence.exception.PersistenceException;

public class TimerBBDD {

	public static void mantenimientoBBDD() {
		Timer timer;
		timer = new Timer();

		TimerTask task = new TimerTask() {

			@Override
			public void run() {

				TripsService serviceT = Factories.services.createTripService();
				ApplicationService serviceA = Factories.services
						.createApplicationService();
				SeatService serviceS = Factories.services.createSeatService();

				List<Trip> viajes = serviceT.viajesFechaCierrePasada();

				for (Trip viaje : viajes) {
					System.out.println("Inicio tarea de mantenimiento de BBDD");
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

					System.out.println("Fin tarea de mantenimiento de BBDD");

				}

			}
		};
		// Empieza en 0ms y se lanza la tarea cada 2000ms
		timer.schedule(task, 0, 2000);
	}
}