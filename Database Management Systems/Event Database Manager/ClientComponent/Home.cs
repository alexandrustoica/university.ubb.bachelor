using System;
using System.Collections.Generic;
using System.Linq;
using System.Windows.Forms;
using ModelComponent.Domain;
using NetworkComponent.Subscribe;
using NetworkComponent.Transferable;
using NetworkComponent.Transmission;

namespace ClientComponent
{
    public partial class Home : Form, ISubscriber
    {
        private readonly IClientTransmission _transmission;
        private List<Player> _players;
        private List<Event> _events;

        public Home(IClientTransmission transmission)
        {
            InitializeComponent();
            _transmission = transmission;
            _transmission.Subscribe(this);
            UpdateData();
        }

        private void UpdatePlayerList(List<Player> list)
        {
            playerList.Clear();
            foreach (var player in list)
            {
                //playerList.Items.Add(player.GetId() + " " + player.Name + " " +  player.Age);
                var events = _transmission.GetEvents(player.GetId());
                var idEvents = events.Aggregate("", (current, element) => current + (element.GetId() + " "));
                ;
                var item = new ListViewItem
                {
                    Text = player.Name + " " + player.Age + " " + idEvents
                };
                item.SubItems.Add(player.GetId().ToString());
                item.SubItems.Add(player.Name);
                item.SubItems.Add(player.Age.ToString());
                playerList.Items.Add(item);
            }
        }

        private void UpdateData()
        {
            _events = _transmission.GetEvents(0);
            var _players = _transmission.GetPlayers(0);
            eventList.Clear();
            UpdatePlayerList(_players);
            foreach (var item in _events)
            {
                var players = _transmission.GetPlayers(item.GetId());
                var idPlayers = players.Aggregate("", (current, element) => current + (element.GetId() + " "));
                var data = new ListViewItem
                {
                    Text = item.Distance + " " + item.Style + " " + idPlayers
                };
                data.SubItems.Add(item.GetId().ToString());
                data.SubItems.Add(item.Distance.ToString());
                data.SubItems.Add(item.Style.ToString());
                eventList.Items.Add(data);
            }
        }

        private void addPlayerButton_Click(object sender, EventArgs e)
        {
            var selected = eventList.SelectedItems;
            var events = (from ListViewItem data in selected select int.Parse(data.SubItems[1].Text)).ToList();
            var name = nameTextBox.Text;
            var age = int.Parse(ageTextBox.Text);
            _transmission.AddPlayer(name, age, events);
            UpdateData();
        }

        private void searchEventButton_Click(object sender, EventArgs e)
        {
            var searchTerm = eventSearchTextBox.Text;

            var result = _events;
            if (searchTerm != string.Empty)
            {
                result = _events.Where(item => item.Distance.Equals(Convert.ToInt32(searchTerm))).ToList();
            }
            eventList.Clear();;
            foreach (var item in result)
            {
                eventList.Items.Add(item.GetId() + " " + item.Distance + " " + item.Style);
            }
        }

        private void searchPlayerButton_Click(object sender, EventArgs e)
        {
            var searchTerm = playerSearchTextBox.Text;
            var result = _players.Where(item => item.Name.Contains(searchTerm)).ToList();
            playerList.Clear();
            foreach (var item in result)
            {
                playerList.Items.Add(item.GetId() + " " + item.Name + " " + item.Age);
            }
        }

        private void onEventClick(object sender, EventArgs e)
        {
            var selected = eventList.SelectedItems;
            var item = selected[0];
            var data = (ListViewItem)item;
            var idEvent = int.Parse(data.SubItems[1].Text);
            var players = _transmission.GetPlayers(idEvent);
            UpdatePlayerList(players);
        }

        public void Update(ITransferable notification)
        {
           // BeginInvoke(new MethodInvoker(UpdateData));
        }
    }
}
