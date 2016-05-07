package com.sdi.business;

import java.util.List;

import com.sdi.model.Rating;

public interface RatingService {

	List<Rating> findLastMonth();

}
