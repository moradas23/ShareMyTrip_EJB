package com.sdi.persistence.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.sdi.model.AddressPoint;
import com.sdi.model.Trip;
import com.sdi.model.TripStatus;
import com.sdi.model.Waypoint;
import com.sdi.persistence.TripDao;
import com.sdi.persistence.util.JdbcTemplate;
import com.sdi.persistence.util.RowMapper;

public class TripJdbcDAO implements TripDao {

	public class TripMapper implements RowMapper<Trip> {

		@Override
		public Trip toObject(ResultSet rs) throws SQLException {
			Trip res = new Trip();
			
			Waypoint wpt = new Waypoint( 
					rs.getDouble("departure_wpt_lat"), 
					rs.getDouble("departure_wpt_lon")
				);
			AddressPoint ap = new AddressPoint(
					rs.getString( "departure_address"),
					rs.getString( "departure_city" ),
					rs.getString( "departure_state" ),
					rs.getString( "departure_country" ),
					rs.getString( "departure_zipcode" ),
					wpt
				);
			res.setDeparture( ap );
			
			wpt = new Waypoint( 
					rs.getDouble("destination_wpt_lat"), 
					rs.getDouble("destination_wpt_lon")
				);
			ap = new AddressPoint(
					rs.getString( "destination_address"),
					rs.getString( "destination_city" ),
					rs.getString( "destination_state" ),
					rs.getString( "destination_country" ),
					rs.getString( "destination_zipcode" ),
					wpt
				);
			res.setDestination( ap );
			
			res.setArrivalDate( toDate( rs.getTimestamp("arrivalDate")) );
			res.setDepartureDate( toDate( rs.getTimestamp("departureDate")) );
			res.setClosingDate( toDate( rs.getTimestamp("closingDate")) );
			
			res.setAvailablePax( rs.getInt("availablePax") );
			res.setMaxPax( rs.getInt("maxPax") );
			res.setComments( rs.getString("comments") );
			res.setEstimatedCost( rs.getDouble("estimatedCost") );
			res.setPromoterId( rs.getLong("promoter_Id") );
			res.setStatus( TripStatus.values()[ rs.getInt("status") ] );
			res.setId( rs.getLong("id") );
			
			return res;
		}

		private Date toDate(Timestamp timestamp) throws SQLException {
			return new Date( timestamp.getTime() );
		}

	}
	
	private JdbcTemplate jdbcTemplate = new JdbcTemplate();

	@Override
	public Long save(Trip dto) {
		jdbcTemplate.execute("TRIP_INSERT", 
				dto.getDeparture().getAddress(), 
				dto.getDeparture().getCity(), 
				dto.getDeparture().getState(), 
				dto.getDeparture().getCountry(), 
				dto.getDeparture().getZipCode(), 
				dto.getDeparture().getWaypoint().getLat(), 
				dto.getDeparture().getWaypoint().getLon(), 
				
				dto.getDestination().getAddress(), 
				dto.getDestination().getCity(), 
				dto.getDestination().getState(), 
				dto.getDestination().getCountry(), 
				dto.getDestination().getZipCode(), 
				dto.getDestination().getWaypoint().getLat(), 
				dto.getDestination().getWaypoint().getLon(),
				
				dto.getArrivalDate(), 
				dto.getDepartureDate(),
				dto.getClosingDate(),
				dto.getAvailablePax(),
				dto.getMaxPax(),
				dto.getEstimatedCost(),
				dto.getComments(),
				dto.getStatus().ordinal(),
				dto.getPromoterId()
			);
		return jdbcTemplate.getGeneratedKey();
	}

	@Override
	public int update(Trip dto) {
		return jdbcTemplate.execute("TRIP_UPDATE", 
				dto.getDeparture().getAddress(), 
				dto.getDeparture().getCity(), 
				dto.getDeparture().getState(), 
				dto.getDeparture().getCountry(), 
				dto.getDeparture().getZipCode(), 
				dto.getDeparture().getWaypoint().getLat(), 
				dto.getDeparture().getWaypoint().getLon(), 

				dto.getDestination().getAddress(), 
				dto.getDestination().getCity(), 
				dto.getDestination().getState(), 
				dto.getDestination().getCountry(), 
				dto.getDestination().getZipCode(), 
				dto.getDestination().getWaypoint().getLat(), 
				dto.getDestination().getWaypoint().getLon(), 
				
				dto.getArrivalDate(), 
				dto.getDepartureDate(),
				dto.getClosingDate(),
				dto.getAvailablePax(),
				dto.getMaxPax(),
				dto.getEstimatedCost(),
				dto.getComments(),
				dto.getStatus().ordinal(),
				dto.getPromoterId(),
				
				dto.getId()
			);
	}

	@Override
	public int delete(Long id) {
		return jdbcTemplate.execute("TRIP_DELETE", id);
	}

	@Override
	public Trip findById(Long id) {
		return jdbcTemplate.queryForObject(
				"TRIP_FIND_BY_ID", 
				new TripMapper(), 
				id
			);
	}

	@Override
	public List<Trip> findAll() {
		return jdbcTemplate.queryForList("TRIP_FIND_ALL", new TripMapper());
	}

	@Override
	public Trip findByPromoterIdAndArrivalDate(Long id, Date arrivalDate) {
		return jdbcTemplate.queryForObject(
				"TRIP_FIND_BY_PROMOTER_AND_ARRIVAL", 
				new TripMapper(),
				id, arrivalDate);
	}

	@Override
	public List<Trip> findByPromoterId(Long id) {
		return jdbcTemplate.queryForList(
				"TRIP_FIND_BY_PROMOTER", 
				new TripMapper(),
				id);
	}

	@Override
	public List<Trip> findAllDisponible() {
		return jdbcTemplate.queryForList(
				"TRIP_FIND_DISPONIBLES", 
				new TripMapper());
	}

	@Override
	public List<Trip> findAllDisponibleUser(String login) {
		return jdbcTemplate.queryForList(
				"TRIP_FIND_DISP_USER", 
				new TripMapper(),
				login,login,login);
	}

	@Override
	public int disminuirPlazas(Long idViaje) {
		return jdbcTemplate.execute("TRIP_DISMINUIR_PLAZAS", idViaje);
	}
	
	@Override
	public int aumentarPlazas(Long idViaje) {
		return jdbcTemplate.execute("TRIP_AUMENTAR_PLAZAS", idViaje);
		
	}

	@Override
	public List<Trip> findFechaCierrePasada() {
		return jdbcTemplate.queryForList(
				"TRIP_FIND_FECHA_CIERRE_PASADA", 
				new TripMapper());
	}

	@Override
	public List<Trip> findLastMonth() {
		return jdbcTemplate.queryForList(
				"TRIP_FIND_BY_LAST_MONTH", 
				new TripMapper()
			);
	}

	@Override
	public List<Trip> findByPromoterIdAndDone(Long idUser) {
		return jdbcTemplate.queryForList(
				"TRIP_FIND_BY_PROMOTER_DONE", 
				new TripMapper(),
				idUser);
	}

	@Override
	public List<Trip> findHaParticipado(Long idUser) {
		return jdbcTemplate.queryForList("TRIP_FIND_HA_PARTICIPADO", new TripMapper(),idUser);
	}

	@Override
	public List<Trip> findByPromoterIdAndAvailablePax(Long id) {
		return jdbcTemplate.queryForList(
				"TRIP_FIND_BY_PROMOTER_AND_AVAILABLE_PAX", 
				new TripMapper(),
				id);
	}

	@Override
	public List<Trip> findParticipa(Long idUsuario) {
		return jdbcTemplate.queryForList(
				"TRIP_FIND_BY_PARTICIPA", 
				new TripMapper(),
				idUsuario);
	}


}
