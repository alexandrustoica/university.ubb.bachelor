using System.Net.Sockets;
using System.Runtime.Serialization;
using NetworkComponent.Subscribe;
using NetworkComponent.Transferable;

namespace NetworkComponent.Listener
{
    public class ResponseListener: IResponseListener
    {
        private readonly NetworkStream _stream;
        private readonly IFormatter _formatter;
        private bool _isRunning;
        private ISubscriber _subscriber;
        public ResponseListener(NetworkStream stream, IFormatter formatter)
        {
            _stream = stream;
            _formatter = formatter;
        }

        public void Start()
        {
            _isRunning = true;
            while (_isRunning)
            {
                Listen();
            }
        }

        public void Stop()
        {
            _isRunning = false;
        }

        public void Subscribe(ISubscriber subscriber)
        {
            _subscriber = subscriber;
        }

        public void Listen()
        {
            var response = _formatter.Deserialize(_stream);
            _subscriber.Update((ITransferable) response);
        }
    }
}