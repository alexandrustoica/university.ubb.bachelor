using ModelComponent;
using ModelComponent.Domain;
using NetworkComponent.Response;
using NetworkComponent.Subscribe;

namespace NetworkComponent.ResponseHandlerBehaviour
{
    public class LoginResponseHandlerBehaviour: ResponseHandlerBehaviour
    {
        public override void Solve(IResponse response)
        {
            var user = (User)response.Get("user");
            Subscriber.UpdateSystem(user);
        }
    }
}