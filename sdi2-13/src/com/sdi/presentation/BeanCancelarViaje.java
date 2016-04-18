package com.sdi.presentation;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.sdi.business.TripsService;
import com.sdi.infrastructure.Factories;
import com.sdi.model.Trip;

@ManagedBean(name = "cancelTrip")
@SessionScoped
public class BeanCancelarViaje implements Serializable {
	private static final long serialVersionUID = 55556L;

	private Map<Long,Trip> viajesCancelar = new  HashMap<Long, Trip>();
	
	
	public void addTrip(Trip viaje){
		if(viajesCancelar.containsKey(viaje.getId()))
			viajesCancelar.remove(viaje.getId());
		else
			viajesCancelar.put(viaje.getId(), viaje);
	}
	
	public String cancelarViajes(){
		if(!viajesCancelar.isEmpty()){
			TripsService trip = Factories.services.createTripService();
			trip.cancelarViajes(viajesCancelar);
			
			FacesContext context = FacesContext.getCurrentInstance();
			
	        ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msgs");
			
	        context.addMessage(null, new FacesMessage(bundle.getString("Exito")
	        		,bundle.getString("viajeCancelado")));
	        
	    	return "cancelTrip";
		}
		return "cancelFail";
	}

	public Map<Long, Trip> getViajesCancelar() {
		return viajesCancelar;
	}

	public void setViajesCancelar(Map<Long, Trip> viajesCancelar) {
		this.viajesCancelar = viajesCancelar;
	}
	
	
}