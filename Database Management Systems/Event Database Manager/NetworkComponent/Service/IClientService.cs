using System.Collections.Generic;
using ModelComponent;
using ModelComponent.Domain;

namespace NetworkComponent.Service
{
    public interface IClientService
    {
        User Login(string username, string password);
        User SignUp(string username, string password, string confirm);
        List<Player> GetPlayers(int idEvent);
        List<Event> GetEvents(int idPlayer);
        void AddPlayer(string name, int age, List<int> idEvents);
    }
}