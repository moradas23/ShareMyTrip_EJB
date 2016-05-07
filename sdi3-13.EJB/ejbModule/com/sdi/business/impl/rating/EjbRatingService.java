package com.sdi.business.impl.rating;

import java.util.List;

import javax.ejb.Stateless;
import javax.jws.WebService;

import com.sdi.business.impl.classes.RatingBuscar;
import com.sdi.infrastructure.Factories;
import com.sdi.model.Rating;
import com.sdi.persistence.RatingDao;

@Stateless
@WebService(name="RatingService")
public class EjbRatingService implements LocalRatingService,RemoteRatingService {

	@Override
	public List<Rating> findLastMonth() {
		return new RatingBuscar().findByLastMonth();
	}

}
