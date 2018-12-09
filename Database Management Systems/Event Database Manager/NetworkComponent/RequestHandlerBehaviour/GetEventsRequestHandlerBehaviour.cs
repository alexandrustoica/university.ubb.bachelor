using System.Collections.Generic;
using ModelComponent.Domain;
using ModelComponent.Model;
using NetworkComponent.Request;
using NetworkComponent.Response;

namespace NetworkComponent.RequestHandlerBehaviour
{
    public class GetEventsRequestHandlerBehaviour: RequestHandlerBehaviour
    {
        public override IResponse Solve(IRequest request)
        {
            var modelPlayer = new ModelPlayer();
            var modelEvent = new ModelEvent();
            var id = (int)request.Get("player");
            IResponse response = new Response.Response(ResponseType.GetEvent);
            var list = modelEvent.GetAll();
            if (!id.Equals(0))
            {
                list = modelPlayer.GetAllPlayerEvents(id);
            }
            response.Add(list, "events");
            return response;
        }
    }
}