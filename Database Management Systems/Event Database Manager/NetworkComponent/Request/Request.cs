using System;
using System.Collections.Generic;
using System.Linq;
using NetworkComponent.Transferable;

namespace NetworkComponent.Request
{
    [Serializable]
    public class Request: IRequest
    {
        private readonly RequestType _type;
        private readonly Dictionary<string, object> _elements;
        private int _errorCode;
        private string _errorMessage;

        public Request(RequestType type)
        {
            _type = type;
            _elements = new Dictionary<string, object>();
        }

        TransferType ITransferable.GetTransferType()
        {
            return TransferType.Request;
        }

        public void Add(object element, string key)
        {
            _elements[key] = element;
        }

        public object Get(string key)
        {
            return _elements[key];
        }

        public List<object> GetAll()
        {
            return _elements.Values.ToList();
        }

        RequestType IRequest.GetRequestType()
        {
            return _type;
        }

        public void SetError(int errorCode, string message)
        {
            _errorCode = errorCode;
            _errorMessage = message;
        }

        public int GetErrorCode()
        {
            return _errorCode;
        }

        public string GetErrorMessage()
        {
            return _errorMessage;
        }
    }
}