package com.sdi.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;

public class LogConfig {

	public static void config() {
		String propertiesFile = "s:/work/logging.properties";
		
		try {
			InputStream configFile = new FileInputStream(propertiesFile);
			LogManager.getLogManager().readConfiguration(configFile);
			configFile.close();
			
		} catch (IOException ex) {
			System.out.println("WARNING: Could not open configuration file");
			System.out.println("WARNING: Logging not configured (console output only)");
		}
	}

}
