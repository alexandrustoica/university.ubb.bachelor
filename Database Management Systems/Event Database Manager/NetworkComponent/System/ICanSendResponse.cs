using NetworkComponent.Response;

namespace NetworkComponent.System
{
    public interface ICanSendResponse
    {
        void Send(IResponse response);
    }
}