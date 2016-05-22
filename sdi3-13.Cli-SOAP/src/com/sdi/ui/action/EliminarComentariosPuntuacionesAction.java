package com.sdi.ui.action;

import java.util.List;

import com.sdi.ws.rating.EjbRatingServiceService;
import com.sdi.ws.rating.Rating;
import com.sdi.ws.rating.RatingService;

import alb.util.console.Console;
import alb.util.menu.Action;

public class EliminarComentariosPuntuacionesAction implements Action {

	@Override
	public void execute() throws Exception {
		RatingService rService = new EjbRatingServiceService()
				.getRatingServicePort();

		Long id = Console.readLong("Introduzca el ID del usuario");

		List<Rating> comentarios = rService.findByAboutUser(id);

		if (comentarios.size() == 0) {
			System.out.println("Este usuario no tiene ningún comentario");
		} else {
			int contador = 1;
			for (Rating comen : comentarios) {
				System.out.println("Comentario nº" + contador + ": "
						+ comen.getComment() + " - Puntuación: "
						+ comen.getValue());
				contador++;
			}

				Integer numero = Console
						.readInt("Seleccione el número del comentario a borrar");

				if (numero!=null && numero <= comentarios.size() && numero > 0) {
					rService.delete(comentarios.get(numero - 1).getId());
					System.out
							.println("Se ha eliminado correctamente el comentario");
				}
				else{
					System.out.println("Valor introducido no válido");
				}
				
			

		}

	}

}
