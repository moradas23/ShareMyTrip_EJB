package com.sdi.business.impl.rating;

import java.util.List;
import javax.ejb.Stateless;
import javax.jws.WebService;
import com.sdi.business.impl.classes.RatingBuscar;
import com.sdi.business.impl.classes.RatingDelete;
import com.sdi.model.Rating;

@Stateless
@WebService(name="RatingService")
public class EjbRatingService implements LocalRatingService,RemoteRatingService {

	@Override
	public List<Rating> findLastMonth() {
		return new RatingBuscar().findByLastMonth();
	}

	@Override
	public List<Rating> findByAboutUser(Long id) {
		return new RatingBuscar().findByAboutUser(id);
	}

	@Override
	public void delete(Long id) {
		new RatingDelete().delete(id);
	}

}
