

public class Application {

	public long userId { get; set; }
	public long tripId { get; set; }

	public Application() {
	}

	public Application(long userId, long tripId) {
		this.userId = userId;
		this.tripId = tripId;
	}

	
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}


	public long getTripId() {
		return tripId;
	}

	public void setTripId(long tripId) {
		this.tripId = tripId;
	}


	public string toString() {
		return "Application [userId=" + userId + ", tripId=" + tripId + "]";
	}

	public long[] makeKey() {
		return new long[] { userId, tripId };
	}

}
