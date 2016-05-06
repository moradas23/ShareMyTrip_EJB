package com.sdi.persistence;
import com.sdi.model.User;
import com.sdi.persistence.exception.AlreadyPersistedException;
import com.sdi.persistence.util.GenericDao;



public interface UserDao extends GenericDao<User, Long>{

	User findByLogin(String login);
	Long save(User dto) throws AlreadyPersistedException;
	int unsubscribe(String login);
	

}
