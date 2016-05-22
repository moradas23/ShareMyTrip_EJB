/**
 * This class represents a value type
 * @author alb
 */

public class Waypoint { 

	/**
	 * 
	 */
	private static readonly long serialVersionUID = 1L;
	private double lat;
	private double lon;
	
	public Waypoint(double lat, double lon) {
		this.lat = lat;
		this.lon = lon;
	}
	
	public Waypoint(){
		
	}
	public double getLat() {
		return lat;
	}

	public double getLon() {
		return lon;
	}

	public string toString() {
		return "Waypoint [lat=" + lat + ", lon=" + lon + "]";
	}
}
