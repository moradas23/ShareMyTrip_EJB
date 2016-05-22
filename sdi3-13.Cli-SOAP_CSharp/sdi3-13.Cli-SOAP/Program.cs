using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace sdi3_13.Cli_SOAP
{
    class Program
    {
        static void Main(string[] args)
        {

            Console.WriteLine("Usuarios del sistema: ");

            EjbUserServiceService userService = new EjbUserServiceService();

            user[] usuarios = userService.getUsers();

            mostrarUsuario(usuarios);

            Console.Write("Pulse un botón para terminar");
            Console.Read();
         
        }

        private static void mostrarUsuario(user[] usuarios)
        {

            EjbTripServiceService tService = new EjbTripServiceService();

            foreach (user usuario in usuarios)
            {
                Console.WriteLine("\n---- Id: " + usuario.id + " -----------------------");
                Console.WriteLine("Login: " + usuario.login);
                Console.WriteLine("Contraseña(Encriptada): " + usuario.password);
                Console.WriteLine("Nombre: " + usuario.name);
                Console.WriteLine("Apellidos: " + usuario.surname);
                Console.WriteLine("Email: " + usuario.email);
                Console.WriteLine("Estado de la cuenta: " + usuario.status);

                trip[] viajesPromotor = tService.findByPromoterAndDone(usuario.id,true);

                if(viajesPromotor!=null)
                    Console.WriteLine("Viajes como promotor: " + viajesPromotor.Length);
                else
                    Console.WriteLine("No fue promotor en ningún viaje");

                trip[] viajesParticipo = tService.findViajesHaParticipado(usuario.id,true);

                if (viajesParticipo != null)
                    Console.WriteLine("Viajes ha participado: " + viajesParticipo.Length);
                else
                    Console.WriteLine("No participó en ningún viaje");

                Console.WriteLine();
            }
        }

    }
}
