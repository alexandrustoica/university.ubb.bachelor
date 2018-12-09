using NetworkComponent.Request;
using NetworkComponent.Response;
using NetworkComponent.Subscribe;

namespace NetworkComponent.Handler
{
    public interface IRequestHandler: ISubscription
    {
        IResponse Handle(IRequest request);
    }
}