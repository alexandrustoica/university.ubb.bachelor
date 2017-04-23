using SQLite;

namespace ModelComponent.Entity
{
    /// <summary>
    /// The event's representation in datebase.
    /// </summary>
    public class EventEntity: IIdableEntity<int>
    {  
        [PrimaryKey, AutoIncrement]
        public int Id { get; set; }

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