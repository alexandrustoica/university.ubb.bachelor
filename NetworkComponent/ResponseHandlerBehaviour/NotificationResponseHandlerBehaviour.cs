using NetworkComponent.Response;

namespace NetworkComponent.ResponseHandlerBehaviour
{
    public class NotificationResponseHandlerBehaviour: ResponseHandlerBehaviour
    {
        public override void Solve(IResponse response)
        {
            Subscriber.Update(response);
        }
    }
}