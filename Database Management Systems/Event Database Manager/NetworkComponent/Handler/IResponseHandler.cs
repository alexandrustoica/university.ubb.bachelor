using NetworkComponent.Response;
using NetworkComponent.Subscribe;

namespace NetworkComponent.Handler
{
    public interface IResponseHandler: ISubscription
    {
        void Handle(IResponse response);
    }
}