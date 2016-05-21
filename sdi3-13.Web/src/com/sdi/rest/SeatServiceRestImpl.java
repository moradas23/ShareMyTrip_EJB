package com.sdi.rest;

import com.sdi.business.SeatService;
import com.sdi.infrastructure.Factories;
import com.sdi.model.Seat;

public class SeatServiceRestImpl implements SeatServiceRest {

	SeatService serviceS = Factories.services.getSeatService();
	
	@Override
	public void insertSeat(Seat seat) {
		serviceS.insertSeat(seat);
	}

}
