using NetworkComponent.Request;
using NetworkComponent.Response;
using NetworkComponent.Subscribe;

namespace NetworkComponent.RequestHandlerBehaviour
{
    public abstract class RequestHandlerBehaviour: ISubscription
    { 
        protected ISubscriber Subscriber;

        public void Subscribe(ISubscriber subscriber)
        {
            Subscriber = subscriber;
        }

        public abstract IResponse Solve(IRequest request);
    }
}