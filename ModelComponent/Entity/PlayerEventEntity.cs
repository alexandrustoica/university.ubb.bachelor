using System.ComponentModel.DataAnnotations.Schema;
using SQLite;

namespace ModelComponent.Entity
{
    /// <summary>
    /// Represents the relationship between players and events.
    /// </summary>
    public class PlayerEventEntity: IIdableEntity
    {
        [PrimaryKey, AutoIncrement]
        public override int Id { get; set; }
        
        [ForeignKey("PlayerDatabaseEntity")]
        [NotNull]
        public int IdPlayer { get; set; }

        [ForeignKey("EventDatabaseEntity")]
        [NotNull]
        public int IdEvent { get; set; }
    }
}