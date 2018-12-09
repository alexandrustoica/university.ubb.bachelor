using System.Collections.Generic;
using NetworkComponent.System;
using NetworkComponent.Transferable;

namespace NetworkComponent.Request
{
    public interface IRequest:
        ITransferable, IErrorSupport
    {
        RequestType GetRequestType();
        void Add(object element, string key);
        object Get(string key);
        List<object> GetAll();
    }
}