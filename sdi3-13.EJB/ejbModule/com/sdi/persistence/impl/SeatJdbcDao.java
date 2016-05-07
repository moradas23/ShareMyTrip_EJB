package com.sdi.persistence.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.sdi.model.Seat;
import com.sdi.model.SeatStatus;
import com.sdi.persistence.SeatDao;
import com.sdi.persistence.util.JdbcTemplate;
import com.sdi.persistence.util.RowMapper;

public class SeatJdbcDao implements SeatDao {

	public class SeatMapper implements RowMapper<Seat> {

		@Override
		public Seat toObject(ResultSet rs) throws SQLException {
			Seat res = new Seat();
			
			res.setUserId( rs.getLong("user_id") );
			res.setTripId( rs.getLong("trip_id") );
			res.setComment( rs.getString("comment") );
			res.setStatus( SeatStatus.values()[ rs.getInt("status") ] );
			
			return res;
		}

	}
	
	private JdbcTemplate jdbcTemplate = new JdbcTemplate();

	@Override
	public Long[] save(Seat dto) {
		jdbcTemplate.execute("SEAT_INSERT", 
				dto.getUserId(), 
				dto.getTripId(), 
				dto.getComment(),
				dto.getStatus().ordinal()
			);
		return null;
	}

	@Override
	public int update(Seat dto) {
		return jdbcTemplate.execute("SEAT_UPDATE", 
				dto.getComment(),
				dto.getStatus().ordinal(),
				dto.getUserId(), 
				dto.getTripId()
			);
	}

	@Override
	public int delete(Long[] ids) {
		return jdbcTemplate.execute("SEAT_DELETE", ids[0], ids[1]);
	}

	@Override
	public Seat findById(Long[] ids) {
		return jdbcTemplate.queryForObject(
				"SEAT_FIND_BY_ID", 
				new SeatMapper(), 
				ids[0], ids[1]
			);
	}

	@Override
	public List<Seat> findAll() {
		return jdbcTemplate.queryForList("SEAT_FIND_ALL", new SeatMapper());
	}

	@Override
	public Seat findByUserAndTrip(Long userId, Long tripId) {
		return jdbcTemplate.queryForObject(
				"SEAT_FIND_BY_ID", 
				new SeatMapper(), 
				userId, tripId
			);
	}

	@Override
	public List<Seat> findByUser(Long idUsuario) {
		return jdbcTemplate.queryForList("SEAT_FIND_BY_USER",
				new SeatMapper(),
				idUsuario);

	}
	
	@Override
	public List<Seat> findAcceptedByUser(Long idUsuario) {
		return jdbcTemplate.queryForList("SEAT_FIND_ACCEPTED_BY_USER",
				new SeatMapper(),
				idUsuario);
	}

	@Override
	public List<Seat> findByTrip(Long idViaje) {
		return jdbcTemplate.queryForList("SEAT_FIND_BY_TRIP",
				new SeatMapper(),
				idViaje);
	}

	@Override
	public List<Seat> findAllAceptadas(Long idViaje) {
		return jdbcTemplate.queryForList("SEAT_FIND_BY_TRIP_ACCEPTED",
				new SeatMapper(),
				idViaje);
	
		
	}


}
