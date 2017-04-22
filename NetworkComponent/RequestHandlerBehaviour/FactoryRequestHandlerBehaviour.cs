using System;
using NetworkComponent.Request;

namespace NetworkComponent.RequestHandlerBehaviour
{
    public class FactoryRequestHandlerBehaviour
    {
        public IRequestHandlerBehaviour GetBehaviour(IRequest request)
        {
            switch (request.GetRequestType())
            {
                case RequestType.Login:
                    return new LoginRequestHandlerBehaviour();
                default:
                    throw new Exception("Unsupported Request Type!");
            }
        }
    }
}