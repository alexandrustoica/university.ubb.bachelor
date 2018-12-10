using NetworkComponent.Response;
using NetworkComponent.Subscribe;

namespace NetworkComponent.ResponseHandlerBehaviour
{
    public abstract class ResponseHandlerBehaviour: ISubscription
    {
        protected IConnectionObserver Subscriber;

        public void Subscribe(ISubscriber subscriber)
        {
            Subscriber = (IConnectionObserver)subscriber;
        }

        public abstract void Solve(IResponse response);
    }
}