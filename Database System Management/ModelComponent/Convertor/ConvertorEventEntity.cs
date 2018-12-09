using ModelComponent.Domain;
using ModelComponent.Entity;

namespace ModelComponent.Convertor
{
    public class ConvertorEventEntity: IConvertorEntity<Event, EventEntity>
    {
        public EventEntity ConvertToDatabaseEntity(Event element)
        {
            var item = new EventEntity
            {
                Id = element.GetId(),
                Distance = element.Distance,
                Style = element.Style.ToString()
            };
            return item;
        }

        public Event ConvertFromDatabaseEntity(EventEntity element)
        {
            return new Event(element.Id, element.Distance, EventStyle.FromString(element.Style));
        }
    }
}