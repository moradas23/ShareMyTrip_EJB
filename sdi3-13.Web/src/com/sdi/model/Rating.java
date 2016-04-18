package com.sdi.model;

import javax.xml.crypto.Data;

/**
 * This class is not an entity, it is a DTO with the same fields as 
 * a row in the table
 * 
 * @see Data Transfer Object pattern
 * @author alb
 *
 */
public class Rating {

	private Long id;
	private Long seatFromTripId;
	private Long seatFromUserId;
	private Long seatAboutTripId;
	private Long seatAboutUserId;

	private String comment;
	private Integer value = 0;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSeatFromTripId() {
		return seatFromTripId;
	}

	public void setSeatFromTripId(Long seatFromTripId) {
		this.seatFromTripId = seatFromTripId;
	}

	public Long getSeatFromUserId() {
		return seatFromUserId;
	}

	public void setSeatFromUserId(Long seatFromUserId) {
		this.seatFromUserId = seatFromUserId;
	}

	public Long getSeatAboutTripId() {
		return seatAboutTripId;
	}

	public void setSeatAboutTripId(Long seatAboutTripId) {
		this.seatAboutTripId = seatAboutTripId;
	}

	public Long getSeatAboutUserId() {
		return seatAboutUserId;
	}

	public void setSeatAboutUserId(Long seatAboutUserId) {
		this.seatAboutUserId = seatAboutUserId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Rating [id=" + id 
				+ ", comment=" + comment 
				+ ", value=" + value 
				+ ", seatFromTripId=" + seatFromTripId
				+ ", seatFromUserId=" + seatFromUserId 
				+ ", seatAboutTripId=" + seatAboutTripId 
				+ ", seatAboutUserId=" + seatAboutUserId 
			+ "]";
	}
	
}
