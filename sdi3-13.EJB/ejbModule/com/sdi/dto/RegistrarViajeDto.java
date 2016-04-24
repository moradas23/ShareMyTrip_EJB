package com.sdi.dto;

import java.util.Date;

public class RegistrarViajeDto {
	
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
	
	private Long idViaje;
	private Long promoter;
	
	private String priceTrip = "";
	private String commentTrip = "";
	private String maxSeats = "";
	private String availableSeats = "";

	private String latZipCodeFrom = "";
	private String longZipCodeFrom = "";
	private String latZipCodeTo = "";
	private String longZipCodeTo = "";
	
	private String result;

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

	public Long getIdViaje() {
		return idViaje;
	}

	public void setIdViaje(Long idViaje) {
		this.idViaje = idViaje;
	}

	public Long getPromoter() {
		return promoter;
	}

	public void setPromoter(Long promoter) {
		this.promoter = promoter;
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

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	
}
