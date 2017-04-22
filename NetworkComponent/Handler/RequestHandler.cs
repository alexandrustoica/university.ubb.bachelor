using NetworkComponent.Request;
using NetworkComponent.RequestHandlerBehaviour;
using NetworkComponent.Response;
using NetworkComponent.Subscribe;
namespace NetworkComponent.Handler
{
    public class RequestHandler: IRequestHandler
    {
        private ISubscriber _subscriber;
        private IRequestHandlerBehaviour _behaviour;
        public void Subscribe(ISubscriber subscriber)
        {
            _subscriber = subscriber;
        }

        public IResponse Handle(IRequest request)
        {
            _behaviour = new FactoryRequestHandlerBehaviour().GetBehaviour(request);
            _behaviour.Subscribe(_subscriber);
            return _behaviour.Solve(request);
        }
    }
}