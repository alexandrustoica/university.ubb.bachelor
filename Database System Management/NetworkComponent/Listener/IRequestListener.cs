using NetworkComponent.Subscribe;
using NetworkComponent.System;

namespace NetworkComponent.Listener
{
    public interface IRequestListener: 
        IStartable, 
        IStoppable,
        ISubscription, 
        ICanSendResponse, 
        ICanListen
    {
        
    }
}