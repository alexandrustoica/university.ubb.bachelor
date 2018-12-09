using System.Net.Sockets;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Formatters.Binary;
using NetworkComponent.Handler;
using NetworkComponent.Request;
using NetworkComponent.Response;
using NetworkComponent.Sender;
using NetworkComponent.Subscribe;

namespace NetworkComponent.Listener
{
    public class RequestListener: IRequestListener
    {
        private readonly NetworkStream _stream;
        private bool _isConnected;
        private readonly IFormatter _formatter;
        private readonly IRequestHandler _handler;
        private readonly IResponseSender _sender;

        public RequestListener(NetworkStream stream)
        {
            _stream = stream;
            _isConnected = true;
            _formatter = new BinaryFormatter();
            _handler = new RequestHandler();
            _sender = new ResponseSender(_stream, _formatter);
        }

        public void Start()
        {
            while (_isConnected)
            {
                Listen();
            }
        }

        public void Stop()
        {
            _isConnected = false;
        }

        public void Subscribe(ISubscriber subscriber)
        {
            _handler.Subscribe(subscriber);
        }
        
        public void Listen()
        {
            var request = (IRequest)_formatter.Deserialize(_stream);
            var response = _handler.Handle(request);
            if (response != null)
            {
                _sender.Send(response);
            }
        }

        public void Send(IResponse response)
        {
            _sender.Send(response);
        }
    }
}