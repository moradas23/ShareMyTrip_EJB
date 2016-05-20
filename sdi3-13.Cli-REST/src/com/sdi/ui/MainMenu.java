package com.sdi.ui;

import com.sdi.ui.action.ConfirmarPasajerosAction;
import alb.util.menu.BaseMenu;

public class MainMenu extends BaseMenu {
	public MainMenu() {
		menuOptions = new Object[][] {
				{ "Tareas Administrativas-REST", null },
				{ "Confirmar Pasajeros", ConfirmarPasajerosAction.class }, };
	}

}
