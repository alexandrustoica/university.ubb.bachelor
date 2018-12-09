using System.Windows.Forms;
using NetworkComponent.Transmission;

namespace ClientComponent
{
    public partial class Login : Form
    {
        private readonly SignUp _signUp;
        private readonly Home _home;
        private readonly IClientTransmission _transmission;

        public Login(SignUp signUp, Home home, IClientTransmission transmission)
        {
            InitializeComponent();
            _transmission = transmission;
            _home = home;
            _signUp = signUp;
        }

        private void signUpButton_Click(object sender, System.EventArgs e)
        {
            _signUp.Location = Location;
            _signUp.Show();
            Hide();
        }

        private void loginButton_Click(object sender, System.EventArgs e)
        {
            var username = usernameTextBox.Text;
            var password = passwordTextBox.Text;
            var user = _transmission.Login(username, password);
            _home.Location = Location;
            _home.Show();
            Hide();
        }
    }
}
