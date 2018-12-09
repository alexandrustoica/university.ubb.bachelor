using NetworkComponent.Subscribe;
using NetworkComponent.System;

namespace NetworkComponent.Listener
{
    public interface IResponseListener:
        IStartable,
        IStoppable,
        ISubscription,
        ICanListen
    {
        
    }
}