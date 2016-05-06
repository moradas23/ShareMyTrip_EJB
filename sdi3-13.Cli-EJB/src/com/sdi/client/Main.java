package com.sdi.client;

import com.sdi.config.LogConfig;
import com.sdi.ui.MainMenu;


public class Main {
	
	public static void main(String[] args) throws Exception {
		LogConfig.config();
		Main.run();
	}

	private static void run() throws Exception {
		new MainMenu().execute();
	}

}
