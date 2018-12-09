using System.Net.Sockets;
using System.Runtime.Serialization;
using NetworkComponent.Response;

namespace NetworkComponent.Sender
{
    public class ResponseSender: IResponseSender
    {

        private readonly NetworkStream _stream;
        private readonly IFormatter _formatter;

        public ResponseSender(NetworkStream stream, IFormatter formatter)
        {
            _stream = stream;
            _formatter = formatter;
        }

        public void Send(IResponse response)
        {
            _formatter.Serialize(_stream, response);
            _stream.Flush();
        }
    }
}