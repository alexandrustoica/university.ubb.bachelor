using System.Collections.Generic;
using NetworkComponent.System;
using NetworkComponent.Transferable;

namespace NetworkComponent.Response
{
    public interface IResponse: ITransferable, IErrorSupport
    {
        ResponseType GeType();
        void Add(object element, string key);
        object Get(string key);
        List<object> GetAll();
    }
}