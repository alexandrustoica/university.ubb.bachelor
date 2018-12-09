using NetworkComponent.Request;

namespace NetworkComponent.System
{
    public interface ICanSendRequest
    {
        void Send(IRequest request);
    }
}