using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace sdi3_13.Cli_SOAP.Actions
{
    class EliminarComentarios
    {
        public void eliminarComentarios()
        {
            EjbRatingServiceService rService = new EjbRatingServiceService();

            Console.Write("Introduzca el ID del usuario: ");
            long id = long.Parse(Console.ReadLine());

            rating[] comentarios = rService.findByAboutUser(id,true);

            if (comentarios.Length == 0)
            {
                Console.WriteLine("Este usuario no tiene ningún comentario");
            }
            else
            {
                int contador = 1;
                foreach (rating comen in comentarios)
                {
                    Console.WriteLine("Comentario nº" + contador + ": "
                            + comen.comment + " - Puntuación: "
                            + comen.value);
                }

                Console.Write("Seleccione el número del comentario a borrar: ");
                int numero = int.Parse(Console.ReadLine());

                if (numero <= comentarios.Length && numero > 0)
                {
                    rService.delete(comentarios[numero - 1].id,true);
                    Console.WriteLine("Se ha eliminado correctamente el comentario");
                }
                else
                {
                    Console.WriteLine("Valor introducido no válido");
                }



            }
        }


    }
}
