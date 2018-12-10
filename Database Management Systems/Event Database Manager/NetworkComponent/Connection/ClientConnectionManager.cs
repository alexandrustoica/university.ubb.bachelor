using NetworkComponent.Request;
using NetworkComponent.Sender;
using NetworkComponent.Subscribe;

namespace NetworkComponent.Connection
{
    public class ClientConnectionManager: IClientConnection
    {
        private readonly IRequestSender _sender;

        public ClientConnectionManager(string host, int port)
        {   
            _sender = new RequestSender(host, port);
        }

        public void Start()
        {
            _sender.Start();
        }

        public void Stop()
        {
            _sender.Stop();
        }

        public void Send(IRequest request)
        {
            _sender.Send(request);
        }

        public void Subscribe(ISubscriber subscriber)
        {
            _sender.Subscribe(subscriber);
        }
    }
}