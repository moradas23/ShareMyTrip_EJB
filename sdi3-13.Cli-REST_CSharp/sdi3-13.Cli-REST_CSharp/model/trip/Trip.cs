using Newtonsoft.Json;
using Newtonsoft.Json.Converters;
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

	public long id;
	public AddressPoint departure { get; set; }
	public AddressPoint destination { get; set; }
    public long arrivalDate { get; set; }
    public long departureDate { get; set; }
    public long closingDate { get; set; }
    public int availablePax = 0;
    public int maxPax = 0;
    public Double estimatedCost = 0.0;
    public String comments { get; set; }
    public TripStatus status { get; set; }

    public long promoterId;

    public DateTime getArrivalDate()
    {
        DateTime start = new DateTime(1970, 1, 1, 0, 0, 0, DateTimeKind.Utc);
        DateTime date = start.AddMilliseconds(arrivalDate).ToLocalTime();

        return date;
    }


    public DateTime getDepartureDate()
    {
        DateTime start = new DateTime(1970, 1, 1, 0, 0, 0, DateTimeKind.Utc);
        DateTime date = start.AddMilliseconds(departureDate).ToLocalTime();

        return date;
    }


    public DateTime getClosingDate()
    {
        DateTime start = new DateTime(1970, 1, 1, 0, 0, 0, DateTimeKind.Utc);
        DateTime date = start.AddMilliseconds(closingDate).ToLocalTime();

        return date;
    }

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
