package com.sdi.persistence.impl;

import java.sql.Connection;
import java.sql.SQLException;

import com.sdi.persistence.Transaction;
import com.sdi.persistence.exception.PersistenceException;

public class TransactionJdbcImpl implements Transaction {
	
	private static String CONFIG_FILE = "/persistence.properties";
	private JdbcHelper jdbc = new JdbcHelper(CONFIG_FILE);
	
	private Connection con;

	@Override
	public void begin() {
		assertNullConnection();
		//con = Jdbc.createConnection();
		con =jdbc.createConnection();
		try {
			con.setAutoCommit( false );
		} catch (SQLException e) {
			throw new PersistenceException("Invalid SQL or database schema", e);
		}
	}

	@Override
	public void commit() {
		assertNonNullConnection();
		assertOpenConnection();
		try {
			con.commit();
			con.setAutoCommit( true ); // makes Jdbc.close() to really close connection
		} catch (SQLException e) {
			throw new PersistenceException( e );
		}
		finally {
			jdbc.close( null,null,con );
		}
	}

	@Override
	public void rollback() {
		assertNonNullConnection();
		assertOpenConnection();
		try {
			con.rollback();
			con.setAutoCommit( true ); // makes Jdbc.close() to really close connection
		} catch (SQLException e) {
			throw new PersistenceException( e );
		}
		finally {
			jdbc.close( null,null,con );
		}
	}

	private void assertNullConnection() {
		if (con == null) return;
		throw new PersistenceException("Transaction is already initiated");
	}

	private void assertNonNullConnection() {
		if (con != null) return;
		throw new PersistenceException("Transaction is not initiated. "
				+ "Call begin() method before use it.");
	}

	private void assertOpenConnection() {
		if ( connectionIsOpen() ) return;
		throw new PersistenceException("Transaction is not initiated. "
				+ "Call begin() method before use it.");
	}

	private boolean connectionIsOpen() {
		try {
			return ! con.isClosed();
		} catch (SQLException e) {
			throw new PersistenceException("Unexpected JDBC error");
		}
	}

}
