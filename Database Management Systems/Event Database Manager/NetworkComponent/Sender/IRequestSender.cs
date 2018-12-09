using NetworkComponent.Subscribe;
using NetworkComponent.System;

namespace NetworkComponent.Sender
{
    public interface IRequestSender: 
        IStartable,
        IStoppable, 
        ISubscription,
        ISubscriber,
        ICanSendRequest
    {
        
    }
}