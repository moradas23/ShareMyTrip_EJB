
using Json;
using Newtonsoft.Json;
using sdi3_13.Cli_REST_CSharp.HttpUtils;
using System;
using System.Collections.Generic;
using System.IO;
using System.Net;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Text;
using System.Web.Script.Serialization;

namespace sdi3_13.Cli_REST_CSharp
{
    class Program
    {


        private static readonly String REST_TRIP_SERVICE_URL = "http://localhost:8280"
            + "/sdi3-13.Web/rest/TripsServiceRs";

        private static readonly String REST_USER_SERVICE_URL = "http://localhost:8280"
            + "/sdi3-13.Web/rest/UserServiceRs";

        private static readonly String REST_APPLICATION_SERVICE_URL = "http://localhost:8280"
            + "/sdi3-13.Web/rest/ApplicationServiceRs";

        private static readonly String REST_SEAT_SERVICE_URL = "http://localhost:8280"
            + "/sdi3-13.Web/rest/SeatServiceRs";

        private static String login;
        private static String password;

        static void Main(string[] args)
        {
            //Obtenemos el login y contraseña
            obtenerCredenciales();

            //Obtenemos usuario
            User usuario = getUserByLogin();

            //Si el usuario es null las credenciales son erroneas
            if (usuario == null)
            {
                Console.WriteLine("Usuario y/o contraseña erroneos");
                return;
            }

            List<Trip> viajes = getTripsPromoted(usuario.getId());

            if (viajes != null && viajes.Count > 0)
            {
                Console.WriteLine("\nViajes abiertos como promotor:");
          //      mostrarViajes(viajes);

            }
            else
            {
                Console.WriteLine("No tiene ningún viaje abierto como promotor"
                        + " con plazas disponibles");
                return;
            }

            Console.WriteLine("ID del viaje");
            int idViaje = int.Parse(Console.ReadLine());

            /*  List<Application> solicitantes = getSolicitantesViaje(idViaje);

              if (solicitantes.size() > 0)
              {
                  System.out.println("\nSolicitantes del viaje:");

                  mostrarSolicitantes(solicitantes);

              }
              else
              {
                  System.out.println("Este viaje no tiene ninguna solicitud de participación");
                  return;
              }

              Long idConfirmado = Console.readLong("Inserte ID del usuario a confirmar");

              for (Application app:solicitantes)
              {
                  if (app.getUserId().equals(idConfirmado))
                  {

                      aceptarSolicitud(idViaje, idConfirmado);
                      return;
                  }
              }

              System.out.println("ID no valido");
              */
        }





        /**
         * Pide por pantalla las credenciales del usuario
         * 
         */
        private static void obtenerCredenciales()
        {
            Console.WriteLine("Login:");
            login = Console.ReadLine();
            Console.WriteLine("Password:");
            password = Console.ReadLine();
        }






        /*Invocaciones a métodos REST. 
        Antes de procesar la petición esta pasará por 
        un filtro de servlet en el que se comprobará si las credenciales son correctas.
        En el caso de aquellas invocaciones que devuelven objetos se producirá una 
        excepción del tipo 'ProcessingException' que deberemos controlar*/

        private static List<Trip> getTripsPromoted(long id)
        {


              var client = new RestClient(REST_TRIP_SERVICE_URL);
              var json = client.MakeRequest("/promotor/" + id.ToString());

              JavaScriptSerializer json_serializer = new JavaScriptSerializer();
              List<Trip> viajes =
                     (List<Trip>)json_serializer.DeserializeObject(json);

             
/*
            WebRequest req = WebRequest.Create(@REST_TRIP_SERVICE_URL);
            req.Method = "GET";
            req.Headers["Authorization"] = "Basic " + Convert.ToBase64String(Encoding.Default.GetBytes("user1:user1"));
            //req.Credentials = new NetworkCredential("username", "password");
            HttpWebResponse resp = req.GetResponse() as HttpWebResponse;
           */

            return viajes;

            /*
                        List<Trip> lista = null;

                        HttpClient client = new HttpClient();
                        client.BaseAddress = new Uri(REST_TRIP_SERVICE_URL);

                        // Add an Accept header for JSON format.
                        client.DefaultRequestHeaders.Accept.Add(
                        new MediaTypeWithQualityHeaderValue("application/json"));

                        // List data response.
                        HttpResponseMessage response = client.GetAsync("/promotor/" + id.ToString()).Result;  
                        if (response.IsSuccessStatusCode)
                        {
                            List<Trip> lista = await response.Content.ReadAsAsync > List<Trip> > ();
                        }

                        return lista;*/
        }

        private static User getUserByLogin()
        {
            /*
            var client = new RestClient(@"http://localhost:8280/sdi3-13.Web/rest/UserServiceRs");
            var json = client.MakeRequest("/login/" + login);

            JavaScriptSerializer json_serializer = new JavaScriptSerializer();
            User usuario =
                   (User)json_serializer.DeserializeObject(json);
             */      

           WebRequest req = WebRequest.Create(@"http://localhost:8280/sdi3-13.Web/rest/UserServiceRs/login/"+login);
            req.Method = "GET";
            req.Headers["Authorization"] = "Basic " + Convert.ToBase64String(Encoding.Default.GetBytes("user1:user1"));
            //req.Credentials = new NetworkCredential("username", "password");
            HttpWebResponse resp = req.GetResponse() as HttpWebResponse;

            Stream receiveStream = resp.GetResponseStream();
            StreamReader readStream = new StreamReader(receiveStream, Encoding.UTF8);
            var json = readStream.ReadToEnd();

             var usuario = JsonConvert.DeserializeObject<User>(json);


           // IDictionary<string,object> dic = JsonParser.FromJson(json);



           // User usuario = new User(json);

            return usuario;

        }



    }
}
