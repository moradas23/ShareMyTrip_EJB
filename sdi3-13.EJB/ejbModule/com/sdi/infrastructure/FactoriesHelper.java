package com.sdi.infrastructure;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FactoriesHelper {
	/**
	 * Devuelve instancia de la clase factory deseada. Crea un objeto a partir
	 * del nombre de la clase
	 * 
	 * @param file
	 *            El fichero de propiedades
	 * @param factoryType
	 *            El nombre de la propiedad en el fichero de proerties
	 * @return El objeto de la clase factoria adecuada
	 */
	static Object createFactory(String file, String factoryType) {
		String className = loadProperty(file, factoryType);
		try {
			Class<?> clazz = Class.forName(className);
			return clazz.newInstance();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Carga un propiedad desde fichero de propiedades Lanza runtime exception
	 * si no existe la propiedad o el fichero
	 * 
	 * @param file
	 * @param property
	 * @return
	 */
	static String loadProperty(String file, String property) {
		Properties p = loadPropertiesFile(file);
		String value = p.getProperty(property);
		if (value == null) {
			throw new RuntimeException("Property not found in " + file);
		}
		return value;
	}

	private static Properties loadPropertiesFile(String file) {
		Properties p = new Properties();
		try {
			InputStream is = Factories.class.getResourceAsStream(file);
			p.load(is);
			is.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return p;
	}
}