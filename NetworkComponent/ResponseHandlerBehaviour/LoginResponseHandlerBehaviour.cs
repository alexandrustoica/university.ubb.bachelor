using ModelComponent;
using NetworkComponent.Response;
using NetworkComponent.Subscribe;

namespace NetworkComponent.ResponseHandlerBehaviour
{
    public class LoginResponseHandlerBehaviour: IResponseHandlerBehaviour
    {
        private IConnectionObserver _subscriber;

        public void Subscribe(ISubscriber subscriber)
        {
            _subscriber = (IConnectionObserver)subscriber;
        }

        public void Solve(IResponse response)
        {
            var user = (User)response.Get("user");
            _subscriber.UpdateSystem(user);
        }
    }
}