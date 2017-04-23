using System;
using System.Windows.Forms;
using ModelComponent.Domain;
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
        
        public void Update(ITransferable notification)
        {
        }

        private void signUpButton_Click(object sender, EventArgs e)
        {
            var username = usernameTextBox.Text;
            var password = passwordTextBox.Text;
            var confirm = confirmTextBox.Text;
            var user = _transmission.SignUp(username, password, confirm);
            label1.Text = user.Name + " " + user.GetId().ToString() + " " + user.Password;
        }
    }
}
