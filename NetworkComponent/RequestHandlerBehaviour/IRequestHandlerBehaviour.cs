using NetworkComponent.Request;
using NetworkComponent.Response;
using NetworkComponent.Subscribe;

namespace NetworkComponent.RequestHandlerBehaviour
{
    public interface IRequestHandlerBehaviour: ISubscription
    {
        IResponse Solve(IRequest request);
    }
}