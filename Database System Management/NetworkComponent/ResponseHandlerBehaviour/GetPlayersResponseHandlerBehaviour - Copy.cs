using System.Collections.Generic;
using ModelComponent.Domain;
using NetworkComponent.Response;

namespace NetworkComponent.ResponseHandlerBehaviour
{
    public class GetPlayersResponseHandlerBehaviour: ResponseHandlerBehaviour
    {
        public override void Solve(IResponse response)
        {
            var players = (List<Player>)response.Get("players");
            Subscriber.UpdateSystem(players);
        }
    }
}