package com.sdi.persistence.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.sdi.model.Rating;
import com.sdi.persistence.RatingDao;
import com.sdi.persistence.util.JdbcTemplate;
import com.sdi.persistence.util.RowMapper;

public class RatingDaoJdbcImpl implements RatingDao {

	public class RatingMapper implements RowMapper<Rating> {

		@Override
		public Rating toObject(ResultSet rs) throws SQLException {
			Rating dto = new Rating();
			
			dto.setId( rs.getLong( "ID" ) );
			dto.setComment( rs.getString( "COMMENT" ) );
			dto.setValue( rs.getInt( "VALUE" ) );
			dto.setSeatAboutTripId( rs.getLong( "ABOUT_TRIP_ID" ) );
			dto.setSeatAboutUserId( rs.getLong( "ABOUT_USER_ID" ) );
			dto.setSeatFromTripId( rs.getLong( "FROM_TRIP_ID" ) );
			dto.setSeatFromUserId( rs.getLong( "FROM_USER_ID" ) );
			
			return dto;
		}

	}

	private JdbcTemplate jdbcTemplate = new JdbcTemplate();

	@Override
	public Long save(Rating dto) {
		jdbcTemplate.execute("RATING_INSERT",
				dto.getComment(),
				dto.getValue(),
				dto.getSeatAboutTripId(),
				dto.getSeatAboutUserId(),
				dto.getSeatFromTripId(),
				dto.getSeatFromUserId()
			);
		return jdbcTemplate.getGeneratedKey();
	}

	@Override
	public int update(Rating dto) {
		return jdbcTemplate.execute("RATING_UPDATE",
				dto.getComment(),
				dto.getValue(),
				dto.getSeatAboutTripId(),
				dto.getSeatAboutUserId(),
				dto.getSeatFromTripId(),
				dto.getSeatFromUserId(),
				
				dto.getId()
			);

	}

	@Override
	public int delete(Long id) {
		return jdbcTemplate.execute("RATING_DELETE", id );
	}

	@Override
	public Rating findById(Long id) {
		return jdbcTemplate.queryForObject(
				"RATING_FIND_BY_ID", 
				new RatingMapper(), 
				id
			);
	}

	@Override
	public List<Rating> findAll() {
		return jdbcTemplate.queryForList("RATING_FIND_ALL", new RatingMapper());
	}

	@Override
	public Rating findByAboutFrom(
			Long aboutUserId, Long aboutTripId, Long fromUserId, Long fromTripId) {
		
		return jdbcTemplate.queryForObject(
				"RATING_FIND_BY_ABOUT_FROM", 
				new RatingMapper(), 
				aboutUserId,
				aboutTripId,
				fromUserId,
				fromTripId
			);
	}

	@Override
	public List<Rating> findByAbout(Long aboutUserId) {
		return jdbcTemplate.queryForList(
				"RATING_FIND_ABOUT", 
				new RatingMapper(), 
				aboutUserId);
	}
	
	@Override
	public List<Rating> findByFrom(Long fromUserId,Long fromTripId) {
		return jdbcTemplate.queryForList(
				"RATING_FIND_FROM", 
				new RatingMapper(), 
				fromUserId,fromTripId);
	}

}
