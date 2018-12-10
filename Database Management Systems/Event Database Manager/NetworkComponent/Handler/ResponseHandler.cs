using NetworkComponent.Response;
using NetworkComponent.ResponseHandlerBehaviour;
using NetworkComponent.Subscribe;
namespace NetworkComponent.Handler
{
    public class ResponseHandler : IResponseHandler
    {
        private ISubscriber _subscriber;
        private ResponseHandlerBehaviour.ResponseHandlerBehaviour _behaviour;
        public void Subscribe(ISubscriber subscriber)
        {
            _subscriber = subscriber;
        }

        public void Handle(IResponse response)
        {
            _behaviour = new FactoryResponseHandlerBehaviour().GetBehaviour(response);
            _behaviour.Subscribe(_subscriber);
            _behaviour.Solve(response);
        }
    }
}