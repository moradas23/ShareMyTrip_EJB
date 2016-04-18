package com.sdi.persistence;

import java.util.List;

import com.sdi.model.Application;
import com.sdi.persistence.util.GenericDao;

public interface ApplicationDao extends GenericDao<Application, Long[]>{

	List<Application> findByUserId( Long userId );
	List<Application> findByTripId( Long tripId );
	Long[] save(String loginUsuario, Long idViaje);
	List<Application> findByUserLogin(String login);
	
}
