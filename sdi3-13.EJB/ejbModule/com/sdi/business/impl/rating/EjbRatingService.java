package com.sdi.business.impl.rating;

import javax.ejb.Stateless;
import javax.jws.WebService;

@Stateless
@WebService(name="RatingService")
public class EjbRatingService implements LocalRatingService,RemoteRatingService {

}
