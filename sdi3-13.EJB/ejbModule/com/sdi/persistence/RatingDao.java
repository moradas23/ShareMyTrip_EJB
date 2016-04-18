package com.sdi.persistence;

import java.util.List;

import com.sdi.model.Rating;
import com.sdi.persistence.util.GenericDao;

public interface RatingDao extends GenericDao<Rating, Long> {

	Rating findByAboutFrom(
			Long aboutUserId, 
			Long aboutTripId, 
			Long fromUserId, 
			Long fromTripId
		); 
	
	List<Rating> findByAbout(
			Long aboutUserId
		);

	List<Rating> findByFrom(Long fromUserId, Long fromTripId); 
	
}
