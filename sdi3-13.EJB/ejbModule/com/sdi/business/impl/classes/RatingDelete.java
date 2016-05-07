package com.sdi.business.impl.classes;

import com.sdi.infrastructure.Factories;
import com.sdi.persistence.RatingDao;

public class RatingDelete {

	public Object delete(Long id) {
		RatingDao dao = Factories.persistence.createRatingDao();
		return dao.delete(id);
	}

}
