using SQLite;

namespace ModelComponent.Entity
{
    /// <summary>
    /// The user's representation in database.
    /// </summary>
    public class UserEntity: IIdableEntity
    {
        [PrimaryKey, AutoIncrement]
        public override int Id { get; set; }

        /// <summary>
        /// The user's name [unique] [not null]
        /// </summary>
        [NotNull]
        public string Name { get; set; }

        /// <summary>
        /// The user's password [not null]
        /// </summary>
        [NotNull]
        public string Password { get; set; } 
    }
}