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
            _isReady = true;
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
            request.Add(username, "username");
            request.Add(password, "password");
            Send(request);
            return (User) _result;
        }

        private void Send(IRequest request)
        {
            _connection.Send(request);
            while (!_isReady)
            {
                // TODO
            }
            _isReady = false;
        }

        public User SignUp(string username, string password, string confirm)
        {
            IRequest request = new Request.Request(RequestType.SignUp);
            request.Add(username, "username");
            request.Add(password, "password");
            request.Add(confirm, "confirm");
            Send(request);
            return (User) _result;
        }

        public List<Player> GetPlayers(int idEvent)
        {
            IRequest request = new Request.Request(RequestType.GetPlayers);
            request.Add(idEvent != 0 ? idEvent : 0, "event");
            Send(request);
            return (List<Player>) _result;
        }

        public List<Event> GetEvents(int idPlayer)
        {
            IRequest request = new Request.Request(RequestType.GetEvents);
            request.Add(idPlayer != 0 ? idPlayer : 0, "player");
            Send(request);
            return (List<Event>)_result;
        }

        public void AddPlayer(string name, int age, List<int> idEvents)
        {
            IRequest request = new Request.Request(RequestType.AddPlayer);
            request.Add(name, "name");
            request.Add(age, "age");
            request.Add(idEvents, "events");
            Send(request);
        }
    }
}