using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace sdi3_13.Cli_SOAP.Actions
{
    class DeshabilitarUsuario
    {

        public void deshabilitarUsuario()
        {


            Console.Write("Login del usuario: ");
            String login = Console.ReadLine();

            EjbUserServiceService service = new EjbUserServiceService();
            EjbSeatServiceService seatService = new EjbSeatServiceService();

            user usuario = service.findByLogin(login);

            if (usuario != null)
            {
                if (usuario.status.Equals(userStatus.ACTIVE))
                {
                    service.unsubscribe(login);

                    seatService.excluirUsuario(login);
                    Console.WriteLine("Usuario deshabilitado");

                }
                else
                {
                    Console.WriteLine("El usuario con login '" + login
                            + "' ya está deshabilitado");
                }
            }
            else
            {
                Console.WriteLine("No existe ningún usuario con ese login");
            }


        }



    }
}
