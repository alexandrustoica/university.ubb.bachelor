using NetworkComponent.Service;
using NetworkComponent.Subscribe;
using NetworkComponent.System;

namespace NetworkComponent.Transmission
{
    public interface IClientTransmission:
        ITransmission, 
        ISubscription, 
        IConnectionObserver,
        IClientService
    {
        
    }
}