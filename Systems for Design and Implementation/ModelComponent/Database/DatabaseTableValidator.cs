using ModelComponent.Entity;

namespace ModelComponent.Database
{
    public class DatabaseTableValidator: DatabaseConnectionManager
    {
        /// <summary>
        /// Checks the database's stucture.
        /// </summary>
        /// <param name="database">The database's URL</param>
        public DatabaseTableValidator(string database) : base(database) { }

        /// <summary>
        /// Checks the tables in our database
        /// and creates those tables if they don't exist.
        /// </summary>
        public void CheckDatabase()
        {
            MakeConnection();
            Connection.CreateTable<PlayerEntity>();
            Connection.CreateTable<EventEntity>();
            Connection.CreateTable<UserEntity>();
            Connection.CreateTable<PlayerEventEntity>();
            CloseConnection();
        }
    }
}