using System.Collections.Generic;
using ModelComponent.Domain;
using ModelComponent.Model;
using NetworkComponent.Request;
using NetworkComponent.Response;

namespace NetworkComponent.RequestHandlerBehaviour
{
    public class GetPlayersRequestHandlerBehaviour: RequestHandlerBehaviour
    {
        public override IResponse Solve(IRequest request)
        {
            var modelPlayer = new ModelPlayer();
            var modelEvent = new ModelEvent();
            var id = (int)request.Get("event");
            IResponse response = new Response.Response(ResponseType.GetPlayers);
            var list = modelPlayer.GetAll();
            if (!id.Equals(0))
            {
                list = modelEvent.GetAllEventPlayers(id);
            }
            response.Add(list, "players");
            return response;
        }
    }
}