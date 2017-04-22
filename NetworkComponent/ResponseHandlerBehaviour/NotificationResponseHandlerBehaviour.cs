using NetworkComponent.Response;
using NetworkComponent.Subscribe;

namespace NetworkComponent.ResponseHandlerBehaviour
{
    public class NotificationResponseHandlerBehaviour: IResponseHandlerBehaviour
    {
        private IConnectionObserver _observer;
        public void Subscribe(ISubscriber subscriber)
        {
            _observer = (IConnectionObserver)subscriber;
        }

        public void Solve(IResponse response)
        {
            _observer.Update(response);
        }
    }
}