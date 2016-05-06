package com.sdi.ui;

import com.sdi.ui.action.DeshabilitarUsuarioAction;
import com.sdi.ui.action.EliminarComentariosPuntuacionesAction;
import com.sdi.ui.action.ListarComentariosPuntuacionesAction;
import com.sdi.ui.action.ListarUsuariosAction;
import alb.util.menu.BaseMenu;

public class MainMenu extends BaseMenu {
	public MainMenu() {
		menuOptions = new Object[][] {
				{ "Tareas Administrativas", null },
				{ "Listado Usuarios", ListarUsuariosAction.class },
				{ "Deshabilitar Usuario", DeshabilitarUsuarioAction.class },
				{ "Listado Comentarios y Puntuaciones",
						ListarComentariosPuntuacionesAction.class },
				{ "Eliminar Comentarios y Puntuaciones",
						EliminarComentariosPuntuacionesAction.class }, };
	}

}
