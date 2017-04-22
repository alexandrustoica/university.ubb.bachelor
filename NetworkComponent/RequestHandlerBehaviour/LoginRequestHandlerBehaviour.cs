using System;
using System.Collections.Generic;
using ModelComponent;
using NetworkComponent.Request;
using NetworkComponent.Response;
using NetworkComponent.Subscribe;

namespace NetworkComponent.RequestHandlerBehaviour
{
    public class LoginRequestHandlerBehaviour:
        IRequestHandlerBehaviour
    {
        private ISubscriber _subscriber;

        public IResponse Solve(IRequest request)
        {
            //var id = (int) request.Get("id");
            //var username = (string) request.Get("username");
            //var password = (string) request.Get("password");
            //Console.WriteLine(id + " " + username + " " + password);
            var list = (List<User>) request.Get("users");
            foreach (var user in list)
            {
               Console.WriteLine(user.GetId() + " " +
                   user.GetName() + " " +  user.GetPassword());
            }

            IResponse notification = new Response.Response(ResponseType.Notification);
            notification.Add(UpdateType.All, "update");
            _subscriber.Update(notification);

            IResponse response = new Response.Response(ResponseType.Login);
            response.Add(new User(1, "ana", "24pass"), "user");
            return response;
        }

        public void Subscribe(ISubscriber subscriber)
        {
            _subscriber = subscriber;
        }
    }
}