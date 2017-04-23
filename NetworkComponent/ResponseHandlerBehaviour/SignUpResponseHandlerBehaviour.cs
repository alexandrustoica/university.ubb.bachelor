using ModelComponent.Domain;
using NetworkComponent.Response;

namespace NetworkComponent.ResponseHandlerBehaviour
{
    public class SignUpResponseHandlerBehaviour: ResponseHandlerBehaviour
    {
        public override void Solve(IResponse response)
        {
            var user = (User)response.Get("user");
            Subscriber.UpdateSystem(user);
        }
    }
}