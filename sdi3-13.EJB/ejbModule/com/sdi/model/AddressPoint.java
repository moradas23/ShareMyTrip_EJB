package com.sdi.model;


/**
 * This class represents a value type
 * @author alb
 */
public class AddressPoint {
	
	private String address;
	private String city;
	private String state;
	private String country;
	private String zipCode;
	private Waypoint waypoint;
	
	public AddressPoint(String address, String city, String state, 
			String country, String zipCode, Waypoint waypoint) {
		
		super();
		this.address = address;
		this.city = city;
		this.state = state;
		this.country = country;
		this.zipCode = zipCode;
		this.waypoint = waypoint;
	}

	public String getAddress() {
		return address;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getCountry() {
		return country;
	}

	public String getZipCode() {
		return zipCode;
	}

	public Waypoint getWaypoint() {
		return waypoint;
	}

	@Override
	public String toString() {
		return "Destination [address=" + address + ", city=" + city 
				+ ", state=" + state + ", country=" + country
				+ ", zipCode=" + zipCode + ", waypoint=" + waypoint + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((waypoint == null) ? 0 : waypoint.hashCode());
		result = prime * result + ((zipCode == null) ? 0 : zipCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddressPoint other = (AddressPoint) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (waypoint == null) {
			if (other.waypoint != null)
				return false;
		} else if (!waypoint.equals(other.waypoint))
			return false;
		if (zipCode == null) {
			if (other.zipCode != null)
				return false;
		} else if (!zipCode.equals(other.zipCode))
			return false;
		return true;
	}

}
