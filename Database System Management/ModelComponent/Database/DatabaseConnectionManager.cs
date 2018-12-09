using SQLite;

namespace ModelComponent.Database
{ 
    /// <summary>
    /// Manages the connection with our SQLite database.
    /// </summary>
    public class DatabaseConnectionManager
    {
        /// <summary>
        /// The database's URL. [SQLite]
        /// </summary>
        protected string Database;

        /// <summary>
        /// The database's connection.
        /// </summary>
        protected SQLiteConnection Connection;

        /// <summary>
        /// Creates a manager to our database connection.
        /// </summary>
        /// <param name="database">The database's URL</param>
        public DatabaseConnectionManager(string database)
        {
            Database = database;
            Connection = null;
        }

        /// <summary>
        /// Closes our current connection to database.
        /// </summary>
        public void CloseConnection()
        {
            Connection.Close();
            Connection = null;
        }

        /// <summary>
        /// Creates a new connection to database.
        /// </summary>
        public void SetConnection()
        {
            Connection = new SQLiteConnection(Database);
        }

        /// <summary>
        /// Checks if we are already connected to our database.
        /// </summary>
        /// <returns></returns>
        public bool IsConnected()
        {
            // TODO: This part should also check the connection's state.
            return Connection != null;
        }

        /// <summary>
        /// Creates a connection to our database.
        /// </summary>
        public void MakeConnection()
        {
            if (!IsConnected())
            {
                SetConnection();
            }
        }
    }
}