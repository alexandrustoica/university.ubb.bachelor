using System;
using System.Collections.Generic;
using System.Linq;
using EventManager.Service;
using ModelComponent.Domain;
using ModelComponent.Model;
using Thrift.Protocol;
using Thrift.Transport;

namespace @event.server.csharp
{
    public class ConnectionManager: Subsciption.Iface
    {
        private readonly List<Subscriber.Client> _subscribers;

        private readonly ModelPlayer _modelPlayer;
        private readonly ModelEvent _modelEvent;
        private readonly ModelUser _modelUser;

        public ConnectionManager()
        {
            _subscribers = new List<Subscriber.Client>();
            _modelEvent = new ModelEvent();   
            _modelPlayer = new ModelPlayer();
            _modelUser = new ModelUser();
        }

        private UserData ConvertToUserData(User user)
        {
            return new UserData(user.Name, user.Password);   
        }

        private User ConvertToUser(UserData user)
        {
            return new User(user.Id, user.Username, user.Password);
        }

        private PlayerData ConvertToPlayerData(Player player)
        {
            return new PlayerData(player.Name, player.Age);
        }

        private Player ConvertToPlayer(PlayerData player)
        {
            return new Player(player.Id, player.Name, player.Age);
        }

        private EventData ConvertToEventData(Event element)
        {
            return new EventData(element.Distance, element.Style.ToString());
        }

        private Event ConvertToEvent(EventData element)
        {
            return new Event(element.Id, element.Distance, EventStyle.FromString(element.Style));
        }

        public string sendMessage(string message)
        {
            Console.Write(message);
            return "Hello From C#!";
        }

        public UserData login(string username, string password)
        {
            return ConvertToUserData(_modelUser.GetAll().Find(user =>
                user.Name.Equals(username) && user.Password.Equals(password)));
        }

        private bool Validate(string username, string password, string confirm)
        {
            return _modelUser.GetAll().Exists(user => !user.Name.Equals(username)) && password.Equals(confirm);
        }

        public UserData signUp(string username, string password, string confirm)
        {
            return ConvertToUserData(
                Validate(username, password, confirm)
                    ? _modelUser.FindElementById(_modelUser.Add(new User(username, password))) : null);
        }

        public List<PlayerData> getPlayers()
        {
            return _modelPlayer.GetAll().Select(ConvertToPlayerData).ToList();
        }

        public List<EventData> getEvents()
        {
            return _modelEvent.GetAll().Select(ConvertToEventData).ToList();
        }

        public List<EventData> getEventsFromPlayer(PlayerData player)
        {
            return _modelPlayer.GetAllPlayerEvents(ConvertToPlayer(player).GetId()).Select(ConvertToEventData).ToList();
        }

        public List<PlayerData> getPlayersFromEvent(EventData @event)
        {
            return _modelEvent.GetAllEventPlayers(ConvertToEvent(@event).GetId()).Select(ConvertToPlayerData).ToList();
        }

        public int addPlayer(PlayerData player, List<EventData> events)
        {
            var id = _modelPlayer.Add(ConvertToPlayer(player));
            events.ForEach(item => _modelEvent.AddEventToPlayer(item.Id, id));
            UpdateSubscribers(Notification.UPDATE_ALL);
            return id;
        }

        private void UpdateSubscribers(Notification notification)
        {
            _subscribers.ForEach(subscriber => subscriber.update(notification));
        }

        public void subscribe(string hostname, int port)
        {
            TTransport transport = new TSocket(hostname, port);
            transport.Open();
            TProtocol protocol = new TBinaryProtocol(transport);
            var client = new Subscriber.Client(protocol);
            _subscribers.Add(client);
            Console.WriteLine("Client#" + _subscribers.Count + " Connected!");
            UpdateSubscribers(Notification.UPDATE_ALL);
        }

        public void unsubscribe(string hostname, int port)
        {
            UpdateSubscribers(Notification.UPDATE_ALL);
        }
    }
}