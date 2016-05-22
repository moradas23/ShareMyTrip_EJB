using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace sdi3_13.Cli_SOAP.Actions
{
    class ListarComentarios
    {
        public void listarComentarios()
        {
            EjbRatingServiceService rService = new EjbRatingServiceService();
            EjbTripServiceService tService = new EjbTripServiceService();
            EjbUserServiceService uService = new EjbUserServiceService();

            rating[] comentarios = rService.findLastMonth();
            if (comentarios != null)
                foreach (rating comentario in comentarios)
                {
                    long id = comentario.seatAboutTripId;
                    trip viaje = tService.findByIdTrip(id, true);

                    user usuario1 = uService.findById(comentario.seatFromUserId, true);
                    user usuario2 = uService.findById(comentario.seatAboutUserId, true);

                    Console.WriteLine("---Destino: " + viaje.cityDestination + "------------------------");
                    Console.WriteLine();
                    Console.WriteLine("Usuario hizo comentario: ");

                    Console.WriteLine("\t ID: " + usuario1.id);
                    Console.WriteLine("\t Login: " + usuario1.login);
                    Console.WriteLine("\t Nombre: " + usuario1.name);

                    Console.WriteLine("Usuario recibe comentario: ");
                    Console.WriteLine("\t ID: " + usuario2.id);
                    Console.WriteLine("\t Login: " + usuario2.login);
                    Console.WriteLine("\t Nombre: " + usuario2.name);

                    Console.WriteLine("Valoración: " + comentario.value);
                    Console.WriteLine("Comentario: " + comentario.comment);

                    Console.WriteLine();
                }

        }
    }
}
