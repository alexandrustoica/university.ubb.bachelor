using NetworkComponent.Response;
using NetworkComponent.Subscribe;

namespace NetworkComponent.ResponseHandlerBehaviour
{
    public interface IResponseHandlerBehaviour: ISubscription
    {
        void Solve(IResponse response);
    }
}