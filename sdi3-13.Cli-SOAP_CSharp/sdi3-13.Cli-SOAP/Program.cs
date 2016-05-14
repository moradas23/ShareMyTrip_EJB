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

            foreach (user usuario in usuarios)
            {
                Console.WriteLine(datosUsuario(usuario));
            }

            Console.Write("Pulse un botón para terminar");
            Console.Read();
         
        }

        public static String datosUsuario(user usuario)
        {
            return "ID: "+usuario.id+", Login: "+usuario.login+", Nombre: " + usuario.name
                +", Apellido: "+usuario.surname+", Email: "+usuario.email+", Estado: "+usuario.status;

        }

    }
}
