using System;
using NetworkComponent.Connection;
using NetworkComponent.Transmission;

namespace ServerComponent
{
    public class Program
    {
        private const int Port = 55555;
        private const string Host = "127.0.0.1";

        public static void Main(string[] args)
        {
            IServerConnection connection = new ServerConnectionManager(Host, Port);
            IServerTransmission transmission = new ServerTransmissionManager(connection);
            transmission.Start();
            Console.Read();
        }
    }
}
