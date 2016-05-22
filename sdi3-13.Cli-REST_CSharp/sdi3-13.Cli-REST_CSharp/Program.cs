
using Json;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Text;
using System.Web.Script.Serialization;

namespace sdi3_13.Cli_REST_CSharp
{
    class Program
    {
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
                mostrarViajes(viajes);

            }
            else
            {
                Console.WriteLine("No tiene ningún viaje abierto como promotor"
                        + " con plazas disponibles");
                return;
            }

            Console.Write("ID del viaje:");
            int idViaje = int.Parse(Console.ReadLine());

          List<Application> solicitantes = getSolicitantesViaje(idViaje);
            
              if (solicitantes.Count > 0)
              {
                Console.WriteLine("\nSolicitantes del viaje:");

                  mostrarSolicitantes(solicitantes);

              }
              else
              {
                Console.WriteLine("Este viaje no tiene ninguna solicitud de participación");
                  return;
              }
             
              Console.Write("Inserte ID del usuario a confirmar:");
                        long idConfirmado = long.Parse(Console.ReadLine());

                        foreach (Application app in solicitantes)
                        {
                            if (app.getUserId().Equals(idConfirmado))
                            {

                                aceptarSolicitud(idViaje, idConfirmado);
                                return;
                            }
                        }

                        Console.WriteLine("ID no valido");



            Console.WriteLine("Pulse cualquier tecla para salir");
            Console.ReadLine();

        }


        /**
         * Muestra los solicitantes por pantalla
        * 
        * @param solicitantes
         */
        private static void mostrarSolicitantes(List<Application> solicitantes)
        {
            foreach (Application app in solicitantes)
            {
                mostrarUsuario(getUserById(app.getUserId()));

            }
        }


        /**
 * Muestra el usuario pasado como parámetro por pantalla
 * 
 * @param user
 */
        private static void mostrarUsuario(User user)
        {
            Console.WriteLine("\n---ID del Usuario: " + user.getId()
                    + "-------------");
            Console.WriteLine("Login: " + user.getLogin());
            Console.WriteLine("Nombre: " + user.getName());
            Console.WriteLine("Apellidos: " + user.getSurname());
            Console.WriteLine("Email: " + user.getEmail() + "\n");

        }



        /**
 * Borra la solicitud de un pasajero, lo inserta en una plaza y disminuye
 * una plaza disponible en el viaje
 * 
 * @param idViaje
 * @param idConfirmado
 */
        private static void aceptarSolicitud(int idViaje,long idConfirmado)
        { 
            // Borrar solicitud
            borrarSolicitud(idConfirmado, Convert.ToInt64(idViaje));

            // Insertamos el asiento
            Seat seat = new Seat();
            seat.setUserId(idConfirmado);
            seat.setTripId((long)idViaje);
            seat.setStatus(SeatStatus.ACCEPTED);

            insertarAsiento(seat);

            // Actualizamos viajes
            Trip viaje = obtenerViaje(Convert.ToInt64(idViaje));
            viaje.setAvailablePax(viaje.getAvailablePax() - 1);
            disminuirPlaza(viaje);

            Console.WriteLine("El pasajero ha sido confirmado satisfactoriamente");
     }




        /**
         * Pide por pantalla las credenciales del usuario
         * 
         */
        private static void obtenerCredenciales()
        {
            Console.Write("Login:");
            login = Console.ReadLine();
            Console.Write("Password:");
            password = Console.ReadLine();
        }


        /**
 * Muestra por pantalla los viajes pasados como parámetro
 * 
 * @param viajes
 */
        private static void mostrarViajes(List<Trip> viajes)
        {
            foreach (Trip viaje in viajes)
            {
                Console.WriteLine("\n---ID del Viaje: " + viaje.getId()
                        + "-------------");
                Console.WriteLine("Ciudad Salida: "
                        + viaje.getDeparture().getCity());
                Console.WriteLine("Ciudad Destino: "
                        + viaje.getDestination().getCity());
                Console.WriteLine("Fecha Salida: " + viaje.getDepartureDate());
                Console.WriteLine("Fecha llegada: " + viaje.getArrivalDate()
                        + "\n");
            }
        }




        /*Invocaciones a métodos REST. 
        Antes de procesar la petición esta pasará por 
        un filtro de servlet en el que se comprobará si las credenciales son correctas.
        En el caso de aquellas invocaciones que devuelven objetos se producirá una 
        excepción del tipo 'ProcessingException' que deberemos controlar*/

        private static List<Trip> getTripsPromoted(long id)
        {

            WebRequest req = WebRequest.Create(@"http://localhost:8280/sdi3-13.Web/rest/TripsServiceRs/promotor/" + id.ToString());
            req.Method = "GET";
            req.Headers["Authorization"] = "Basic " + Convert.ToBase64String(Encoding.Default.GetBytes(login + ":" + password));
            HttpWebResponse resp = req.GetResponse() as HttpWebResponse;

            Stream receiveStream = resp.GetResponseStream();
            StreamReader readStream = new StreamReader(receiveStream, Encoding.UTF8);
            var json = readStream.ReadToEnd();

            JavaScriptSerializer serializer = new JavaScriptSerializer();
            List<Trip> viajes = serializer.Deserialize<List<Trip>>(json);

            return viajes;
        }

        private static User getUserByLogin()
        {

           WebRequest req = WebRequest.Create(@"http://localhost:8280/sdi3-13.Web/rest/UserServiceRs/login/"+login);
            req.Method = "GET";
            req.Headers["Authorization"] = "Basic " + Convert.ToBase64String(Encoding.Default.GetBytes(login+":"+password));
            HttpWebResponse resp = req.GetResponse() as HttpWebResponse;

            Stream receiveStream = resp.GetResponseStream();
            StreamReader readStream = new StreamReader(receiveStream, Encoding.UTF8);
            var json = readStream.ReadToEnd();

            JavaScriptSerializer serializer = new JavaScriptSerializer();
            User usuario = serializer.Deserialize<User>(json);

            return usuario;

        }


        private static User getUserById(long id)
        {
            WebRequest req = WebRequest.Create(@"http://localhost:8280/sdi3-13.Web/rest/UserServiceRs/id/" + id);
            req.Method = "GET";
            req.Headers["Authorization"] = "Basic " + Convert.ToBase64String(Encoding.Default.GetBytes(login + ":" + password));
            HttpWebResponse resp = req.GetResponse() as HttpWebResponse;

            Stream receiveStream = resp.GetResponseStream();
            StreamReader readStream = new StreamReader(receiveStream, Encoding.UTF8);
            var json = readStream.ReadToEnd();

            JavaScriptSerializer serializer = new JavaScriptSerializer();
            User usuario = serializer.Deserialize<User>(json);

            return usuario;
	}


        private static List<Application> getSolicitantesViaje(int idViaje)
        {

            WebRequest req = WebRequest.Create(@"http://localhost:8280/sdi3-13.Web/rest/ApplicationServiceRs/" + idViaje.ToString());
            req.Method = "GET";
            req.Headers["Authorization"] = "Basic " + Convert.ToBase64String(Encoding.Default.GetBytes(login + ":" + password));
            HttpWebResponse resp = req.GetResponse() as HttpWebResponse;

            Stream receiveStream = resp.GetResponseStream();
            StreamReader readStream = new StreamReader(receiveStream, Encoding.UTF8);
            var json = readStream.ReadToEnd();

            JavaScriptSerializer serializer = new JavaScriptSerializer();
            List<Application> solicitudes = serializer.Deserialize<List<Application>>(json);

            return solicitudes;
        }

    
        private static void insertarAsiento(Seat seat)
        {

            WebRequest req = WebRequest.Create(@"http://localhost:8280/sdi3-13.Web/rest/SeatServiceRs/");
            req.Method = "PUT";
            req.Headers["Authorization"] = "Basic " + Convert.ToBase64String(Encoding.Default.GetBytes(login + ":" + password));
           
            req.ContentType = "application/json";

            using (var streamWriter = new StreamWriter(req.GetRequestStream()))
            {
                JavaScriptSerializer serializer = new JavaScriptSerializer();
                streamWriter.Write(serializer.Serialize(seat));
                streamWriter.Flush();
                streamWriter.Close();
            }

            HttpWebResponse resp = req.GetResponse() as HttpWebResponse;


            HttpWebResponse response = (HttpWebResponse)req.GetResponse();
            string returnString = response.StatusCode.ToString();
        }


        private static void borrarSolicitud(long idSolicitante, long idViaje)
        {
            WebRequest req = WebRequest.Create(@"http://localhost:8280/sdi3-13.Web/rest/ApplicationServiceRs/"+idSolicitante.ToString()+"/"+idViaje.ToString());
            req.Method = "DELETE";
            req.Headers["Authorization"] = "Basic " + Convert.ToBase64String(Encoding.Default.GetBytes(login + ":" + password));
            req.ContentType = "application/json";

            HttpWebResponse resp = req.GetResponse() as HttpWebResponse;

         //   HttpWebResponse response = (HttpWebResponse)req.GetResponse();
          //  string returnString = response.StatusCode.ToString();
        }

        private static void disminuirPlaza(Trip viaje)
        {

            WebRequest req = WebRequest.Create(@"http://localhost:8280/sdi3-13.Web/rest/TripsServiceRs/");
            req.Method = "POST";
            req.Headers["Authorization"] = "Basic " + Convert.ToBase64String(Encoding.Default.GetBytes(login + ":" + password));

            req.ContentType = "application/json";

            using (var streamWriter = new StreamWriter(req.GetRequestStream()))
            {
                JavaScriptSerializer serializer = new JavaScriptSerializer();
                streamWriter.Write(serializer.Serialize(viaje));
                streamWriter.Flush();
                streamWriter.Close();
            }

            HttpWebResponse resp = req.GetResponse() as HttpWebResponse;


         //   HttpWebResponse response = (HttpWebResponse)req.GetResponse();
        //    string returnString = response.StatusCode.ToString();
        }

        private static Trip obtenerViaje(long idViaje)
        {
            WebRequest req = WebRequest.Create(@"http://localhost:8280/sdi3-13.Web/rest/TripsServiceRs/viaje/" + idViaje.ToString());
            req.Method = "GET";
            req.Headers["Authorization"] = "Basic " + Convert.ToBase64String(Encoding.Default.GetBytes(login + ":" + password));

            HttpWebResponse resp = req.GetResponse() as HttpWebResponse;

            Stream receiveStream = resp.GetResponseStream();
            StreamReader readStream = new StreamReader(receiveStream, Encoding.UTF8);
            var json = readStream.ReadToEnd();

            JavaScriptSerializer serializer = new JavaScriptSerializer();
            Trip viaje = serializer.Deserialize<Trip>(json);

            

            return viaje;
	}

   

    }
}
