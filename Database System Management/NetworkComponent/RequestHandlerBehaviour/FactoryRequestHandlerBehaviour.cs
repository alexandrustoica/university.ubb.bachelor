using System;
using NetworkComponent.Request;

namespace NetworkComponent.RequestHandlerBehaviour
{
    public class FactoryRequestHandlerBehaviour
    {
        public RequestHandlerBehaviour GetBehaviour(IRequest request)
        {
            switch (request.GetRequestType())
            {
                case RequestType.Login:
                    return new LoginRequestHandlerBehaviour();
                case RequestType.SignUp:
                    return new SignUpRequestHandlerBehaviour();
                case RequestType.GetPlayers:
                    return new GetPlayersRequestHandlerBehaviour();
                case RequestType.GetEvents:
                    return new GetEventsRequestHandlerBehaviour();
                case RequestType.AddPlayer:
                    return new AddPlayerRequestHandlerBehaviour();
                default:
                   throw new Exception("Unsupported Request Type!");
            }
        }
    }
}