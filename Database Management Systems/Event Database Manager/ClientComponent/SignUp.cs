using System;
using System.Windows.Forms;
using NetworkComponent.Connection;
using NetworkComponent.Subscribe;
using NetworkComponent.Transferable;
using NetworkComponent.Transmission;

namespace ClientComponent
{
    public partial class SignUp : Form, ISubscriber
    {
        private readonly IClientTransmission _transmission;
        private const int Port = 55555;
        private const string Host = "127.0.0.1";
        private readonly Login _login;
        private readonly Home _home;

        public SignUp()
        {
            InitializeComponent();
            IClientConnection connection = new ClientConnectionManager(Host, Port);
            _transmission = new ClientTransmissionManager(connection);
            _transmission.Start();
            _transmission.Subscribe(this);
            _home = new Home(_transmission)
            {
                Location = Location,
                StartPosition = FormStartPosition.Manual
            };
            _login = new Login(this, _home, _transmission)
            {
                Location = Location,
                StartPosition = FormStartPosition.Manual
            };
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
            _home.Location = Location;
            _home.Show();
            Hide();
        }

        private void loginButton_Click(object sender, EventArgs e)
        {
            _login.Location = Location;
            _login.Show();
            Hide();
        }
    }
}
