package com.sdi.presentation;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import com.sdi.business.TripsService;
import com.sdi.infrastructure.Factories;

@ManagedBean(name = "registerTrip")
@SessionScoped
public class BeanRegisterTrip implements Serializable {
	private static final long serialVersionUID = 6L;
	private String adressFrom = "";
	private String cityFrom = "";
	private String provinceFrom = "";
	private String countryFrom = "";
	private String postalCodeFrom = "";

	private String adressTo = "";
	private String cityTo = "";
	private String provinceTo = "";
	private String countryTo = "";
	private String postalCodeTo = "";

	private Date dateFrom;
	private Date dateTo;
	private Date dateLimit;

	public void onDateSelect(SelectEvent event) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		facesContext.addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected",
						format.format(event.getObject())));
	}

	public void click() {
		RequestContext requestContext = RequestContext.getCurrentInstance();

		requestContext.update("form:display");
		requestContext.execute("PF('dlg').show()");
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public Date getDateLimit() {
		return dateLimit;
	}

	public void setDateLimit(Date dateLimit) {
		this.dateLimit = dateLimit;
	}

	private String priceTrip = "";
	private String commentTrip = "";
	private String maxSeats = "";
	private String availableSeats = "";

	private String latZipCodeFrom = "";
	private String longZipCodeFrom = "";
	private String latZipCodeTo = "";
	private String longZipCodeTo = "";

	private String result = "registerTrip_form_result_valid";

	public BeanRegisterTrip() {
		System.out.println("BeanRegisterTrip - No existia");
	}

	public String getAdressFrom() {
		return adressFrom;
	}

	public void setAdressFrom(String adressFrom) {
		this.adressFrom = adressFrom;
	}

	public String getCityFrom() {
		return cityFrom;
	}

	public void setCityFrom(String cityFrom) {
		this.cityFrom = cityFrom;
	}

	public String getProvinceFrom() {
		return provinceFrom;
	}

	public void setProvinceFrom(String provinceFrom) {
		this.provinceFrom = provinceFrom;
	}

	public String getCountryFrom() {
		return countryFrom;
	}

	public void setCountryFrom(String countryFrom) {
		this.countryFrom = countryFrom;
	}

	public String getPostalCodeFrom() {
		return postalCodeFrom;
	}

	public void setPostalCodeFrom(String postalCodeFrom) {
		this.postalCodeFrom = postalCodeFrom;
	}

	public String getAdressTo() {
		return adressTo;
	}

	public void setAdressTo(String adressTo) {
		this.adressTo = adressTo;
	}

	public String getCityTo() {
		return cityTo;
	}

	public void setCityTo(String cityTo) {
		this.cityTo = cityTo;
	}

	public String getProvinceTo() {
		return provinceTo;
	}

	public void setProvinceTo(String provinceTo) {
		this.provinceTo = provinceTo;
	}

	public String getCountryTo() {
		return countryTo;
	}

	public void setCountryTo(String countryTo) {
		this.countryTo = countryTo;
	}

	public String getPostalCodeTo() {
		return postalCodeTo;
	}

	public void setPostalCodeTo(String postalCodeTo) {
		this.postalCodeTo = postalCodeTo;
	}

	public String getPriceTrip() {
		return priceTrip;
	}

	public void setPriceTrip(String priceTrip) {
		this.priceTrip = priceTrip;
	}

	public String getCommentTrip() {
		return commentTrip;
	}

	public void setCommentTrip(String commentTrip) {
		this.commentTrip = commentTrip;
	}

	public String getMaxSeats() {
		return maxSeats;
	}

	public void setMaxSeats(String maxSeats) {
		this.maxSeats = maxSeats;
	}

	public String getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(String availableSeats) {
		this.availableSeats = availableSeats;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getLatZipCodeFrom() {
		return latZipCodeFrom;
	}

	public void setLatZipCodeFrom(String latZipCodeFrom) {
		this.latZipCodeFrom = latZipCodeFrom;
	}

	public String getLongZipCodeFrom() {
		return longZipCodeFrom;
	}

	public void setLongZipCodeFrom(String longZipCodeFrom) {
		this.longZipCodeFrom = longZipCodeFrom;
	}

	public String getLatZipCodeTo() {
		return latZipCodeTo;
	}

	public void setLatZipCodeTo(String latZipCodeTo) {
		this.latZipCodeTo = latZipCodeTo;
	}

	public String getLongZipCodeTo() {
		return longZipCodeTo;
	}

	public void setLongZipCodeTo(String longZipCodeTo) {
		this.longZipCodeTo = longZipCodeTo;
	}

	public String register() {

		TripsService trip = Factories.services.createTripService();

		if (trip.registrar(this)) {
			reiniciarBean();
			
			FacesContext context = FacesContext.getCurrentInstance();
			
	        ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msgs");
			
	        context.addMessage(null, new FacesMessage(bundle.getString("Exito")
	        		,bundle.getString("viajeCreado")));
			
			return "exito";
		}

		return "fallo";
	}
	
	private void reiniciarBean() {
		setAdressFrom("");
		setCityFrom("");
		setProvinceFrom("");
		setCountryFrom("");
		setPostalCodeFrom("");
		setAdressTo("");
		setCityTo("");
		setProvinceTo("");
		setCountryTo("");
		setPostalCodeTo("");
		setDateFrom(null);
		setDateTo(null);
		setDateLimit(null);
		setPriceTrip("");
		setCommentTrip("");
		setMaxSeats("");
		setAvailableSeats("");
	}

	@SuppressWarnings("deprecation")
	public void datosPorDefecto() {
		if (getAdressFrom().compareTo("") != 0 && getDateFrom() != null) {
			reiniciarBean();
		} else {
			setAdressFrom("CalleSalida");
			setCityFrom("CiudadSalida");
			setProvinceFrom("ProvinciaSalida");
			setCountryFrom("PaisSalida");
			setPostalCodeFrom("CodigoPostalSalida");

			setAdressTo("CalleDestino");
			setCityTo("CiudadDestino");
			setProvinceTo("ProvinciaDestino");
			setCountryTo("PaisDestino");
			setPostalCodeTo("CodigoPostalDestino");

			Date fecha = new Date();
			setDateFrom(fecha);

			fecha.setMonth(fecha.getMonth() + 1);
			setDateTo(fecha);
			fecha.setDate(fecha.getDate() - 1);
			setDateLimit(fecha);

			setPriceTrip("20");
			setCommentTrip("Buena compañia");
			setMaxSeats("5");
			setAvailableSeats("4");
		}
	}

}
