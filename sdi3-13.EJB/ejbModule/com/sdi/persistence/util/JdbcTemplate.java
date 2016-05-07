package com.sdi.persistence.util;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.sdi.persistence.exception.PersistenceException;
import com.sdi.persistence.impl.JdbcHelper;


/**
 * Provides template methods to execute DML statements and queries with one
 * single object return or a list of them.
 * 
 * The queries must be located in a properties file which name is determined in
 * the Jdbc.class
 * 
 * @author alb
 */
public class JdbcTemplate {

	private Object generatedKey;
	
	private static String CONFIG_FILE = "/persistence.properties";
	private static JdbcHelper jdbc = new JdbcHelper(CONFIG_FILE);

	/**
	 * Template method to execute INSERT, UPDATE and DELETE DML statements.
	 * If due to the statement execution a new row is inserted and a key has
	 * been generated by the database it can be recovered with 
	 * the getGeneratedKey() method
	 * 
	 * @param queryKey, Key for the query in the properties file
	 * @param args, arguments to the JDBC query
	 * @return the number of affected rows
	 */
	public int execute(String queryKey, Object... args) {
		String sql = jdbc.getSqlQuery(queryKey);

		Connection con = null;
		PreparedStatement ps =  null;
		try {
			con = jdbc.createConnection();
			ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			bindSqlParameters(args, ps);

			int res = ps.executeUpdate();
			recoverGeneratedKeys(ps);
			return res;

		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
			jdbc.close( ps, con );
		}
	}

	/**
	 * Template method to execute a SELECT returning a single object
	 * 
	 * @param queryKey, Key for the query in the properties file
	 * @param mapper, a instantiation of RowMapper to convert from row to 
	 * 			the result object
	 * @param args, arguments to the JDBC query
	 * @return the object of type T retrieved from the row
	 */
	public <T> T queryForObject(String queryKey, RowMapper<T> mapper, Object... args) {
		String sql = jdbc.getSqlQuery(queryKey);

		Connection con = null;
		PreparedStatement ps =  null;
		ResultSet rs = null;
		try {
			con = jdbc.createConnection();
			ps = con.prepareStatement(sql);
			bindSqlParameters(args, ps);
			rs = ps.executeQuery();

			return rs.next() ? mapper.toObject(rs) : null;

		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
			jdbc.close( ps, rs, con );
		}
	}

	/**
	 * Template method to execute a SELECT returning a list of objects
	 * @param queryKey, Key for the query in the properties file
	 * @param mapper, a instantiation of RowMapper to convert from row to 
	 * 			the result object
	 * @param args, arguments to the JDBC query
	 * @return the list of objects of type T retrieved from the query
	 */
	public <T> List<T> queryForList(
			String queryKey, RowMapper<T> mapper, Object... args) {
		
		String sql = jdbc.getSqlQuery(queryKey);

		Connection con = null;
		PreparedStatement ps =  null;
		ResultSet rs = null;
		try {
			con = jdbc.createConnection();
			ps = con.prepareStatement(sql);
			bindSqlParameters(args, ps);
			rs = ps.executeQuery();

			List<T> res = new LinkedList<>();
			while (rs.next()) {
				res.add( mapper.toObject(rs) );
			}
			return res;

		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
			jdbc.close( ps, rs, con );
		}
	}

	@SuppressWarnings("unchecked")
	public <K> K getGeneratedKey() {
		return (K) generatedKey;
	}

	protected void bindSqlParameters(Object[] args, PreparedStatement ps) 
			throws SQLException {
		
		for (int i = 0; i < args.length; i++) {
			callProperSetterMethod(i+1 /* first jdbc param index */, args[i], ps);
		}
	}

	private void recoverGeneratedKeys(PreparedStatement ps) throws SQLException {
		ResultSet rs = ps.getGeneratedKeys();
		try {
			if ( rs.next() ) {
				generatedKey = rs.getObject(1);
			}
		}
		finally {
			jdbc.close(null,rs,null );
		}
	}

	private void callProperSetterMethod(
			int idx, Object arg, PreparedStatement ps) throws SQLException {
		
		if (arg == null) {
			ps.setNull(idx, java.sql.Types.OTHER);
		}else if (arg instanceof String) {
			ps.setString(idx, (String) arg);
		} else if (arg instanceof Integer) {
			ps.setInt(idx, (int) arg);
		} else if (arg instanceof Long) {
			ps.setLong(idx, (long) arg);
		} else if (arg instanceof Double) {
			ps.setDouble(idx, (double) arg);
		} else if (arg instanceof Date) {
			ps.setDate(idx, new java.sql.Date( ((Date) arg).getTime() ) );
		} else if (arg instanceof java.sql.Date) {
			ps.setDate(idx, (java.sql.Date) arg);
		} else if (arg instanceof Boolean) {
			ps.setBoolean(idx, (boolean) arg);
		} else if (arg instanceof Float) {
			ps.setFloat(idx, (float) arg);
		} else if (arg instanceof java.sql.Time) {
			ps.setTime(idx, (java.sql.Time) arg);
		} else if (arg instanceof Short) {
			ps.setShort(idx, (short) arg);
		} else if (arg instanceof Byte) {
			ps.setByte(idx, (byte) arg);
		} else if (arg instanceof Byte[]) {
			ps.setBytes(idx, (byte[]) arg);
		} else {
			ps.setObject(idx, arg);
		}
	}

}
