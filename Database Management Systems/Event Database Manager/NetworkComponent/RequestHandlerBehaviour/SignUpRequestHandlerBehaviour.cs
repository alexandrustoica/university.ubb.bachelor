using System.Linq;
using ModelComponent.Domain;
using ModelComponent.Model;
using NetworkComponent.Request;
using NetworkComponent.Response;

namespace NetworkComponent.RequestHandlerBehaviour
{
    public class SignUpRequestHandlerBehaviour: RequestHandlerBehaviour
    {
        private ModelUser _model;

        public SignUpRequestHandlerBehaviour()
        {
            _model = new ModelUser();
        }

        private bool IsNameUnique(string username)
        {
            _model = new ModelUser();
            return _model.GetAll().All(user => !user.Name.Equals(username));
        }

        public override IResponse Solve(IRequest request)
        {
            var username = (string) request.Get("username");
            var password = (string) request.Get("password");
            var confirm = (string) request.Get("confirm");
            IResponse response = new Response.Response(ResponseType.SignUp);
            if (password.Equals(confirm) && IsNameUnique(username))
            {
                var user = new User(username, password);
                var id = _model.Add(user);
                response.Add(_model.FindElementById(id), "user");
                return response;
            }  
            response.SetError(404, "Invalid User Data!");
            return response;
        }
    }
}