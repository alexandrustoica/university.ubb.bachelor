using NetworkComponent.Subscribe;

namespace NetworkComponent.Connection
{
    public interface IServerConnection:
        IConnection,
        ISubscriber
    {
        
    }
}