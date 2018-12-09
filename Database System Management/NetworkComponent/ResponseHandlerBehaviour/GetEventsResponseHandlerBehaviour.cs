using System.Collections.Generic;
using ModelComponent.Domain;
using NetworkComponent.Response;

namespace NetworkComponent.ResponseHandlerBehaviour
{
    public class GetEventsResponseHandlerBehaviour: ResponseHandlerBehaviour
    {
        public override void Solve(IResponse response)
        {
            var events = (List<Event>)response.Get("events");
            Subscriber.UpdateSystem(events);
        }
    }
}