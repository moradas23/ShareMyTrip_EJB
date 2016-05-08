package com.sdi.presentation;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import com.sdi.business.ApplicationService;
import com.sdi.business.SeatService;
import com.sdi.business.TripService;
import com.sdi.business.UserService;
import com.sdi.infrastructure.Factories;
import com.sdi.model.Application;
import com.sdi.model.ImplicacionStatus;
import com.sdi.model.Seat;
import com.sdi.model.SeatStatus;
import com.sdi.model.Trip;
import com.sdi.model.TripImplicacion;
import com.sdi.model.User;
import com.sdi.model.UserLogin;

@ManagedBean(name = "trips")
@SessionScoped
public class BeanTrips implements Serializable {
	private static final long serialVersionUID = 55556L;

	private List<Trip> viajes;

	// Usado para los viaje seleccionados
	private Trip viaje;
	private User promotorViaje;
	private List<User> participantes;

	// Viajes en los que tiene implicación el usuario
	private List<TripImplicacion> viajesImplicado;

	public List<User> getParticipantes() {
		return participantes;
	}

	public void setParticipantes(List<User> participantes) {
		this.participantes = participantes;
	}

	public void setViaje(Trip viaje) {
		this.viaje = viaje;

	}

	public Trip getViaje() {
		return viaje;
	}

	public User getPromotorViaje() {
		return promotorViaje;
	}

	public void setPromotorViaje(User promotorViaje) {
		this.promotorViaje = promotorViaje;
	}

	public String mostrarDatosViaje(Trip viaje) {
		if (userIsNotLoggedIn()) {
			return "fracaso";
		}

		this.viaje = viaje;
		cargarParticipantesViaje(viaje);
		cargarPromotor(viaje.getPromoterId());
		return "exito";

	}

	private void cargarPromotor(Long promoterId) {
		UserService service;

		service = Factories.services.getUserService();
		promotorViaje = service.findById(promoterId);

	}

	private void cargarParticipantesViaje(Trip viaje) {
		SeatService serviceS;
		UserService serviceU;

		serviceS = Factories.services.getSeatService();
		serviceU = Factories.services.getUserService();
		List<Seat> plazasAceptadas = serviceS
				.findPlazasAceptadas(viaje.getId());

		participantes = new LinkedList<User>();

		for (Seat plaza : plazasAceptadas) {

			User usuario = serviceU.findById(plaza.getUserId());
			participantes.add(usuario);

		}
	}

	public List<Trip> getViajes() {
		return viajes;
	}

	public void setViajes(List<Trip> viajes) {
		this.viajes = viajes;
	}

	/**
	 * Se buscan los viajes en los que el usuario puede participar. Esto depende
	 * de si es un usuario público o registrado.
	 * 
	 * @return
	 */
	public String obtenerViajesDisponibles() {
		TripService service = Factories.services.getTripService();

		if (userIsNotLoggedIn()) {
			viajes = service.findAllDisponible();
			return "listadoPublico";
		}

		UserLogin usuario = (UserLogin) getObjectFromSession("LOGGEDIN_USER");
		viajes = service.findAllDisponibleUser(usuario.getLogin());
		return "listadoRegistrado";
	}

	public void apuntarse() {
		ApplicationService app;
		app = Factories.services.getApplicationService();
		UserLogin usuario = (UserLogin) getObjectFromSession("LOGGEDIN_USER");
		app.save(usuario.getLogin(), viaje.getId());

		FacesContext context = FacesContext.getCurrentInstance();

		ResourceBundle bundle = context.getApplication().getResourceBundle(
				context, "msgs");

		context.addMessage(null, new FacesMessage(bundle.getString("Exito"),
				bundle.getString("ApuntadoTexto")));

	}

	private boolean userIsNotLoggedIn() {
		UserLogin usuariologueado = (UserLogin) getObjectFromSession("LOGGEDIN_USER");
		if (usuariologueado != null) {

			System.out.println("usuario activo: " + usuariologueado.getName());
			return false;
		}

		return true;
	}

	private Object getObjectFromSession(String key) {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap().get(key);

	}

	public List<TripImplicacion> getViajesImplicado() {
		return viajesImplicado;
	}

	public void setViajesImplicado(List<TripImplicacion> viajesImplicado) {
		this.viajesImplicado = viajesImplicado;
	}

	public void obtenerViajesImplicado() {

		UserLogin usuario = (UserLogin) getObjectFromSession("LOGGEDIN_USER");

		viajesImplicado = new LinkedList<TripImplicacion>();

		obtenerViajesPromotor(usuario);
		obtenerViajesAceptadoExcluidoSinPlaza(usuario);
		obtenerViajesPendiente(usuario);

	}

	private void obtenerViajesPendiente(UserLogin usuario) {
		ApplicationService serviceA = Factories.services
				.getApplicationService();
		TripService serviceT = Factories.services.getTripService();

		List<Application> solicitudes = serviceA.getSolicitudes(usuario
				.getLogin());

		for (Application app : solicitudes) {
			TripImplicacion viaje = new TripImplicacion(serviceT.findByIdTrip(app
					.getTripId()));
			viaje.setImplicacion(ImplicacionStatus.PENDIENTE);
			viajesImplicado.add(viaje);
		}
	}

	private void obtenerViajesAceptadoExcluidoSinPlaza(UserLogin usuario) {

		SeatService serviceS = Factories.services.getSeatService();
		TripService serviceT = Factories.services.getTripService();
		List<Seat> seats = serviceS.findByUser(usuario.getId());

		List<Long> idsViajesEsPromotor = new LinkedList<Long>();
		
		for(TripImplicacion viajeAux:viajesImplicado){
			idsViajesEsPromotor.add(viajeAux.getId());
		}
		
		for (Seat seat : seats) {
			
				TripImplicacion viaje = new TripImplicacion(
						serviceT.findByIdTrip(seat.getTripId()));
				
				if(seat.getStatus().equals(SeatStatus.EXCLUDED)){
					viaje.setImplicacion(ImplicacionStatus.EXCLUIDO);
					viajesImplicado.add(viaje);
				}
				else if(seat.getStatus().equals(SeatStatus.SIN_PLAZA)){
					viaje.setImplicacion(ImplicacionStatus.SIN_PLAZA);
					viajesImplicado.add(viaje);
				}
				
				else{
					//Esta comprobación es debida a que los promotores están 'aceptados'
					//en su propio viaje ya que ocupan una plaza. Por ello no nos 
					//interesa mostrar al promotor que está aceptado en su propio viaje 	
					if(!idsViajesEsPromotor.contains(viaje.getId())){
						viaje.setImplicacion(ImplicacionStatus.ACEPTADO);
						viajesImplicado.add(viaje); 
					}
						
				}
								
			}

	}

	private void obtenerViajesPromotor(UserLogin usuario) {
		TripService service = Factories.services.getTripService();
		List<Trip> viajes = service.findByPromoter(usuario.getId());

		for (Trip viaje : viajes) {
				TripImplicacion viajeIm = new TripImplicacion(viaje);
				viajeIm.setImplicacion(ImplicacionStatus.PROMOTOR);
				viajesImplicado.add(viajeIm);
		
		}
	}

	
	public void cancelarParticipacion(TripImplicacion viaje){
		
		UserLogin usuario = (UserLogin) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("LOGGEDIN_USER");
		
		if(viaje.getImplicacion().equals(ImplicacionStatus.ACEPTADO)){
			SeatService serviceS = Factories.services.getSeatService();
			TripService serviceT = Factories.services.getTripService();
			serviceS.delete(usuario.getId(),viaje.getId());	
			serviceT.liberarPlaza(viaje.getId());
		}
		
		else{
			ApplicationService serviceA = Factories.services.getApplicationService();
			serviceA.delete(usuario.getId(), viaje.getId());
			
			
		}
		
		viajesImplicado.remove(viaje);
		
		FacesContext context = FacesContext.getCurrentInstance();

		ResourceBundle bundle = context.getApplication().getResourceBundle(
				context, "msgs");

		context.addMessage(null, new FacesMessage(bundle.getString("Exito"),
				bundle.getString("CancelarParticipacion")));
	}
	
	
	public void cancelarViajes(Map<Long,Trip> trips){
		for(Long key:trips.keySet()){
			Trip trip = trips.get(key);
			if(viajesImplicado.contains(trip)){
				viajesImplicado.remove(trip);
			}
		}
	}
	

}
