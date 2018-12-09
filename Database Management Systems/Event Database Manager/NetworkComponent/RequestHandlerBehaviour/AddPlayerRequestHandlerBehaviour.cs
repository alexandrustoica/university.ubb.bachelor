using System.Collections.Generic;
using ModelComponent.Domain;
using ModelComponent.Model;
using NetworkComponent.Request;
using NetworkComponent.Response;

namespace NetworkComponent.RequestHandlerBehaviour
{
    public class AddPlayerRequestHandlerBehaviour: RequestHandlerBehaviour
    {
        public override IResponse Solve(IRequest request)
        {
            var modelPlayer = new ModelPlayer();
            var modelEvent = new ModelEvent();
            var name = (string) request.Get("name");
            var age = (int) request.Get("age");
            var events = (List<int>) request.Get("events");
            IResponse response = new Response.Response(ResponseType.Notification);
            var id = modelPlayer.Add(new Player(name, age));
            foreach (var item in events)
            {
                modelPlayer.AddEventToPlayer(item, id);
            }
            //response.Add(UpdateType.All, "update");
            return response;
        }
    }
}