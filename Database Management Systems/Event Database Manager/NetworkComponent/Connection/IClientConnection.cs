using NetworkComponent.Subscribe;
using NetworkComponent.System;

namespace NetworkComponent.Connection
{
    public interface IClientConnection: 
        IConnection, 
        ICanSendRequest, 
        ISubscription
    {
        
    }
}