using System;
using System.Collections.Generic;
using ModelComponent.Domain;
using NetworkComponent.Connection;
using NetworkComponent.Transferable;
using NetworkComponent.Request;
using NetworkComponent.Subscribe;

namespace NetworkComponent.Transmission
{
    public class ClientTransmissionManager: IClientTransmission
    {
        private readonly IClientConnection _connection;
        private ISubscriber _subscriber;
        private bool _isReady;
        private object _result;

        public ClientTransmissionManager(IClientConnection connection)
        {
            _connection = connection;
            _connection.Subscribe(this);
        }

        public void Start()
        {
            _connection.Start();
        }

        public void Stop()
        {
            _connection.Stop();
        }

        public void Update(ITransferable notification)
        {
            _subscriber.Update(notification);
        }

        public void Subscribe(ISubscriber subscriber)
        {
            _subscriber = subscriber;
        }

        public void UpdateSystem(object response)
        {
            try
            {

                var exception = (Exception) response;
                _isReady = true;
                throw exception;
            }
            catch (InvalidCastException)
            {
                _result = response;
                _isReady = true;
            } 
        }

        public User Login(string username, string password)
        {
            IRequest request = new Request.Request(RequestType.Login);
            //request.Add(1, "id");
            //request.Add(username, "username");
            //request.Add(password, "password");
            var list = new List<User>();
            list.Add(new User(1, "Ana", "password"));
            list.Add(new User(1, "Naan", "23423"));
            request.Add(list, "users");
            _connection.Send(request);
            while (!_isReady)
            {
                // TODO
            }
            _isReady = false;
            return (User) _result;
        }
    }
}