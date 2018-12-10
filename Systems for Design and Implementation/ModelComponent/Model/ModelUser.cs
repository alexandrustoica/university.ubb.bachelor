using ModelComponent.Domain;
using ModelComponent.Entity;
using ModelComponent.Repository;

namespace ModelComponent.Model
{
    public class ModelUser : Model<User, UserEntity>
    {
        public ModelUser(string database) : base(database)
        {
            Repository = RepositoryFactory
                .GetInstance<User, UserEntity>(PreferredDatabaseSource, RepositoryType.User);
        }

        public ModelUser()
        {
            Repository = RepositoryFactory
                .GetInstance<User, UserEntity>(PreferredDatabaseSource, RepositoryType.User);
        }
    }
}