using System;
using System.Windows.Forms;
using NetworkComponent.Connection;
using NetworkComponent.Response;
using NetworkComponent.Subscribe;
using NetworkComponent.System;
using NetworkComponent.Transferable;
using NetworkComponent.Transmission;

namespace ClientComponent
{
    public partial class LoginWindow : Form, ISubscriber
    {
        private readonly IClientTransmission _transmission;
        private const int Port = 55555;
        private const string Host = "127.0.0.1";

        public LoginWindow()
        {
            InitializeComponent();
            IClientConnection connection = new ClientConnectionManager(Host, Port);
            _transmission = new ClientTransmissionManager(connection);
             _transmission.Start();
            _transmission.Subscribe(this);
        }

        private void OnLoginButton(object sender, EventArgs e)
        {
            var user = _transmission.Login("Ana", "test");
            Message.Text = user.GetName();
        }

        public void Update(ITransferable notification)
        {
            var response = (IResponse) notification;
            Console.WriteLine(response.Get("update"));
        }
    }
}
