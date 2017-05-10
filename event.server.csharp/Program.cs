using System;
using EventManager.Service;
using Thrift.Server;
using Thrift.Transport;

namespace @event.server.csharp
{
    public class Program
    {
        public static void Main(string[] args)
        {
            try
            {
                var eventServer = new ConnectionManager();
                var processor = new Subsciption.Processor(eventServer);
                TServerTransport transport = new TServerSocket(9091);
                TServer server = new TThreadedServer(processor, transport);
                Console.WriteLine("Starting the server ...");
                server.Serve();
                Console.Read();
            }
            catch (Exception exception)
            {
                Console.WriteLine(exception.Message);
                Console.Read();
            }
            Console.WriteLine("Done");
        }
    }
}
