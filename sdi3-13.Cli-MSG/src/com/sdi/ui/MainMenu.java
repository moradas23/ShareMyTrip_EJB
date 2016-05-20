package com.sdi.ui;

import alb.util.menu.BaseMenu;

import com.sdi.ui.action.ObtenerViajesAction;

public class MainMenu extends BaseMenu {
	public MainMenu() {
		menuOptions = new Object[][] {	{ 
			"Cliente de mensajeria", null },
				{ "Iniciar Sesión", ObtenerViajesAction.class }
			,};
	}

}
