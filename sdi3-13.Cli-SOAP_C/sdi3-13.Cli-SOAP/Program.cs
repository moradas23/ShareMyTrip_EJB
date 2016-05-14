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

            EjbUserServiceService userService = new EjbUserServiceService();

            user[] usuarios = userService.getUsers();


            foreach (user usuario in usuarios)
            {
                Console.WriteLine(usuario.name);
            }

            Console.Write("Pulse un botón para terminar");
            Console.Read();
            


        }
    }
}
