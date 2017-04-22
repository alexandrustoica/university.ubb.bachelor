using System;
using NetworkComponent.RequestHandlerBehaviour;
using NetworkComponent.Response;

namespace NetworkComponent.ResponseHandlerBehaviour
{
    public class FactoryResponseHandlerBehaviour
    {
        public IResponseHandlerBehaviour GetBehaviour(IResponse response)
        {
            switch (response.GeType())
            {
                case ResponseType.Login:
                    return new LoginResponseHandlerBehaviour();
                case ResponseType.Notification:
                    return new NotificationResponseHandlerBehaviour();
                default:
                    throw new Exception("Unsupported Request Type!");
            }
        }
    }
}