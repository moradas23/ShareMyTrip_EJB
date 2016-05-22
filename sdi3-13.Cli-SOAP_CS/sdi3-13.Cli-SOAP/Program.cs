using sdi3_13.Cli_SOAP.Actions;
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
            int opcion;

            do
            {
                Console.WriteLine("\n-> Opción 1: Listar usuarios");
                Console.WriteLine("-> Opción 2: Deshabilitar Usuario");
                Console.WriteLine("-> Opción 3: Listado Comentarios y Puntuaciones");
                Console.WriteLine("-> Opción 4: Eliminar Comentarios y Puntuaciones");
                Console.WriteLine("-> Para salir introduzca 0\n");

                Console.Write("Introduzca una opción:");
                opcion = int.Parse(Console.ReadLine());

                switch (opcion)
                {
                    case 1:
                        mostrarUsuarios();
                        break;

                    case 2:
                        deshabilitarUsuario();
                        break;

                    case 3:
                        listarComentarios();
                        break;

                    case 4:
                        eliminarComentarios();
                        break;
                        
                   
                }
        
            }
            while (opcion > 0);
        }

        private static void mostrarUsuarios()
        {
            MostrarUsuarios mU = new MostrarUsuarios();
            mU.mostrarUsuario();

        }

        private static void deshabilitarUsuario()
        {
            DeshabilitarUsuario dU = new DeshabilitarUsuario();
            dU.deshabilitarUsuario();
        }


        private static void listarComentarios()
        {
            ListarComentarios lC = new ListarComentarios();
            lC.listarComentarios();
        }

        private static void eliminarComentarios()
        {
            EliminarComentarios eC = new EliminarComentarios();
            eC.eliminarComentarios();
        }
    }
}
