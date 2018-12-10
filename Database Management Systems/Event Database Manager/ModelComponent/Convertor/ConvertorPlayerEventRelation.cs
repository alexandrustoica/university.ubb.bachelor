using ModelComponent.Domain;
using ModelComponent.Entity;

namespace ModelComponent.Convertor
{
    public class ConvertorPlayerEventRelation: IConvertorRelation<Player, PlayerEntity, Event, EventEntity, PlayerEventEntity>
    {
        public PlayerEventEntity ConvertToDatabaseRelation(Player element, Event item)
        {
            var relation = new PlayerEventEntity()
            {
                IdPlayer = element.GetId(),
                IdEvent = item.GetId()
            };
            return relation;
        }

        public bool IsIdEqual(EventEntity element, PlayerEventEntity item)
        {
            return element.Id.Equals(item.IdEvent);
        }

        public bool IsIdEqual(PlayerEntity element, PlayerEventEntity item)
        {
            return element.Id.Equals(item.IdPlayer);
        }

        public bool IsIdEqual(Player element, PlayerEventEntity item)
        {
            return element.GetId().Equals(item.IdPlayer);
        }

        public bool IsIdEqual(Event element, PlayerEventEntity item)
        {
            return element.GetId().Equals(item.IdEvent);
        }
    }
}