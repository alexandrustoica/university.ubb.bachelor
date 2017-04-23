using ModelComponent;
using ModelComponent.Domain;

namespace NetworkComponent.Service
{
    public interface IClientService
    {
        User Login(string username, string password);
        User SignUp(string username, string password, string confirm);
    }
}