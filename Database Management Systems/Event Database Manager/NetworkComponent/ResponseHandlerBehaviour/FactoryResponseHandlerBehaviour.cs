using System;
using NetworkComponent.RequestHandlerBehaviour;
using NetworkComponent.Response;

namespace NetworkComponent.ResponseHandlerBehaviour
{
    public class FactoryResponseHandlerBehaviour
    {
        public ResponseHandlerBehaviour GetBehaviour(IResponse response)
        {
            switch (response.GeType())
            {
                case ResponseType.Login:
                    return new LoginResponseHandlerBehaviour();
                case ResponseType.Notification:
                    return new NotificationResponseHandlerBehaviour();
                case ResponseType.SignUp:
                    return new SignUpResponseHandlerBehaviour();
                case ResponseType.GetPlayers:
                    return new GetPlayersResponseHandlerBehaviour();
                case ResponseType.GetEvent:
                    return new GetEventsResponseHandlerBehaviour();
                default:
                    throw new Exception("Unsupported Request Type!");
            }
        }
    }
}