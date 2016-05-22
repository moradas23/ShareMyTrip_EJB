


/**
 * This class represents a value type
 * @author alb
 */

public class AddressPoint {


	public string address { get; set; }
    public string city { get; set; }
    public string state { get; set; }
    public string country { get; set; }
    public string zipCode { get; set; }
    public Waypoint waypoint { get; set; }

    public AddressPoint(string address, string city, string state, 
			string country, string zipCode, Waypoint waypoint) {
		

		this.address = address;
		this.city = city;
		this.state = state;
		this.country = country;
		this.zipCode = zipCode;
		this.waypoint = waypoint;
	}
	
	public AddressPoint(){
		
	}

	public string getAddress() {
		return address;
	}

	
	public string getCity() {
		return city;
	}

	
	public string getState() {
		return state;
	}


    public string getCountry() {
	return country;
	}

	
	public string getZipCode() {
		return zipCode;
	}

	
	public Waypoint getWaypoint() {
		return waypoint;
	}

	
	public string toString() {
		return "Destination [address=" + address + ", city=" + city 
				+ ", state=" + state + ", country=" + country
				+ ", zipCode=" + zipCode + ", waypoint=" + waypoint + "]";
	}	
}
