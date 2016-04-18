package com.sdi.model;

import javax.xml.crypto.Data;

/**
 * This class is not an entity, it is a DTO with the same fields as a row in the
 * table
 * 
 * @see Data Transfer Object pattern
 * @author alb
 *
 */
public class Seat {

	private Long userId;
	private Long tripId;

	private String comment;
	private SeatStatus status;


	public Long[] makeKey() {
		return new Long[]{ userId, tripId };
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getTripId() {
		return tripId;
	}

	public void setTripId(Long tripId) {
		this.tripId = tripId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public SeatStatus getStatus() {
		return status;
	}

	public void setStatus(SeatStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Seat [userId=" + userId + ", tripId=" 
				+ tripId + ", comment=" + comment 
				+ ", status=" + status + "]";
	}

}
