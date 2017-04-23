using System;

namespace ModelComponent.Domain
{
    /// <summary>
    /// The representation of a player in our system.
    /// Designed to support the player's name & age.
    /// An object player is participating to an object event. 
    /// [A player can participate to an event without an account.]
    /// </summary>
    [Serializable]
    public class Player: Idable<int>
    {
        /// <summary>
        /// The player's name.
        /// </summary>
        public string Name { get; }

        /// <summary>
        /// The player's age.
        /// </summary>
        public int Age { get; }

        /// <summary>
        /// Creates a player with a valid id.
        /// </summary>
        /// <param name="id">The player's id</param>
        /// <param name="name">The player's name</param>
        /// <param name="age">The player's age</param>
        public Player(int id, string name, int age): base (id)
        { 
            Name = name;
            Age = age;
        }

        /// <summary>
        /// Creates a player with an invalid/empty id = 0.
        /// </summary>
        /// <param name="name">The player's name</param>
        /// <param name="age">The player's age</param>
        public Player(string name, int age) : this(0, name, age) { }

        public override bool Equals(object obj)
        {
            if (obj == null)
            {
                return false;
            }
            if (obj == this)
            {
                return true;
            }
            var other = (Player)obj;
            return GetId().Equals(other.GetId()) &&
                   Name.Equals(other.Name) &&
                   Age.Equals(other.Age);
        }

        protected bool Equals(Player other)
        {
            return string.Equals(Name, other.Name) &&
                Age.Equals(other.Age);
        }

        public override int GetHashCode()
        {
            unchecked
            {
                return ((Name?.GetHashCode() ?? 0) * 397) ^ Age;
            }
        }
    }
}