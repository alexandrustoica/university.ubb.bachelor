using System.ComponentModel.DataAnnotations.Schema;
using SQLite;

namespace ModelComponent.Entity
{
    /// <summary>
    /// Represents the relationship between players and events.
    /// </summary>
    public class PlayerEventEntity: IIdableEntity<int>
    {
        [PrimaryKey, AutoIncrement]
        public int Id { get; set; }
        
        [ForeignKey("PlayerDatabaseEntity")]
        [NotNull]
        public int IdPlayer { get; set; }

        [ForeignKey("EventDatabaseEntity")]
        [NotNull]
        public int IdEvent { get; set; }
    }
}