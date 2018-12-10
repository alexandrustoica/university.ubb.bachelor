using System;
using System.Collections.Generic;
using System.Net;
using System.Net.Sockets;
using System.Threading;
using NetworkComponent.Listener;
using NetworkComponent.Response;
using NetworkComponent.Transferable;

namespace NetworkComponent.Connection
{
    public class ServerConnectionManager: IServerConnection
    {
        /// <summary>
        /// The port used by the server's system.
        /// </summary>
        private readonly int _port;

        /// <summary>
        /// The host (localhost) used by the server's system.
        /// </summary>
        private readonly string _host;

        /// <summary>
        /// Checks if the server is running.
        /// </summary>
        private bool _isRunning;

        /// <summary>
        /// The list of clients what are currently connected to the server.
        /// </summary>
        public List<IRequestListener> Clients;
        
        /// <summary>
        /// Manages the server's connection to it's clients.
        /// </summary>
        /// <param name="host"> The server's port </param>
        /// <param name="port"> The server's host (localhost) </param>
        public ServerConnectionManager(string host, int port)
        {
            _host = host;
            _port = port;
            Clients = new List<IRequestListener>();
        }

        /// <summary>
        /// Starts the server's activity by waiting & connecting to it's clients. 
        /// </summary>
        public void Start()
        {
            _isRunning = true;
            Console.WriteLine("Connection Starts with port: " + _port);
            var address = IPAddress.Parse(_host);
            var endPoint = new IPEndPoint(address, _port);
            var server = new TcpListener(endPoint);
            server.Start();
            while (_isRunning)
            {
                Console.WriteLine("Waiting For Clients ...");
                var tcpClient = server.AcceptTcpClient();
                Console.WriteLine("Client Connected ...");
                ProcessClient(tcpClient.GetStream());
            }
        }
        /// <summary>
        /// Runs the client's task in a new thread. 
        /// (listens for client's requests)
        /// </summary>
        /// <param name="stream"> The system's socket </param>
        private void ProcessClient(NetworkStream stream)
        {
            var client = CreateThread(stream);
            client.Start();
        }

        private Thread CreateThread(NetworkStream stream)
        {
            IRequestListener listener = new RequestListener(stream);
            listener.Subscribe(this);
            Clients.Add(listener);
            return new Thread(listener.Start);
        }

        public void Stop()
        {
            Console.Write("Connection Ends with port: " + _port);
            _isRunning = false;
        }

        public void Update(ITransferable notification)
        {
            foreach (var client in Clients) 
            {
                client.Send((IResponse)notification);
            }
        }
    }
}
