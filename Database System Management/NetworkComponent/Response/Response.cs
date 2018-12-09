using System;
using System.Collections.Generic;
using System.Linq;
using NetworkComponent.Transferable;

namespace NetworkComponent.Response
{
    [Serializable]
    public class Response: IResponse
    {
        private readonly ResponseType _type;
        private readonly Dictionary<string, object> _elements;
        private int _errorCode;
        private string _errorMessage;

        public Response(ResponseType type)
        {
            _type = type;
            _elements = new Dictionary<string, object>();
        }

        public TransferType GetTransferType()
        {
            return TransferType.Response;
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

        public ResponseType GeType()
        {
            return _type;
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
    }
}