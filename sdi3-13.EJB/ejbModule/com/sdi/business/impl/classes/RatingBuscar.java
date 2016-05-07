package com.sdi.business.impl.classes;

import java.util.List;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Rating;
import com.sdi.persistence.RatingDao;

public class RatingBuscar {

	public List<Rating> findByLastMonth() {
		RatingDao dao = Factories.persistence.createRatingDao();
		return dao.findLastMonth();
	}

	public List<Rating> findByAboutUser(Long id) {
		RatingDao dao = Factories.persistence.createRatingDao();
		return dao.findByAbout(id);
	}

}
