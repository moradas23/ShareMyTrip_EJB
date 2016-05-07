package com.sdi.persistence.impl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.sdi.persistence.exception.PersistenceException;

public class JdbcHelper {
	private Properties properties;
	private static Properties sqlQueries;
	private static final String QUERIES_PROPERTIES_FILE = "sql_queries.properties";

	public JdbcHelper(String configFile) {
		properties = new Properties();
		try {
			properties.load(JdbcHelper.class.getResourceAsStream(configFile));
		} catch (IOException e) {
			throw new RuntimeException("Properties file not found: "
					+ configFile);
		}
	}
	
	static {
	
		sqlQueries = loadProperties( QUERIES_PROPERTIES_FILE );

	}
	
	public static String getSqlQuery(String queryKey) {
		return sqlQueries.getProperty(queryKey).trim();
	}


	public Connection createConnection() {

		/* Obtenemos la conexi�n a la base de datos con un Datasource
		 * El Datasource es una factoria de conexiones. 
		 * Las conexiones as� obtenidas son gestionadas por el contenedor
		 * y ello hace que todo el SQL que se ejecute quede dentro de una 
		 * transacci�n
		 */
		try {
			String jndiKey = getProperty("JNDI_DATASOURCE");

			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup(jndiKey);
			return ds.getConnection();
			
		} catch (NamingException e) {
			throw new RuntimeException("Can't open JDBC conection from JNDI", e);
		} catch (SQLException e) {
			throw new RuntimeException("Can't open JDBC conection", e);
		}
	}

	private String getProperty(String property) {
		String value = properties.getProperty(property);
		if (value == null){
			throw new RuntimeException("Property not found " + property);
		}
		return value;
	}

	public String getSql(String sql) {
		return getProperty(sql);
	}

	public void close(PreparedStatement ps, ResultSet rs, Connection con) {
		if (rs != null) {try{ rs.close(); } catch (Exception ex){}};
		close(ps, con);
	}

	public void close(PreparedStatement ps, Connection con) {
		if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
		if (con != null) {try{ con.close(); } catch (Exception ex){}};
	}
	
	private static Properties loadProperties(String fileName) {
		Properties prop = new Properties();
		InputStream stream = JdbcHelper.class.getClassLoader().getResourceAsStream(fileName);
		try {
			prop.load( stream );
		} catch (IOException e) {
			throw new PersistenceException("Wrong configutation file " + fileName );
		}
		return prop;
	}

}
