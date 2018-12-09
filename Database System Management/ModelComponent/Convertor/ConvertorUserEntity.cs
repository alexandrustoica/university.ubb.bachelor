using ModelComponent.Domain;
using ModelComponent.Entity;

namespace ModelComponent.Convertor
{
    public class ConvertorUserEntity: IConvertorEntity<User, UserEntity>
    {
        public UserEntity ConvertToDatabaseEntity(User element)
        {
            var user = new UserEntity()
            {
                Id = element.GetId(),
                Name = element.Name,
                Password = element.Password
            };
            return user;
        }

        public User ConvertFromDatabaseEntity(UserEntity element)
        {
            return new User(element.Id, element.Name, element.Password);
        }
    }
}