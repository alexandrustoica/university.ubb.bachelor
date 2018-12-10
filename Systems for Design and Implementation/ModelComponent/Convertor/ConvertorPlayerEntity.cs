using ModelComponent.Domain;
using ModelComponent.Entity;

namespace ModelComponent.Convertor
{
    public class ConvertorPlayerEntity: IConvertorEntity<Player, PlayerEntity>
    {
        public PlayerEntity ConvertToDatabaseEntity(Player element)
        {
            var player = new PlayerEntity
            {
                Id = element.GetId(),
                Name = element.Name,
                Age = element.Age
            };
            return player;
        }

        public Player ConvertFromDatabaseEntity(PlayerEntity element)
        {
            return new Player(element.Id, element.Name, element.Age);
        }
    }
}