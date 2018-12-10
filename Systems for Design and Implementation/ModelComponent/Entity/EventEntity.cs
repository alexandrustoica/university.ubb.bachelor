
using SQLite;
using SQLite.Net.Attributes;

namespace ModelComponent.Entity
{
    /// <summary>
    /// The event's representation in datebase.
    /// </summary>
    public class EventEntity: IIdableEntity
    {  
        [PrimaryKey, AutoIncrement]
        public override int Id { get; set; }

        /// <summary>
        /// The event's distance [m] [not null]
        /// </summary>
        [NotNull]
        public int Distance { get; set; }

        /// <summary>
        /// The event's style [category] [not null]
        /// </summary>
        [NotNull]
        public string Style { get; set; }
        
    }
}