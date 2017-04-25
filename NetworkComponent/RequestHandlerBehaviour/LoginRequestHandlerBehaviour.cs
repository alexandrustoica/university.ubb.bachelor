using System.Linq;
using ModelComponent.Domain;
using ModelComponent.Model;
using NetworkComponent.Request;
using NetworkComponent.Response;

namespace NetworkComponent.RequestHandlerBehaviour
{
    public class LoginRequestHandlerBehaviour:
        RequestHandlerBehaviour
    {
        private readonly ModelUser _model;

        public LoginRequestHandlerBehaviour()
        {
            _model = new ModelUser();
        }

        private User IsUserValid(string username,  string password)
        {
            return _model.GetAll().FirstOrDefault(user =>
            user.Name.Equals(username) && user.Password.Equals(password));
        }


        public override IResponse Solve(IRequest request)
        {
            var username = (string) request.Get("username");
            var password = (string) request.Get("password");
            IResponse response = new Response.Response(ResponseType.Login);
            var user = IsUserValid(username, password);
            if (user != null) 
            {
                response.Add(user, "user");
                return response;
            }
            response.SetError(404, "Invalid User Data!");
            return response;
        }
    }
}