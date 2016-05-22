

using System;
/**
* This class is not an entity, it is a DTO with the same fields as 
* a row in the table
* 
* @see Data Transfer Object pattern
* @author alb
*
*/
public class Trip  {
	
	/**
	 * 
	 */
	private static readonly long serialVersionUID = 1L;

	private long id;
	
	private AddressPoint departure;
	private AddressPoint destination;
	private DateTime arrivalDate;
	private DateTime departureDate;
	private DateTime closingDate;
	private int availablePax = 0; 
	private int maxPax = 0;
	private Double estimatedCost = 0.0;
	private String comments;
	private TripStatus status;
	
	private long promoterId;
	
	public AddressPoint getDeparture() {
		return departure;
	}

	public void setDeparture(AddressPoint departure) {
		this.departure = departure;
	}

	public TripStatus getStatus() {
		return status;
	}

	public void setStatus(TripStatus status) {
		this.status = status;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public AddressPoint getDestination() {
		return destination;
	}

	public void setDestination(AddressPoint destination) {
		this.destination = destination;
	}

	public DateTime getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(DateTime arrivalDate) {
		this.arrivalDate = arrivalDate;
	}


	public DateTime getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(DateTime departureDate) {
		this.departureDate = departureDate;
	}


	public DateTime getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(DateTime closingDate) {
		this.closingDate = closingDate;
	}

	
	public int getAvailablePax() {
		return availablePax;
	}

	public void setAvailablePax(int availablePax) {
		this.availablePax = availablePax;
	}

	
	public int getMaxPax() {
		return maxPax;
	}

	public void setMaxPax(int maxPax) {
		this.maxPax = maxPax;
	}

	
	public Double getEstimatedCost() {
		return estimatedCost;
	}

	public void setEstimatedCost(Double estimatedCost) {
		this.estimatedCost = estimatedCost;
	}

	
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	
	public long getPromoterId() {
		return promoterId;
	}

	public void setPromoterId(long promoterId) {
		this.promoterId = promoterId;
	}
	


	

}
