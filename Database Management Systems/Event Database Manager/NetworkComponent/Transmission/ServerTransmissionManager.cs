using NetworkComponent.Connection;

namespace NetworkComponent.Transmission
{
    public class ServerTransmissionManager: IServerTransmission
    {
        private readonly IServerConnection _connection;

        public ServerTransmissionManager(IServerConnection connection)
        {
            _connection = connection;
        }

        public void Start()
        {
            _connection.Start();
        }

        public void Stop()
        {
            _connection.Stop();
        }
    }
}
