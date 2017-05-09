using SQLite;
using SQLite.Net.Attributes;


namespace ModelComponent.Entity
{
    /// <summary>
    /// The player's representation in database.
    /// </summary>
    public class PlayerEntity: IIdableEntity
    {
        [PrimaryKey, AutoIncrement]
        public override int Id { get; set; }

        /// <summary>
        /// The player's name [not null]
        /// </summary>
        [NotNull]
        public string Name { get; set; }

        /// <summary>
        /// The player's age [not null]
        /// </summary>
        [NotNull]
        public int Age { get; set; }
    }
}