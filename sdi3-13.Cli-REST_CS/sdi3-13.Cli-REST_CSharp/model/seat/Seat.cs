
/**
 * This class is not an entity, it is a DTO with the same fields as a row in the
 * table
 * 
 * @see Data Transfer Object pattern
 * @author alb
 *
 */

public class Seat {

	public long userId {get;set;}
	public long tripId {get;set;}

	public string comment {get;set;}
	public SeatStatus status {get;set;}


	public long[] makeKey() {
		return new long[]{ userId, tripId };
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


	public string getComment() {
		return comment;
	}

	public void setComment(string comment) {
		this.comment = comment;
	}


	public SeatStatus getStatus() {
		return status;
	}

	public void setStatus(SeatStatus status) {
		this.status = status;
	}


	public string toString() {
		return "Seat [userId=" + userId + ", tripId=" 
				+ tripId + ", comment=" + comment 
				+ ", status=" + status + "]";
	}

}
