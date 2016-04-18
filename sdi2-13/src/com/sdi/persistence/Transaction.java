package com.sdi.persistence;

/**
 * Every isolated call to a DAO method is run in its own and self contained
 * transaction (JDBC auto commit mode way). When several calls to (perhaps
 * different) DAO(s) methods must be done inside one single transaction one
 * object of type transaction must be acquired and the transaction controlled
 * with the usual begin-commit-rollback semantics.
 * 
 * XxxDao xDao = PersistenceFactory.newXxxDao();
 * YyyDao yDao = PersistenceFactory.newXxxDao();
 * Transaction t = PersistenceFactory.newTransaction();
 * 
 * t.begin(); 
 * try {
 * 
 * 		xDao.save( ... ); 
 * 		yDao.delete( ... );
 * 
 * 		t.commit(); 
 * } catch (PersistenceException e) { 
 * 		t.rollback();
 * 		throw e; 
 * }
 * 
 * 
 * @author alb
 *
 */
public interface Transaction {

	void begin();
	void commit();
	void rollback();

}
