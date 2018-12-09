using System.Net.Sockets;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Formatters.Binary;
using System.Threading;
using NetworkComponent.Handler;
using NetworkComponent.Listener;
using NetworkComponent.Request;
using NetworkComponent.Response;
using NetworkComponent.Subscribe;
using NetworkComponent.Transferable;

namespace NetworkComponent.Sender
{
    public class RequestSender: IRequestSender
    {
        private readonly IResponseListener _listener;
        private readonly IResponseHandler _handler;
        private readonly IFormatter _formatter;
        private readonly NetworkStream _stream;

        public RequestSender(string host, int port)
        {
            var client = new TcpClient(host, port);
            _stream = client.GetStream();
            _formatter = new BinaryFormatter();

            _handler = new ResponseHandler();
            _listener = new ResponseListener(_stream, _formatter);
            _listener.Subscribe(this);
        }

        public void Start()
        {
            var thread = new Thread(_listener.Start);
            thread.Start();
        }

        public void Stop()
        {
            _listener.Stop();
        }

        public void Send(IRequest request)
        {
            _formatter.Serialize(_stream, request);
            _stream.Flush();
        }

        public void Subscribe(ISubscriber subscriber)
        {
            _handler.Subscribe(subscriber);
        }

        public void Update(ITransferable notification)
        {
            _handler.Handle((IResponse) notification);
        }
    }
}